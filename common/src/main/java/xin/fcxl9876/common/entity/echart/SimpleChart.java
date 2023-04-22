package xin.fcxl9876.common.entity.echart;

import lombok.Data;

import java.util.List;

/**
 * @author shaolay
 * @date 2023/3/13 15:26
 */
@Data
public class SimpleChart {

    List<String> xAxis;

    List<Series> series;

    List<String> legend;
}
