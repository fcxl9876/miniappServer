package xin.fcxl9876.common.entity.activitiEntity;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务变量参数接收 用于任务完成 驳回等
 *
 * @author shaolay
 * @date 2023/3/8 9:38
 */
@Data
public class TaskVariables implements Serializable {

    private static final long serialVersionUID = 11L;

    @ApiModelProperty(value = "流程实例id", required = true)
    private String instanceId;

    @ApiModelProperty(value = "任务完成人", required = true)
    private String assignee; //任务完成人

    @ApiModelProperty(value = "备注", required = true)
    private String comment; //备注

    @ApiModelProperty(value = "下一个审批人", required = true)
    private String nextUserId; //下一个审批人

    @ApiModelProperty(value = "驳回的目标节点", required = true)
    private String rejectedTargetNode;

    @ApiModelProperty(value = "任务变量 可以存储任务完成信息等", required = true)
    private Map<String, Object> params; //任务变量 可以存储任务完成信息等

    public Map<String, Object> getParams() {
        if (ObjectUtil.isNull(params)){
            params = new HashMap<String, Object>();
        }
        params.put("userId", nextUserId);
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
