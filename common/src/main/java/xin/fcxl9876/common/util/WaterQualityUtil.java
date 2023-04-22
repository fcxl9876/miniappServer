package xin.fcxl9876.common.util;

import cn.hutool.core.util.ObjectUtil;
import xin.fcxl9876.common.constant.DataStateConstant;
import xin.fcxl9876.common.entity.basEntity.Project;
import xin.fcxl9876.common.entity.basEntity.ProjectQuality;
import xin.fcxl9876.common.entity.basEntity.ProjectRating;
import xin.fcxl9876.common.entity.basEntity.Station;
import xin.fcxl9876.common.entity.dto.StationInfoDto;
import xin.fcxl9876.common.entity.midEntity.StationAndProject;
import xin.fcxl9876.common.entity.vo.DataVo;
import xin.fcxl9876.common.enums.TimeTypeEnum;
import xin.fcxl9876.common.enums.WaterQualityLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 水质等级计算工具
 *
 * @author shaolay
 * @date 2023/2/20 13:51
 */
@Slf4j
public class WaterQualityUtil {

    private static List<ProjectRating> projectRatingList;

    private static Map<String, List<ProjectRating>> projectRatingMap;

    private static Map<String, List<Double>> projectWaterQuality;

    private static final class InstanceHolder {
        static final WaterQualityUtil instance = new WaterQualityUtil();
    }


    public static WaterQualityUtil getInstance() {
        return InstanceHolder.instance;
    }

    public List<ProjectRating> getProjectRatingList() {
        return projectRatingList;
    }

    public Double getLimitByProjectCodeAndLevel(String projectCode, int level){
        List<Double> limits = projectWaterQuality.get(projectCode);

        if (ObjectUtil.isNull(limits)){
            return null;
        }

        return limits.get(level - 1);
    }

    /**
     * 报表中展示的数据和报表时间类型不一样
     */
    private static Map<TimeTypeEnum, TimeTypeEnum> reportTypeEnum = new HashMap<TimeTypeEnum, TimeTypeEnum>(){{
        put(TimeTypeEnum.day, TimeTypeEnum.hour);
        put(TimeTypeEnum.week, TimeTypeEnum.week);
        put(TimeTypeEnum.month, TimeTypeEnum.day);
        put(TimeTypeEnum.year, TimeTypeEnum.month);
    }};

    public void setProjectRatingList(List<ProjectRating> projectRatingList) {
        this.projectRatingList = projectRatingList;
        this.projectRatingMap = this.projectRatingList.stream().collect(Collectors.groupingBy(ProjectRating::getProjectCode));
    }

    public void setProjectWaterQuality(List<ProjectQuality> projectQualities){
        projectWaterQuality = new HashMap<>();
        for (ProjectQuality projectQuality : projectQualities) {
            this.projectWaterQuality.put(projectQuality.getProjectCode(), Arrays.asList(projectQuality.getOne(),
                    projectQuality.getTwo(), projectQuality.getThree(), projectQuality.getFour(),
                    projectQuality.getFive()));
        }
    }

    /**
     * 计算数据集合中的水质等级、主要污染物
     *
     * @param dataList
     * @param timeType 数据类型
     */
    public static void setWaterQuality(List<StationInfoDto> dataList, TimeTypeEnum timeType, boolean isReport) {
        if (isReport){
            timeType = reportTypeEnum.get(timeType);
        }

        for (StationInfoDto stationInfoDto : dataList) {
            //站点信息
            Station station = stationInfoDto.getStation();

            //对数据进行处理
            Map<String, DataVo> dataMap = stationInfoDto.getDataMap();

            List<String> mainPollutant = new ArrayList<>();
            int waterQualityNum = 0;
            int stationState = DataStateConstant.OFF_LINE;

            Date latestTime = DateUtil.getSystemTime(DateUtil.DATE_SMALL_YMDHMS);
            if (dataMap != null && !dataMap.isEmpty()) {
                latestTime = stationInfoDto.getDataTime() == null ?
                        Collections.max(dataMap.values().stream().map(DataVo::getMonitorTime).collect(Collectors.toList())) : DateUtil.stringToDate(stationInfoDto.getDataTime(), timeType);
            } else {
                dataMap = new HashMap<>();
                stationInfoDto.setDataMap(dataMap);
            }

            List<String> compositeIndexes = new ArrayList<>();
            //对站点关联因子进行遍历
            for (StationAndProject stationAndProject : station.getStationAndProjects()) {
                //获取因子对应数据
                Project project = ProjectUtil.getInstance().getProjectByCode(stationAndProject.getProjectCode());
                DataVo dataVo = null;

                dataVo = dataMap.get(project.getProjectCode());

                //在实时数据和历史数据中，无数据展示不一样
                if (dataVo == null) {
                    dataVo = new DataVo();
                    if (TimeTypeEnum.hour.compareTo(timeType) == 0) {
                        if (judgeCycle(stationAndProject, DateUtil.stringToDate(stationInfoDto.getDataTime(), timeType))) {
                            dataVo.setProjectState(DataStateConstant.OFF_LINE);
                            dataVo.setDataValueAvg("--");
                            dataVo.setProject(project);
                            dataVo.setProjectCode(project.getProjectCode());
                            dataMap.put(project.getProjectCode(), dataVo);
                            continue;
                        } else {
                            dataVo.setProjectState(DataStateConstant.OFF_LINE);
                            dataVo.setProject(project);
                            dataVo.setProjectCode(project.getProjectCode());
                            dataMap.put(project.getProjectCode(), dataVo);
                            continue;
                        }
                    } else {
                        //没有数据则单因子离线
                        dataVo.setProjectState(DataStateConstant.OFF_LINE);
                        dataVo.setDataValueAvg("--");
                        dataVo.setProject(project);
                        dataVo.setProjectCode(project.getProjectCode());
                        dataMap.put(project.getProjectCode(), dataVo);
                        continue;
                    }
                }

                //数据修约
                if (StringUtils.isNotBlank(dataVo.getDataValueAvg())) {
                    dataVo.setDataValueAvg(roundByProject(stationAndProject, dataVo.getDataValueAvg()));
                }
                if (StringUtils.isNotBlank(dataVo.getDataValueMax())) {
                    dataVo.setDataValueMax(roundByProject(stationAndProject, dataVo.getDataValueMax()));
                }
                if (StringUtils.isNotBlank(dataVo.getDataValueMin())) {
                    dataVo.setDataValueMin(roundByProject(stationAndProject, dataVo.getDataValueMin()));
                }
                if (StringUtils.isNotBlank(dataVo.getDataValueCou())) {
                    dataVo.setDataValueCou(roundByProject(stationAndProject, dataVo.getDataValueCou()));
                }

                //数据vo类中存储因子信息
                dataVo.setProject(project);

                //计算因子水质等级
                Integer projectLevel = calWaterQualityNum(dataVo.getProjectCode(), dataVo.getDataValueAvg());

                //计算单因子综合污染指数
                String compositeIndex = calCompositeIndex(dataVo.getProjectCode(), dataVo.getDataValueAvg());
                if (StringUtils.isNotBlank(compositeIndex)) {
                    compositeIndexes.add(compositeIndex);
                }

                //设置单因子水质等级
                dataVo.setWaterQualityNum(projectLevel);
                dataVo.setWaterQuality(WaterQualityLevelEnum.NumToChinese(projectLevel));
                dataVo.setCompositeIndex(compositeIndex);

                //判断单因子是否超标
                if (projectLevel > station.getTargetLevel()) {
                    mainPollutant.add(project.getProjectName());
                    dataVo.setProjectState(DataStateConstant.EXCESS);
                } else {
                    dataVo.setProjectState(DataStateConstant.NORMAL);
                }
                //判断单因子是否异常
                if (!DataIdentUtil.getInstance().judgeDataStatus(dataVo.getDataState())) {
                    dataVo.setProjectState(DataStateConstant.UNUSUAL);
                }
                //判断站点的水质等级，取最大单因子水质等级
                waterQualityNum = projectLevel > waterQualityNum ? projectLevel : waterQualityNum;
                //当全部因子都离线时，站点离线
                stationState = stationState > dataVo.getProjectState() ? stationState : dataVo.getProjectState();
            }

            //判断站点是否超标
            if (waterQualityNum > station.getTargetLevel()) {
                stationInfoDto.setStationState(DataStateConstant.EXCESS);
                stationInfoDto.setPrimaryPollutant(StringUtils.join(mainPollutant, ","));
            } else {
                stationInfoDto.setStationState(stationState);
            }

            //当因子时间为空时取当前整点时间
            if (stationInfoDto.getDataTime() == null) {
                stationInfoDto.setDataTime(DateUtil.dateToString(latestTime, timeType));
            }

            //水质等级
            stationInfoDto.setWaterQualityNum(waterQualityNum);
            stationInfoDto.setWaterQuality(WaterQualityLevelEnum.NumToChinese(waterQualityNum));
            stationInfoDto.setCompositeIndex(NumberUtil.calAvg(compositeIndexes, 2, BigDecimal.ROUND_HALF_UP));
            stationInfoDto.setWaterCondition(Objects.requireNonNull(WaterQualityLevelEnum.getDesc(waterQualityNum)).getWaterCondition());
        }

        if (TimeTypeEnum.realTime.compareTo(timeType) == 0){
            //实时数据需要排序
            //结果排序按站点排序字段排序，再按站点状态排序，再按水质等级排序
            stationSort(dataList);
        }


    }

    /**
     * 数据修约
     * @param stationAndProject
     * @param dataValueAvg
     * @return
     */
    public static String roundByProject(StationAndProject stationAndProject, String dataValueAvg) {
        Integer reviseScale = 3;
        if (StringUtils.isNotBlank(stationAndProject.getReviseScale())){
            reviseScale = Integer.parseInt(stationAndProject.getReviseScale());
        }
        return NumberUtil.setScale(dataValueAvg, reviseScale, BigDecimal.ROUND_HALF_EVEN);
    }

    public Map<String, Object> calculationOver(Map<String, Object> data, int level) {

        Map<String, Object> result = new HashMap<String, Object>();

        for (String key : data.keySet()) {
            int grade = calWaterQualityNum(key, data.get(key).toString());
            grade = grade == 0 ? level : grade;
            if (level < grade) {

                BigDecimal sum = BigDecimal.ZERO;
                BigDecimal max = BigDecimal.ZERO;
                if (key.equals("w01009")) {
                    max = BigDecimal.TEN;
                }
                BigDecimal avg = BigDecimal.ZERO;
                BigDecimal value = BigDecimal.ZERO;
                BigDecimal size = BigDecimal.ZERO;
                value = new BigDecimal(data.get(key).toString());
                size = new BigDecimal(data.size());

                if (key.equals("w01009")) {
                    max = value.compareTo(max) == -1 ? value : max;
                } else {
                    max = value.compareTo(max) == 1 ? value : max;
                }
                sum = sum.add(value);
                avg = sum.divide(size, 4, RoundingMode.HALF_UP);
                max = max.divide(BigDecimal.ONE, 4, RoundingMode.HALF_UP);

                Map<String, String> qualityMap = getMapByTData(key, max.toString(), level);

                result.put(key, qualityMap.get("real"));

            } else {
                result.put(key, "--");
            }

        }

        return result;
    }

    private Map<String, String> getMapByTData(String code, String value, int mb) {

        Map<String, String> map = new HashMap<String, String>();

        if (code == null || value == null || mb > 5)
            return null;
        List<Double> levelList = projectWaterQuality.get(code);
        if (levelList == null) {
            return null;
        }
        double val = Double.valueOf(value);

        DecimalFormat df = new DecimalFormat("#.###");

        if (code.equals("w01001")) {
            if (6.0 < Double.valueOf(value) && Double.valueOf(value) < 9.0) {
                return null;
            } else if (6.0 > Double.valueOf(value)) {
                map.put("standard", "6.0-9.0");
                map.put("type", "超下限");
                map.put("real", df.format((6.0 - val) / 6.0));
                return map;
            } else {
                map.put("standard", "6.0-9.0");
                map.put("type", "超上限");
                map.put("real", df.format((val - 9.0) / 9.0));
                return map;
            }
        } else {
            if (code.equals("w01009")) {
                double s = levelList.get(mb - 1);
                String b = df.format((s - val) / s);

                map.put("standard", s + "");
                map.put("type", "超下限");
                map.put("real", String.valueOf(b));

                return map;

            } else {
                double u = levelList.get(mb - 1);
                String b = df.format((val - u) / u);

                map.put("standard", u + "");
                map.put("type", "超上限");
                map.put("real", String.valueOf(b));

                return map;
            }
        }
    }

    public static int calculationLevel(Map<String, Object> data, int level) {
        int res = level;
        int grade = 0;
        for (String key : data.keySet()) {
            if("w21001".equals(key)||"w00000".equalsIgnoreCase(key)) {
                continue;
            }
            grade = calWaterQualityNum(key, data.get(key).toString());
            res = grade > res ? grade : res;
        }
        return res;
    }
    /**
     * 结果排序按站点排序字段排序，再按站点状态排序，再按水质等级排序
     * @param dataList 结果
     */
    private static void stationSort(List<StationInfoDto> dataList) {
        //1.按站点排序字段排序
        Collections.sort(dataList, new Comparator<StationInfoDto>() {
            @Override
            public int compare(StationInfoDto o1, StationInfoDto o2) {

                Integer sortNum1 = o1.getStation().getSortNum() == null ? 9999999 : o1.getStation().getSortNum();
                Integer sortNum2 = o2.getStation().getSortNum() == null ? 9999999 : o2.getStation().getSortNum();

                return sortNum1 - sortNum2;
            }
        });

        //2.按站点状态排序
        Collections.sort(dataList, new Comparator<StationInfoDto>() {
            @Override
            public int compare(StationInfoDto o1, StationInfoDto o2) {

                Integer sortNum1 = DataStateConstant.DATA_STATE_SORT.indexOf(o1.getStationState());
                Integer sortNum2 = DataStateConstant.DATA_STATE_SORT.indexOf(o2.getStationState());

                return sortNum1 - sortNum2;
            }
        });

        //3.按水质等级排序
        Collections.sort(dataList, new Comparator<StationInfoDto>() {
            @Override
            public int compare(StationInfoDto o1, StationInfoDto o2) {

                Integer sortNum1 = DataStateConstant.WATER_LEVEL_SORT.indexOf(o1.getWaterQualityNum());
                Integer sortNum2 = DataStateConstant.WATER_LEVEL_SORT.indexOf(o2.getWaterQualityNum());

                return sortNum1 - sortNum2;
            }
        });

    }

    /**
     * 计算水质等级
     *
     * @param project 因子编码
     * @param value   数值
     * @return 水质等级
     */
    public static Integer calWaterQualityNum(String project, String value) {
        Integer waterQualityNum = 0;

        if (StringUtils.isBlank(project) || StringUtils.isBlank(value) || "--".equals(value)){
            return waterQualityNum;
        }

        try {
            //获取对应因子的水质等级标准
            List<ProjectRating> projectRatings = projectRatingMap.get(project);

            if (projectRatings != null) {
                for (ProjectRating projectRating : projectRatings) {
                    //判断数值是否在给定条件范围内
                    if (judgeRange(value, projectRating)) {
                        waterQualityNum = projectRating.getWaterLevelNum() > waterQualityNum ? projectRating.getWaterLevelNum() : waterQualityNum;
                    }
                }
            }
        } catch (Exception e) {
            log.error("计算水质等级", e);
        }

        return waterQualityNum;
    }

    /**
     * 计算综合污染指数
     *
     * @param data 数据集合
     * @return 水质等级
     */
    public static String calCompositeIndex(Map<String, Object> data) {

        List<String> compositeIndexes = new ArrayList<>();
        for (String project : data.keySet()) {
            String value = String.valueOf(data.get(project));
            compositeIndexes.add(calCompositeIndex(project, value));
        }

        return NumberUtil.calAvg(compositeIndexes, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 计算综合污染指数
     *
     * @param project 因子编码
     * @param value   数值
     * @return 水质等级
     */
    public static String calCompositeIndex(String project, String value) {
        // 结果
        String result = "";

        if (StringUtils.isBlank(value)){
           return result;
        }

        // 因子目标值
        List<Double> valueTarget = new ArrayList<Double>();
        // 标准值集合
        valueTarget = projectWaterQuality.get(project);

        if (ObjectUtil.isNull(valueTarget)){
            return result;
        }

        BigDecimal compositeIndex = null;
        try {
            compositeIndex = new BigDecimal(value).divide(BigDecimal.valueOf(valueTarget.get(2)), 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            log.error(project + "--" + value + "计算综合污染指数 失败！", e);
        }

        if (ObjectUtil.isNotNull(compositeIndex)){
            result = compositeIndex.toString();
        }

        return result;
    }

    /**
     * 判断数值是否在给定条件范围内
     *
     * @param value         数值
     * @param projectRating 条件范围（有最大、最小值以及是否能取最大最小值）
     * @return true 在给定条件范围内
     */
    private static boolean judgeRange(String value, ProjectRating projectRating) {

        BigDecimal valueBig = new BigDecimal(value);
        BigDecimal upperBig = new BigDecimal(projectRating.getUpperLimit());
        BigDecimal lowerBig = new BigDecimal(projectRating.getLowerLimit());

        //大于上限
        if (valueBig.compareTo(upperBig) == 1) {
            return false;
        }

        //小于下限
        if (valueBig.compareTo(lowerBig) == -1) {
            return false;
        }

        //边界值的问题
        //等于上限且条件为不包含时
        if (valueBig.compareTo(upperBig) == 0 && projectRating.getUpperEqual() != 1) {
            return false;
        }
        //等于下限且条件为不包含时
        if (valueBig.compareTo(lowerBig) == 0 && projectRating.getLowerEqual() != 1) {
            return false;
        }

        return true;
    }



    /**
     * 判断数值是否在对应上传周期上
     *
     * @param stationAndProject 站点因子关联关系，里面包含该站点因子的上传频率
     * @param dateTime        数据时间
     * @return true 在周期内
     */
    public static boolean judgeCycle(StationAndProject stationAndProject, Date dateTime) {
        Integer monitorFrequency = 24;

        try {
            String monitorFrequencyStr = stationAndProject.getMonitorFrequency();
            monitorFrequency = Integer.valueOf(monitorFrequencyStr);
        } catch (Exception e){
//            log.error(stationAndProject.getStationCode() + "站点:" + stationAndProject.getProjectCode() + "因子未设置因子频率或不规范，已采用默认值:24(一小时一条)！");
        }
        //根据因子频率将对应天划分成集合
        List<Date> dates = DateUtil.divideDayByMonitorFrequency(monitorFrequency, dateTime);

        List<Long> dayList = dates.stream().map(l -> l.getTime()).collect(Collectors.toList());

        if (dayList.contains(dateTime.getTime())){
            return true;
        }

        return false;
    }

}
