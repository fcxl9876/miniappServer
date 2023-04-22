package xin.fcxl9876.common.entity.midEntity;

import xin.fcxl9876.common.entity.basEntity.StationType;
import xin.fcxl9876.common.entity.primaryKey.UserAndStationKey;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 9:53
 */
@Data
@Entity
@Table(name = "t_mid_user_station")
@IdClass(UserAndStationKey.class)
@NamedQuery(name="UserAndStation.findAll", query="SELECT b FROM UserAndStation b")
public class UserAndStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "station_code")
    private String stationCode;

    /**站点类型编码（关联站点类型表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_type_code", referencedColumnName = "station_type_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private StationType stationType;
}
