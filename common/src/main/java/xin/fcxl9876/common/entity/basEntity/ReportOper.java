package xin.fcxl9876.common.entity.basEntity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shaolay
 * @date 2023/2/28 16:55
 */
@Entity
@Data
@Table(name = "t_bas_report_oper", catalog = "db_water_subject")
public class ReportOper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /**
     * 站点mn号
     */
    @Column(name = "station_code")
    private String stationCode;

    /**
     * 报表编码
     */
    @Column(name = "report_code")
    private String reportCode;

    /**
     * 报表名称
     */
    @Column(name = "report_name")
    private String reportName;

    /**
     * 因子编码
     */
    @Column(name = "project_code")
    private String projectCode;

    /**
     * 操作编码（avg，取均值，sum，取累计量）
     */
    @Column(name = "oper_code")
    private String operCode;

    /**
     * 操作名称（avg，取均值，sum，取累计量）
     */
    @Column(name = "oper_name")
    private String operName;

    /**
     * 时间类型
     */
    @Column(name = "date_type")
    private String dateType;

    /**
     * 行名
     */
    @Column(name = "row_name")
    private String rowName;
}
