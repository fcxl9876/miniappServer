package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 8:54
 */
@Data
@Entity
@Table(name = "t_bas_river_level", catalog = "db_water_subject")
@NamedQuery(name="RiverLevel.findAll", query="SELECT b FROM RiverLevel b")
public class RiverLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="river_level_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String riverLevelId;

    /**河流等级名称**/
    @Column(name = "river_level_name")
    private String riverLevelName;

    /**备注**/
    @Column(name = "remark")
    private String remark;

    /**河流等级启用状态0_否1_是**/
    @Column(name = "status")
    private String status;

}
