package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.conf.JpaDto;
import lombok.Data;

/**
 * 站点因子频率
 *
 * @author shaolay
 * @date 2023/3/2 14:55
 */
@Data
@JpaDto
public class StationProjectFre {

    private String stationCode;

    private String projectCode;

    private String monitorFrequency;


}
