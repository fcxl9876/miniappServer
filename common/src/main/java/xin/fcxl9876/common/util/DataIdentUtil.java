package xin.fcxl9876.common.util;


import xin.fcxl9876.common.entity.basEntity.DataIdentification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shaolay
 * @date 2023/3/1 16:43
 */
public class DataIdentUtil {


    private static DataIdentUtil instance;

    private Map<String, DataIdentification> map;

    public static DataIdentUtil getInstance() {
        if (instance == null) {
            synchronized (DataIdentUtil.class) {
                if (instance == null) {
                    instance = new DataIdentUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据编码获取数据标识信息
     *
     * @param code 编码
     * @return
     */
    public DataIdentification getDataIdentByCode(String code) {
        DataIdentification dataIdentification = map.get(code);
        if (dataIdentification == null) {
            dataIdentification = new DataIdentification().setCode(code).setExplain("未定义数据标识(" + code + ")").setDefintion("未定义数据标识(" + code + ")").setFlag("1");
        }
        return dataIdentification;
    }

    /**
     * 根据编码获判断数据标识是否异常
     *
     * @param code
     * @return true 正常
     */
    public boolean judgeDataStatus(String code) {
        DataIdentification dataIdentification = map.get(code);
        if (dataIdentification == null) {
            return true;
        }
        return "1".equals(dataIdentification.getFlag());
    }

    public void setMap(List<DataIdentification> dataIdentifications) {
        this.map = dataIdentifications.stream().collect(Collectors.toMap(DataIdentification::getCode, l -> l));
    }

}
