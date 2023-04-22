package xin.fcxl9876.common.entity.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.entity.primaryKey.ManholeKey;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/28 14:41
 */
@Entity
@Data
@Table(name = "t_bas_original_data_manhole")
@IdClass(ManholeKey.class)
@Accessors(chain = true)
public class ManholeData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mp_id")
    private String mpId;

    /**
     * 监测时间
     **/
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "monitor_time")
    private Date monitorTime;

    /**
     * 信号量
     **/
    @Column(name = "semaphore")
    private String semaphore;

    /**
     * 电压
     **/
    @Column(name = "voltage")
    private String voltage;

    /**
     * 状态 o 开启 c 关闭
     **/
    @Column(name = "state")
    private String state;

}
