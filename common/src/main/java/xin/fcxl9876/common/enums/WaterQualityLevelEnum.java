package xin.fcxl9876.common.enums;

public enum WaterQualityLevelEnum {

    ZERO(0, "-", ""),
    ONE(1, "I类", "优"),
    TWO(2, "II类", "优"),
    THREE(3, "III类", "良好"),
    FOUR(4, "IV类", "轻度污染"),
    FIVE(5, "V类", "中度污染"),
    VFIVE(6, "劣V类", "重度污染");

    private final Integer code;
    private final String reason;

    private final String waterCondition;

    private WaterQualityLevelEnum(Integer code, String reason, String waterCondition) {
        this.code = code;
        this.reason = reason;
        this.waterCondition = waterCondition;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getReasonPhrase() {
        return this.reason;
    }

    public String getWaterCondition() {
        return this.waterCondition;
    }


    public static WaterQualityLevelEnum getDesc(String reason) {
        for (WaterQualityLevelEnum ls : WaterQualityLevelEnum.values()) {
            if (ls.getReasonPhrase().equals(reason)) {
                return ls;
            }
        }
        return null;
    }

    public static WaterQualityLevelEnum getDesc(Integer code) {
        for (WaterQualityLevelEnum ls : WaterQualityLevelEnum.values()) {
            if (ls.getCode() == code) {
                return ls;
            }
        }
        return null;
    }

    /**
     * 将数字水质等级转换成中文描述
     *
     * @param waterQualityLevel
     * @return 水质等级中文描述
     */
    public static String NumToChinese(Integer waterQualityLevel) {
        String waterQualityLevelStr = "-";
        WaterQualityLevelEnum waterQualityLevelEnum = getDesc(waterQualityLevel);
        if (waterQualityLevelEnum != null) {
            waterQualityLevelStr = waterQualityLevelEnum.getReasonPhrase();
        }
        return waterQualityLevelStr;
    }

    /**
     * 将水质等级中文描述转换成数字水质等级
     *
     * @param waterQualityLevelStr
     * @return 数字水质等级
     */
    public static Integer ChineseToNum(String waterQualityLevelStr) {
        Integer waterQualityLevel = 0;
        WaterQualityLevelEnum waterQualityLevelEnum = getDesc(waterQualityLevelStr);
        if (waterQualityLevelEnum != null) {
            waterQualityLevel = waterQualityLevelEnum.getCode();
        }
        return waterQualityLevel;
    }

    public static boolean judge(int level, WaterQualityLevelEnum... levelEnum){
        boolean flag = false;

        for (WaterQualityLevelEnum waterQualityLevelEnum : levelEnum) {
            if (waterQualityLevelEnum.getCode() == level){
                flag = true;
            }
        }

        return flag;
    }

}

