package xin.fcxl9876.common.entity.activitiEntity;

import cn.hutool.core.util.ObjectUtil;
import xin.fcxl9876.common.conf.JpaDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程开启参数
 *
 * @author shaolay
 * @date 2023/3/8 9:08
 */
@Data
@JpaDto
public class ProcessStartParam {

    @ApiModelProperty(value = "流程标识", required = true)
    private String processKey;

    @ApiModelProperty(value = "申请人", required = true)
    private String userId;

    @ApiModelProperty(value = "业务键", required = true)
    private String businessKey;

    @ApiModelProperty(value = "变量", required = true)
    private Map<String, Object> variables;

    public Map<String, Object> getVariables() {
        if (ObjectUtil.isNull(variables)){
            variables = new HashMap<String, Object>();
        }
        variables.put("userId", userId);
        return variables;
    }

}
