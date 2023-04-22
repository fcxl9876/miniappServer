package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.conf.JpaDto;
import xin.fcxl9876.common.entity.basEntity.Project;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author shaolay
 * @date 2023/2/20 14:15
 */
@Data
@JpaDto
@Accessors(chain = true)
public class DataVo {

    private String stationCode;

    private String projectCode;

    /**
     * 监测时间
     **/
    private Date monitorTime;

    /**
     * 报表时间
     **/
    private String reportTime;

    /**
     * 监测值
     **/
    private String dataValueAvg;

    /**
     * 最大值
     **/
    private String dataValueMax;

    /**
     * 最小值
     **/
    private String dataValueMin;

    /**
     * 数据累计值
     **/
    private String dataValueCou;

    /**
     * 标准条数
     **/
    private Integer standardNum;

    /**
     * 实际条数
     **/
    private Integer realNum;

    /**
     * 数据标识
     **/
    private String dataState;

    /**
     * 单因子水质等级
     */
    private Integer waterQualityNum;

    /**
     * 水质等级
     */
    private String waterQuality;

    /**
     * 单因子综合污染指数
     */
    private String compositeIndex;

    /**
     * 单因子状态 (0离线 1正常 2超标)
     */
    private Integer projectState;

    /**
     * 因子信息
     */
    private Project project;
}
