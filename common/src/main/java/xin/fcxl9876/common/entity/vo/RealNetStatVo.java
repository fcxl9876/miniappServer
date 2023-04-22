package xin.fcxl9876.common.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 实时联网统计返回结果类
 *
 * @author shaolay
 * @date 2023/3/2 8:16
 */
@Data
@Accessors(chain = true)
public class RealNetStatVo {


    @ApiModelProperty(value = "站点总数", required = true)
    private int siteNum;

    @ApiModelProperty(value = "联网率", required = true)
    private String netWorkingRate;

    @ApiModelProperty(value = "在线数", required = true)
    private int onlineNum;

    @ApiModelProperty(value = "申请停运", required = true)
    private int stopOperationNum;

    @ApiModelProperty(value = "异常掉线", required = true)
    private int offlineNum;
}
