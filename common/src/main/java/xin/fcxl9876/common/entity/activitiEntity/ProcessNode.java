package xin.fcxl9876.common.entity.activitiEntity;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @author shaolay
 * @date 2023/3/7 17:11
 */
@Data
public class ProcessNode {

    private String id;

    private String name;

    private String approve;

    public void setId(String id) {
        if (StringUtils.isBlank(id)) {
            id = IdUtil.simpleUUID();
        }
        this.id = id;
    }
}
