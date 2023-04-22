package xin.fcxl9876.common.entity.activitiEntity;

import xin.fcxl9876.common.page.CriterionRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/3/10 15:50
 */
@Data
public class TaskQueryVo extends CriterionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程定义key", required = false)
    private String processKey;
    @ApiModelProperty(value = "用户id", required = false)
    private String userId;

}
