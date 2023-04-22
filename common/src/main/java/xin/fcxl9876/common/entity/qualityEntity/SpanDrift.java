package xin.fcxl9876.common.entity.qualityEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/20 14:03
 */
@Data
@Entity
@Table(name="t_oth_zero_drift")
public class SpanDrift implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String id;

    /** 核查相对误差 **/
    @Column(name="check_relative_error")
    private String checkRelativeError;

    /** 指标Code **/
    @Column(name="index_code")
    private String indexCode;

    /** 指标名称 **/
    @Column(name="index_name")
    private String indexName;

    /** 指标单位 **/
    @Column(name="index_unit")
    private String indexUnit;

    /** 前24小时测定值 **/
    @Column(name="last_value")
    private String lastValue;

    /** 合格情况 **/
    @Column(name="qualification")
    private String qualification;

    /** 相对误差 **/
    @Column(name="drift_relative_error")
    private String driftRelativeError;

    /** 跨度值 **/
    @Column(name="span_value")
    private String spanValue;

    /** 标样值 **/
    @Column(name="standard_samples_value")
    private String standardSamplesValue;

    /** 测试时间 **/
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name="time")
    private Date time;

    /** 测定值 **/
    @Column(name="value")
    private String value;

    /** 前24小时合格情况 **/
    @Column(name="last_qualification")
    private String lastQualification;

    /** 站点编码 **/
    @Column(name="station_code")
    private String stationCode;

    /** 站点名称 **/
    @Column(name="station_name")
    private String stationName;

    /** 流域编码 **/
    @Column(name="basin_code")
    private String basinCode;

    /** 流域名称 **/
    @Column(name="basin_name")
    private String basinName;

    /** 区域编码 **/
    @Column(name="area_code")
    private String areaCode;

    /** 区域名称 **/
    @Column(name="area_name")
    private String areaName;

    /** 运营编码 **/
    @Column(name="operation_code")
    private String operationCode;

    /** 运营名称 **/
    @Column(name="operation_name")
    private String operationName;

    /** 核查相对误差技术标准 **/
    @Column(name="check_technical_requirement")
    private String checkTechnicalRequirement;

    /** 漂移相对误差技术标准 **/
    @Column(name="drift_technical_requirement")
    private String driftTechnicalRequirement;

    /** 质控配置参数ID **/
    @Column(name="quality_dispose_id")
    private String qualityDisposeId;

}
