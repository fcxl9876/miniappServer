package xin.fcxl9876.miniappserver.fw.enums;

/**
 * @author ljf
 * @ClassName DateUnit.java
 * @Description TODO
 * @createTime 2021年12月27日 09:33:00
 */
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
