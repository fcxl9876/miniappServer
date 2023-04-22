package xin.fcxl9876.common.entity.vo;

import lombok.Data;

/**
 * @author shaolay
 * @date 2023/3/27 9:45
 */
@Data
public class DataAuditBo {

    // 审核数据主键id
    private Integer id;

    private String name; // 字段名称

    private String value; // 字段值

    private int colorStatus = 1; // 1 有效 2无效 3存疑 4 已审核 5 差异

    private String remark; // 备注

    private String flowId; //流程实例id

    private boolean operationAudit;
}
