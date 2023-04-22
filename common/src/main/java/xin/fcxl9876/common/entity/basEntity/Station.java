package xin.fcxl9876.common.entity.basEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import xin.fcxl9876.common.entity.midEntity.StationAndProject;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shaolay
 * @date 2023/2/17 9:21
 */
@Data
@Entity
@Table(name = "t_bas_water_station")
@NamedQuery(name="Station.findAll", query="SELECT b FROM Station b")
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="station_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String stationId;

    /**站点编码**/
    @Column(name = "station_code")
    private String stationCode;

    /**站点名称**/
    @Column(name = "station_name")
    private String stationName;

    /**站点密码**/
    @Column(name = "station_password")
    private String stationPassword;

    /**站点名称-别名（演示使用）**/
    @Column(name = "station_alias")
    private String stationAlias;

    /**经度**/
    @Column(name = "longitude")
    private String longitude;

    /**纬度**/
    @Column(name = "latitude")
    private String latitude;

    /**站点地址**/
    @Column(name = "station_address")
    private String stationAddress;

    /**站点类型编码（关联站点类型表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_type_code", referencedColumnName = "station_type_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private StationType stationType;

    /**站点级别编码（关联站点级别表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_level_code", referencedColumnName = "station_level_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private StationLevel stationLevel;

    /**流域编码（关联流域表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "basin_code", referencedColumnName = "basin_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Basin basin;

    /**河流编码（关联河流信息表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "river_code", referencedColumnName = "river_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private River river;

    /**区域编码（关联区域信息表）**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "area_code", referencedColumnName = "area_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private Area area;

    /**是否启用（0-否,1-是）**/
    @Column(name = "status")
    private String status;

    /**目标等级（水质1-6）6 :劣V类**/
    @Column(name = "target_level")
    private Integer targetLevel;

    /**监测类型（0-手,1-自）**/
    @Column(name = "monitor_type")
    private String monitorType;

    /**站点信息简介**/
    @Column(name = "station_info")
    private String stationInfo;

    /**数据更新时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;

    /**操作人**/
    @Column(name = "operation_user")
    private String operationUser;

    /**站点排序**/
    @Column(name = "sort_num")
    private Integer sortNum;

    /**操作时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "operation_time")
    private Date operationTime;

    /**站点属性**/
    @Column(name = "station_property")
    private String stationProperty;

    /**站点属性**/
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "station_scale_code", referencedColumnName = "station_scale_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private StationScale stationScale;

    /** 站点因子关联关系 **/
    @JsonIgnoreProperties
    @OneToMany(targetEntity = StationAndProject.class)
    @JoinColumns(@JoinColumn(name = "station_code", referencedColumnName = "station_code"))
    private Set<StationAndProject> stationAndProjects = new HashSet<>();

    public void emptyUnnecessary(){
        this.setArea(null);
        this.setBasin(null);
        this.setStationAndProjects(null);
        this.setStationScale(null);
        this.setStationType(null);
    }
}
