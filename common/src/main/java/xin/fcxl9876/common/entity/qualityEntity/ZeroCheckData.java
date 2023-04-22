package xin.fcxl9876.common.entity.qualityEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/20 11:03
 */
@Entity
@Data
@Table(name = "t_mid_zerocheck_data")
public class ZeroCheckData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "zerocheck_id")
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String id;

    /**
     * 数据时间
     **/
    @Column(name = "data_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dataTime;

    /**
     * 因子编码
     **/
    @Column(name = "factor_code")
    private String factorCode;

    /**
     * 数据状态标识
     **/
    @Column(name = "flag")
    private String flag;

    /**
     * 站点mn
     **/
    @Column(name = "mn")
    private String mn;

    /**
     * 零点核查数据
     **/
    @Column(name = "check_data")
    private String CheckData;

    /**
     * 标准样浓度
     **/
    @Column(name = "standard_value")
    private String StandardValue;

    /**
     * 仪器跨度值
     **/
    @Column(name = "span_value")
    private String SpanValue;

    /**
     * 更新标识
     **/
    @Column(name = "update_flag")
    private String updateFlag;

    @Transient
    private String stationName;

    @Transient
    private String factorName;
}
