package xin.fcxl9876.common.entity.quartzEntity;

import xin.fcxl9876.common.entity.primaryKey.QrtzJobDetailKey;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@IdClass(QrtzJobDetailKey.class)
@Table(name = "qrtz_job_details", catalog = "db_quartz_subject")
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**调度器名称**/
    @Id
    @Column(name = "sched_name")
    private String schedName;

    /**任务名称**/
    @Id
    @Column(name = "job_name")
    private String jobName;

    /**任务群组**/
    @Id
    @Column(name = "job_group")
    private String jobGroup;

    /**说明**/
    @Column(name = "description")
    private String description;

    /**任务class全路径**/
    @Column(name = "job_class_name")
    private String jobClassName;

    /**持久化**/
    @Column(name = "is_durable")
    private String isDurable;

    /**监测值**/
    @Column(name = "is_nonconcurrent")
    private String isNonconcurrent;

    /**更新时间**/
    @Column(name = "is_update_data")
    private String isUpdateData;

    /**请求恢复**/
    @Column(name = "requests_recovery")
    private String requestsRecovery;

    /**任务数据**/
    @Lob
    @Column(name = "job_data")
    private byte[] jobData;
}
