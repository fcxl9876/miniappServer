package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 区域表
 *
 * @author shaolay
 * @date 2023/2/16 14:37
 */
@Entity
@Data
@Table(name = "t_bas_area")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "area_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String id;

    /**
     * 区域编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 父编码
     */
    @Column(name = "parent_code")
    private String parentCode;

    /**
     * 区域名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 区域名称-别名（演示使用）
     */
    @Column(name = "area_alias")
    private String areaAlias;

    /**
     * 区域等级编码
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "area_level_code")
    @NotFound(action = NotFoundAction.IGNORE)
    private AreaLevel areaLevel;

    /**
     * 数据更新入库时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 操作人
     */
    @Column(name = "operation_user")
    private String operationUser;

    /**
     * 操作时间
     */
    @Column(name = "operation_time")
    private Date operationTime;

}
