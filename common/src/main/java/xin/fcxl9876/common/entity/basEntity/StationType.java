package xin.fcxl9876.common.entity.basEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shaolay
 * @date 2023/2/17 9:27
 */
@Data
@Entity
@Table(name = "t_bas_station_type")
@NamedQuery(name="StationType.findAll", query="SELECT b FROM StationType b")
public class StationType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="station_type_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String stationTypeId;

    /**监测站点类型编码**/
    @Column(name = "station_type_code")
    private String stationTypeCode;

    /**父编码**/
    @Column(name = "parent_code")
    private String parentCode;

    /**监测站点类型名称**/
    @Column(name = "station_type_name")
    private String stationTypeName;

    /**是否启用（0-否,1-是）**/
    @Column(name = "status")
    private String status;

    /**备注**/
    @Column(name = "remark")
    private String remark;

    /**操作人**/
    @Column(name = "operation_user")
    private String operationUser;

    /**操作时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "operation_time")
    private Date operationTime;

    /**等级**/
    @Column(name = "level")
    private Integer level;

    @Transient
    private List<StationType> children = new ArrayList<StationType>();

}
