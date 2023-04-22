package xin.fcxl9876.common.entity.vo;

import xin.fcxl9876.common.conf.JpaDto;
import lombok.Data;

/**
 * @author shaolay
 * @date 2023/3/23 12:37
 */
@Data
@JpaDto
public class Reasonable {

    private String projectCode;

    private String projectName;

    private String maxValue;

    private Integer maxEq;

    private String minValue;

    private Integer minEq;

}
