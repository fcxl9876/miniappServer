package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.entity.dto.StationInfoDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author shaolay
 * @date 2023/3/31 14:40
 */
@Data
@Accessors(chain = true)
public class PolluteCalendar {

    private String time;

    private String stationCode;

    private String stationName;

    private List<Map<String, Object>> monthStat;

    private Map<String, Object> yearStat;

    private Map<String, Map<String, Object>> dataList;
}
