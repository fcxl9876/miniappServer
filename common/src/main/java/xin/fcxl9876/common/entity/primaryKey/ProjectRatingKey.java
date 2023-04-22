package xin.fcxl9876.common.entity.primaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/20 13:22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectRatingKey implements Serializable {

    private String projectCode;

    private Integer waterLevelNum;

    private String upperLimit;

    private String lowerLimit;
}
