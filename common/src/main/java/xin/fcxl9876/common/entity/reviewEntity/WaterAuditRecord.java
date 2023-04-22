package xin.fcxl9876.common.entity.reviewEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/23 16:20
 */
@Entity
@Data
@Table(name = "t_oth_audit_record_water")
public class WaterAuditRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String id;

    /**
     * 流程实例id
     */
    @Column(name = "instance_id")
    private String instanceId;

    /**
     * 站点编码（关联站点表）
     */
    @Column(name = "station_code")
    private String stationCode;

    /**
     * 站点名称
     */
    @Column(name = "station_name")
    private String stationName;

    /**
     * 日报时间
     */
    @Column(name = "report_time")
    private Date reportTime;

    /**
     * 初审时间
     */
    @Column(name = "first_audit_time")
    private Date firstAuditTime;

    /**
     * 初审人员
     */
    @Column(name = "first_audit_user")
    private String firstAuditUser;

    /**
     * 复审时间
     */
    @Column(name = "review_audit_time")
    private Date reviewAuditTime;

    /**
     * 复审人员
     */
    @Column(name = "review_audit_user")
    private String reviewAuditUser;

    /**
     * 驳回初审人员
     */
    @Column(name = "reject_user")
    private String rejectUser;

    /**
     * 驳回时间
     */
    @Column(name = "reject_time")
    private Date rejectTime;

    /**
     * 驳回意见
     */
    @Column(name = "reject_suggestion")
    private String rejectSuggestion;

    /**
     * 二次复核人员
     */
    @Column(name = "recheck_user")
    private String recheckUser;

    /**
     * 二次复核时间
     */
    @Column(name = "recheck_time")
    private Date recheckTime;


}
