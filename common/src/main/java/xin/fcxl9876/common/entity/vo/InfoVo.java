package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.entity.basEntity.Area;
import xin.fcxl9876.common.entity.basEntity.Basin;
import xin.fcxl9876.common.entity.basEntity.StationType;
import lombok.Data;

import java.util.List;

/**
 * 地区、流域、运维单位、站点类型
 * @author ??
 * @date 2023/3/2 14:15
 */
@Data
public class InfoVo {

    /**
     * 省份
     */
    private List<Area> provinceList;

    /**
     * 流域
     */
    private List<Basin> basinList;

    /**
     * 运维单位
     */
    private List<Basin> companyList;

    /**
     * 站点类型
     */
    private List<StationType> stationTypeList;
    




}
