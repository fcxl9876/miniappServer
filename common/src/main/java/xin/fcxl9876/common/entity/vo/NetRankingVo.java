package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.conf.JpaDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;

/**
 * 联网率排名
 *
 * @author shaolay
 * @date 2023/3/6 8:47
 */
@Data
@JpaDto
public class NetRankingVo {

    @ApiModelProperty(value = "站点编码", required = true)
    private String stationCode;

    @ApiModelProperty(value = "站名称码", required = true)
    private String stationName;

    @ApiModelProperty(value = "联网率", required = true)
    private Double netRanking;

    private BigInteger sortNum;
}
