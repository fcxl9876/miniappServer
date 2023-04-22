package xin.fcxl9876.common.entity.primaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/24 10:37
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QrtzJobDetailKey implements Serializable {
    private String schedName;
    private String jobName;
    private String jobGroup;
}
