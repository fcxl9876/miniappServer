package xin.fcxl9876.common.entity.dataEntity;

import xin.fcxl9876.common.entity.primaryKey.YearDataKey;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 16:43
 */
@Entity
@Data
@Table(name = "t_mid_year_data_water")
@IdClass(YearDataKey.class)
@Accessors(chain = true)
public class WaterYearData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private String yearId;

    @Id
    @Column(name = "station_code")
    private String stationCode;

    @Id
    @Column(name = "project_code")
    private String projectCode;

    @Id
    @Column(name = "monitor_time")
    private String monitorTime;

    /**
     * 监测值
     **/
    @Column(name = "data_value_avg")
    private String dataValueAvg;

    /**
     * 最大值
     **/
    @Column(name = "data_value_max")
    private String dataValueMax;

    /**
     * 最小值
     **/
    @Column(name = "data_value_min")
    private String dataValueMin;

    /**
     * 数据状态
     **/
    @Column(name = "data_state")
    private String dataState;

    /**
     * 标准条数
     **/
    @Column(name = "standard_num")
    private String standardNum;

    /**
     * 实际条数
     **/
    @Column(name = "real_num")
    private String realNum;

    /**
     * 上传率
     **/
    @Column(name = "upload_rate")
    private String uploadRate;

    /**
     * 修约前数据
     **/
    @Column(name = "data_value_revise")
    private String dataValueRevise;

    /**
     * 数据累计值
     **/
    @Column(name = "data_value_cou")
    private String dataValueCou;

    /**
     * 水质等级
     **/
    @Column(name = "water_quality")
    private String waterQuality;

}
