package xin.fcxl9876.common.enums;

/**
 * 质控分析内因子枚举
 *
 * @author Cold
 */
public enum QualityProjectEnum {

    IMN("w01019", "高锰酸盐指数", "mg/L", "≤±10%"), // 高锰酸盐指数
    NH3("w21003", "氨氮", "mg/L", "≤±10%"), // 氨氮
    TP("w21011", "总磷", "mg/L", "≤±10%"),  // 总磷
    TN("w21001", "总氮", "mg/L", "≤±10%"),  // 总氮
    F("w21017", "氟化物", "mg/L", "≤±10%"),  // 氟化物
    PH("w01001", "pH", "无量纲", "≤±0.5pH"),  // PH
    WT("w01010", "水温", "℃", ""),  // 水温
    DO("w01009", "溶解氧", "mg/L", "≤±10%"),  // 溶解氧
    EC("w01014", "电导率", "μS/cm", "≤±5%"),  // 电导率
    TUB("w01003", "浊度", "NTU", "≤±10%"),  // 浊度
    CHL("w01016", "叶绿素", "mg/L", "±20%"), // 叶绿素
    COD("w01018", "化学需氧量", "mg/L", "≤±5mg/L"), // 化学需氧量
    TOX("w01023", "生物毒性", "无量纲", ""); // 生物毒性

    private QualityProjectEnum(String code, String name, String unit, String technicalRequirement) {
        this.code = code;
        this.name = name;
        this.unit = unit;
        this.technicalRequirement = technicalRequirement;
    }

    private String code;
    private String name;
    private String unit;
    private String technicalRequirement;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getTechnicalRequirement() {
        return technicalRequirement;
    }

    public static String getName(String code) {
        for (QualityProjectEnum qp : QualityProjectEnum.values()) {
            if (qp.getCode().equals(code)) {
                return qp.getName();
            }
        }
        return "";
    }

    public static String getTechnicalRequirement(String code) {
        for (QualityProjectEnum qp : QualityProjectEnum.values()) {
            if (qp.getCode().equals(code)) {
                return qp.getTechnicalRequirement();
            }
        }
        return "";
    }

    public static String getUnit(String code) {
        for (QualityProjectEnum qp : QualityProjectEnum.values()) {
            if (qp.getCode().equals(code)) {
                return qp.getUnit();
            }
        }
        return "";
    }
}
