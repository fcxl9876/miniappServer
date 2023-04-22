package xin.fcxl9876.common.enums;

public enum QuartzState {

    TRI_YEAR(1),

    TRI_MONTH(2),

    TRI_DAY(3),

    TRI_HOUR(4),

    TRI_SECOND(5),

    TRI_WEEK(6);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    QuartzState(Integer value) {
        this.value = value;
    }
}
