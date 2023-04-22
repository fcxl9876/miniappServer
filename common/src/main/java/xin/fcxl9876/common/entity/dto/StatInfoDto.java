package xin.fcxl9876.common.entity.dto;

import xin.fcxl9876.common.conf.JpaDto;
import lombok.Data;

/**
 * @author shaolay
 * @date 2023/2/28 13:30
 */
@Data
@JpaDto
public class StatInfoDto {

    private String stationCode;

    private String dataTime;

    private String projectCode;

    private String dataYear;

    private Integer dataWeek;

    private Integer quarter;
}
