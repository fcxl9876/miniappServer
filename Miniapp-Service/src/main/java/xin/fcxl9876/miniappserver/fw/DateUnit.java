package xin.fcxl9876.miniappserver.fw;

public enum DateUnit {
    YEAR("year"),
    MONTH("month"),
    DAY("day"),
    HOUR("hour");

    private String unit;

    DateUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }
}
