package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 9:25
 */
@Data
@Entity
@Table(name = "t_bas_station_level")
@NamedQuery(name="StationLevel.findAll", query="SELECT b FROM StationLevel b")
public class StationLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="station_level_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String stationLevelId;

    /**站点级别编码**/
    @Column(name = "station_level_code")
    private String stationLevelCode;

    /**站点级别名称**/
    @Column(name = "station_level_name")
    private String stationLevelName;

    /**是否启用（0-否,1-是）**/
    @Column(name = "status")
    private String status;

    /**备注**/
    @Column(name = "remark")
    private String remark;
}
