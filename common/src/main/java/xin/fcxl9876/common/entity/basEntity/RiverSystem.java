package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 8:57
 */
@Data
@Entity
@Table(name = "t_bas_water_river_system", catalog = "db_water_subject")
@NamedQuery(name="RiverSystem.findAll", query="SELECT b FROM RiverSystem b")
public class RiverSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="system_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String systemId;

    /**河流水系名称**/
    @Column(name = "system_name")
    private String systemName;

    /**水系类型（树枝状、扇形、羽状、平行状、格子状）**/
    @Column(name = "system_type")
    private String systemType;

    /**水系归宿（内流（流入内海，湖泊）、外流（流入江河海洋））**/
    @Column(name = "system_result")
    private String systemResult;

    /**水系密度**/
    @Column(name = "system_density")
    private String systemDensity;
}
