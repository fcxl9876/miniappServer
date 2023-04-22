package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 区域等级表
 *
 * @author shaolay
 * @date 2023/2/16 14:37
 */
@Entity
@Data
@Table(name = "t_bas_area_level")
public class AreaLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "area_level_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String areaLevelId;

    /**
     * 区域等级编码
     */
    @Column(name = "area_level_code")
    private String areaCode;

    /**
     * 区域等级名称
     */
    @Column(name = "area_level_name")
    private String areaLevelName;

    /**
     * 是否启用（0-否,1-是）
     */
    @Column(name = "status")
    private String areaName;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

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
