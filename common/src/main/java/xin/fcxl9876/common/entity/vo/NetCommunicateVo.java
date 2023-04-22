package xin.fcxl9876.common.entity.vo;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.conf.JpaDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 *
 *  联网通讯分析
 * @author shaolay
 * @date 2023/3/13 11:12
 */
@Data
@JpaDto
public class NetCommunicateVo {

    @ApiModelProperty(value = "站点编码")
    private String stationCode;

    @ApiModelProperty(value = "站点名称")
    private String stationName;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "最近上线时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    @ApiModelProperty(value = "在线时长")
    private BigInteger onLineHours;

    @ApiModelProperty(value = "离线时长")
    private BigInteger offLineHours;

}
