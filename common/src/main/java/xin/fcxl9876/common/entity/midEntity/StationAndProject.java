package xin.fcxl9876.common.entity.midEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.entity.primaryKey.StationAndProjectKey;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/2/17 10:06
 */
@Data
@Entity
@Table(name = "t_bas_station_project")
@IdClass(StationAndProjectKey.class)
@Accessors(chain = true)
@NamedQuery(name = "StationAndProject.findAll", query = "SELECT b FROM StationAndProject b")
public class StationAndProject implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 站点编码 */
    @Id
    @Column(name = "station_code")
    private String stationCode;

    /** 监测指标编码 */
    @Id
    @Column(name = "project_code")
    private String projectCode;

    /** 站点因子单位（可能不同站点因子单位不同） */
    @Column(name = "project_unit")
    private String projectUnit;

    /** 监测频率(一天发送数据总条数)，-1一个月一条手工 */
    @Column(name = "monitor_frequency")
    private String monitorFrequency;

    /** 监测指标报警上限 */
    @Column(name = "alarm_upper")
    private String alarmUpper;

    /** 监测指标报警下限 */
    @Column(name = "alarm_lower")
    private String alarmLower;

    /** 修约位数 */
    @Column(name = "revise_scale")
    private String reviseScale;

    /** 操作人 */
    @Column(name = "operation_user")
    private String operationUser;

    /** 操作时间 */
    @Column(name = "operation_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationTime;

    /** 站点因子监测类型（sail:移动走航、point:定点走航） */
    @Column(name = "monitor_type")
    private String monitorType;

    /** 站点因子检出下限值 */
    @Column(name = "check_lower")
    private String checkLower;

    /** 站点因子检出上限值 */
    @Column(name = "check_upper")
    private String checkUpper;


}
