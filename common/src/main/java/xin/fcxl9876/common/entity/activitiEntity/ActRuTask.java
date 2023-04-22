//package xin.fcxl9876.common.entity.ActivitiEntity;
//
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author shaolay
// * @date 2023/3/10 16:08
// */
//@Entity
//@Data
//@Table(name = "act_ru_task")
//public class ActRuTask implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @Column(name = "ID_")
//    @GeneratedValue(generator = "uuidGenerator")
//    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
//    private String id;
//
//    @Column(name = "REV_")
//    private String rev;
//
//    @Column(name = "EXECUTION_ID_")
//    private String executionId;
//
//    @Column(name = "PROC_INST_ID_")
//    private String procInstId;
//
//    @Column(name = "PROC_DEF_ID_")
//    private String procDefId;
//
//    @Column(name = "NAME_")
//    private String name;
//
//    @Column(name = "PARENT_TASK_ID_")
//    private String parentTaskId;
//
//    @Column(name = "DESCRIPTION_")
//    private String description;
//
//    @Column(name = "TASK_DEF_KEY_")
//    private String taskDefKey;
//
//    @Column(name = "OWNER_")
//    private String owner;
//
//    @Column(name = "ASSIGNEE_")
//    private String assignee;
//
//    @Column(name = "DELEGATION_")
//    private String delegation;
//
//    @Column(name = "PRIORITY_")
//    private String priority;
//
//    @Column(name = "CREATE_TIME_")
//    private Date createTime;
//
//    @Column(name = "DUE_DATE_")
//    private Date dueDate;
//
//    @Column(name = "CATEGORY_")
//    private String category;
//
//    @Column(name = "SUSPENSION_STATE_")
//    private Integer suspensionState;
//
//    @Column(name = "TENANT_ID_")
//    private String tenantId;
//
//    @Column(name = "FORM_KEY_")
//    private String formKey;
//
//    @Column(name = "CLAIM_TIME_")
//    private Date claimTime;
//
//}
