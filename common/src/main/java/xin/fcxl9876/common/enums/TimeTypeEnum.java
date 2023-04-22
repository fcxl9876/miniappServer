package xin.fcxl9876.common.enums;

import lombok.Data;

/**
 * @author shaolay
 * @date 2023/2/22 15:37
 */
public enum TimeTypeEnum {
    hour("hour"),
    day("day"),
    week("week"),
    month("month"),
    quarter("quarter"),
    year("year"),
    realTime("realTime");

    private String timeType;

    TimeTypeEnum(String timeType) {
        this.timeType = timeType;
    }

    public String getTimeType() {
        return timeType;
    }

}
