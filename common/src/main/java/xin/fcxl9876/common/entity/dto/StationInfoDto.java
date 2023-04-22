package xin.fcxl9876.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.entity.vo.DataVo;
import xin.fcxl9876.common.entity.basEntity.Station;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

/**
 * 站点详细信息类
 *
 * @author shaolay
 * @date 2023/2/18 16:57
 */
@Data
public class StationInfoDto {

    private Station station;

    /**
     * 数据时间
     */
    @ApiModelProperty(value = "数据时间", required = true)
    private String dataTime;

    /**
     * 水质等级数字
     */
    @ApiModelProperty(value = "水质等级数字", required = true)
    private Integer waterQualityNum;

    /**
     * 水质等级
     */
    @ApiModelProperty(value = "水质等级", required = true)
    private String waterQuality;

    /**
     * 站点状态
     */
    @ApiModelProperty(value = "站点状态", required = true)
    private Integer stationState;

    /**
     * 首要污染物
     */
    @ApiModelProperty(value = "首要污染物", required = true)
    private String primaryPollutant;

    /**
     * 断面综合指数
     */
    @ApiModelProperty(value = "断面综合指数", required = true)
    private String compositeIndex;

    /**
     * 水质定性评价
     */
    @ApiModelProperty(value = "水质定性评价", required = true)
    private String waterCondition;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据", required = true)
    private Map<String, DataVo> dataMap;

}
