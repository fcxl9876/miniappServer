package xin.fcxl9876.common.entity.primaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 9:55
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UserAndStationKey implements Serializable {

    private String userId;

    private String stationCode;
}
