package xin.fcxl9876.common.entity.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.entity.primaryKey.WeekDataKey;
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
@Table(name = "t_mid_week_data_water")
@IdClass(WeekDataKey.class)
@Accessors(chain = true)
public class WaterWeekData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "week_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String weekId;

    @Id
    @Column(name = "station_code")
    private String stationCode;

    @Id
    @Column(name = "project_code")
    private String projectCode;

    /**
     * 开始时间
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 年
     **/
    @Id
    @Column(name = "data_year")
    private String dataYear;

    /**
     * 周
     **/
    @Id
    @Column(name = "data_week")
    private String dataWeek;

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
     * 数据累计值
     **/
    @Column(name = "data_value_cou")
    private String dataValueCou;

    /**
     * 水质等级
     **/
    @Column(name = "water_quality")
    private String waterQuality;

    /**
     * 小时数据来源(0_直传、1_统计结果、2_外接)
     **/
    @Column(name = "data_source")
    private String dataSource;

}
