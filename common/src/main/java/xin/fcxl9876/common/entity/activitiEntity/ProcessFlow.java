package xin.fcxl9876.common.entity.activitiEntity;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author shaolay
 * @date 2023/3/7 17:10
 */
@Data
public class ProcessFlow {

    private String id;

    private String name;

    private List<ProcessNode> ProcessNodes;

    public void setId(String id) {
        if (StringUtils.isBlank(id)) {
            id = IdUtil.simpleUUID();
        }
        this.id = id;
    }

}
