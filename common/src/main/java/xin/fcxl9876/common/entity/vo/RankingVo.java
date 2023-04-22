package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.conf.JpaDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;

/**
 * 水质排名
 *
 * @author shaolay
 * @date 2023/3/3 16:05
 */
@Data
@JpaDto
public class RankingVo {

    @ApiModelProperty(value = "站点编码", required = true)
    private String stationCode;

    @ApiModelProperty(value = "站点名称", required = true)
    private String stationName;

    @ApiModelProperty(value = "水质等级", required = true)
    private String waterQuality;

    @ApiModelProperty(value = "水质等级数字", required = true)
    private Integer waterQualityNum;

    @ApiModelProperty(value = "同比水质等级", required = true)
    private String waterQualityYoy;

    @ApiModelProperty(value = "同比水质等级数字", required = true)
    private Integer waterQualityNumYoy;

    private BigInteger sortNum;

}
