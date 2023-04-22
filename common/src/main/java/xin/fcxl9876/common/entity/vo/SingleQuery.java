package xin.fcxl9876.common.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import xin.fcxl9876.common.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author shaolay
 * @date 2023/3/14 8:38
 */
@Data
public class SingleQuery {

    @ApiModelProperty(value = "站点编码", required = true)
    private String stationCode;

    @ApiModelProperty(value = "开始时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "分页大小", required = true)
    private Integer pageSize;

    public void setEndTime(String endTime){
        if (StringUtils.isBlank(endTime)) {
            this.endTime = new Date();
        } else {
            endTime += " 23:59:59";
            this.endTime = DateUtil.stringToDate(endTime, DateUtil.DATE_FULL_STR);
        }
    }

}
