package xin.fcxl9876.common.entity.basEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/2/17 8:51
 */
@Data
@Entity
@Table(name = "t_bas_river", catalog = "db_water_subject")
@NamedQuery(name="River.findAll", query="SELECT b FROM River b")
public class River implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="river_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String riverId;

    /**河流编码**/
    @Column(name = "river_code")
    private String riverCode;

    /**河流名称**/
    @Column(name = "river_name")
    private String riverName;

    /**河流长度**/
    @Column(name = "river_length")
    private String riverLength;

    /**是否启用（0-否,1-是）**/
    @Column(name = "status")
    private String status;

    /**备注**/
    @Column(name = "remark")
    private String remark;

    /**河流面积**/
    @Column(name = "river_area")
    private String riverArea;

    /**河流密度**/
    @Column(name = "river_density")
    private String riverDensity;

    /**河流形状**/
    @Column(name = "river_shape")
    private String riverShape;

    /**河流高度**/
    @Column(name = "river_height")
    private String riverHeight;

    /**水系**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "system_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private RiverSystem riverSystem;

    /**河流等级**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "river_level_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private RiverLevel riverLevel;

    /**线标志号**/
    @Column(name = "line_number")
    private String lineNumber;

    /**汇入点标志号**/
    @Column(name = "point_number")
    private String pointNumber;

    /**起点**/
    @Column(name = "start_point")
    private String startPoint;

    /**终点**/
    @Column(name = "end_point")
    private String endPoint;

    /**更新时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;
}
