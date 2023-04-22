package xin.fcxl9876.common.entity.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.entity.primaryKey.DataKey;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/2/17 16:43
 */
@Entity
@Data
@Table(name = "t_mid_month_data_water")
@IdClass(DataKey.class)
@Accessors(chain = true)
public class WaterMonthData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "month_id")
    private String monthId;

    @Id
    @Column(name = "station_code")
    private String stationCode;

    @Id
    @Column(name = "project_code")
    private String projectCode;

    /**开始时间**/
    @Id
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**监测值**/
    @Column(name = "data_value_avg")
    private String dataValueAvg;

    /**最大值**/
    @Column(name = "data_value_max")
    private String dataValueMax;

    /**最小值**/
    @Column(name = "data_value_min")
    private String dataValueMin;

    /**数据状态**/
    @Column(name = "data_state")
    private String dataState;

    /**标准条数**/
    @Column(name = "standard_num")
    private String standardNum;

    /**实际条数**/
    @Column(name = "real_num")
    private String realNum;

    /**上传率**/
    @Column(name = "upload_rate")
    private String uploadRate;

    /**修约前数据**/
    @Column(name = "data_value_revise")
    private String dataValueRevise;

    /**数据累计值**/
    @Column(name = "data_value_cou")
    private String dataValueCou;

    /**水质等级**/
    @Column(name = "water_quality")
    private String waterQuality;

}
