package xin.fcxl9876.common.entity.alarmEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/29 14:37
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "t_basic_alarm_message", catalog = "db_alarm")
public class AlarmMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String alarmId;

    /**
     * 报警等级(越小等级越高)
     */
    @Column(name = "alarm_level")
    private Integer alarmLevel;

    /**
     * 报警类型id
     */
    @Column(name = "data_equip_code")
    private String alarmTypeId;

    /**
     * 报警类型名称
     */
    @Column(name = "alarm_type_name")
    private String alarmTypeName;

    /**
     * 所属系统名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 所属系统id
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 所属监测点位id
     */
    @Column(name = "data_equip_id")
    private String dataEquipId;

    /**
     * 所属监测点位名称
     */
    @Column(name = "data_equip_name")
    private String dataEquipName;

    /**
     * 报警涉及因子id
     */
    @Column(name = "project_code")
    private String projectCode;

    /**
     * 报警涉及因子名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 报警时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "alarm_time")
    private Date alarmTime;

    /**
     * 报警结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 报警数据类型(0:h,1:d)
     */
    @Column(name = "date_type")
    private Integer dateType;

    /**
     * 监测数据条数(只有与原始数据直接关联的报警，该字段才有意义，其他默认为0)
     */
    @Column(name = "data_num")
    private Integer dataNum;

    /**
     * 报警事件内容
     */
    @Column(name = "alarm_desc")
    private Integer alarmDesc;

    /**
     * --
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * -关联的问题记录id-
     */
    @Column(name = "issue_id")
    private String issueId;

}
