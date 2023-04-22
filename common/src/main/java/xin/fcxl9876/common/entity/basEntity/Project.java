package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/17 9:13
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_bas_project")
@NamedQuery(name="Project.findAll", query="SELECT b FROM Project b")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="project_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid") // UUID生成策略
    private String projectId;

    /**监测指标编码（新国标）**/
    @Column(name = "project_code")
    private String projectCode;

    /**监测指标名称-化学式/英文**/
    @Column(name = "project_chemical")
    private String projectChemical;

    /**监测指标名称**/
    @Column(name = "project_name")
    private String projectName;

    /**监测指标单位**/
    @Column(name = "project_unit")
    private String projectUnit;

    /**监测指标修约位数**/
    @Column(name = "project_scale")
    private String projectScale;

    /**监测指标类型编码**/
    @Column(name = "project_type_code")
    private String projectTypeCode;

    /**备注**/
    @Column(name = "remark")
    private String remark;

    /**是否启用（0-否,1-是）**/
    @Column(name = "status")
    private String status;

    /**监测指标编码（旧编码）**/
    @Column(name = "project_code_old")
    private String projectCodeOld;

    /**仪器编码**/
    @Column(name = "instrument_code")
    private String instrumentCode;

    /**数据更新入库时间**/
    @Column(name = "update_time")
    private String updateTime;

    /**因子对应颜色**/
    @Column(name = "project_color")
    private String projectColor;

    /**分子量**/
    @Column(name = "project_mir")
    private String projectMir;

    /**voc因子对应的mir系数**/
    @Column(name = "formula_num")
    private String formulaNum;

    /**fac**/
    @Column(name = "project_fac")
    private String projectFac;

    /**FVOCr(%)**/
    @Column(name = "project_fvocr")
    private String projectFvocr;

    /**VOC化合物种类**/
    @Column(name = "pams")
    private String pams;

    /**因子类型名称**/
    @Transient
    private String projectTypeName;
}
