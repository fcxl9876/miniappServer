package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 数据标识字典表
 *
 * @author shaolay
 * @date 2023/3/1 16:24
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_bas_data_identification", catalog = "db_water_subject")
public class DataIdentification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据标识编码
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 定义
     */
    @Column(name = "defintion")
    private String defintion;

    /**
     * 解释
     */
    @Column(name = "explain")
    private String explain;

    /**
     * 是否正常（0异常，1正常）
     */
    @Column(name = "flag")
    private String flag;

}
