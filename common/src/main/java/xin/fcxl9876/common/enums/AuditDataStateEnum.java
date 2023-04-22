package xin.fcxl9876.common.enums;

import lombok.Getter;

/**
 * @author shaolay
 * @date 2023/3/27 16:17
 */
public enum AuditDataStateEnum {

    WAIT_STATE(0, "未审核"),
    EFFECTIVE_STATE(1, "有效"),
    INVALID_STATE(2, "无效"),
    DUBIOUS_STATE(3, "存疑"),
    REVIEWED(4, "存疑"),
    DIFFERENCE(5, "差异");

    @Getter
    private final int code;
    @Getter
    private final String reason;

    private AuditDataStateEnum(int statusCode, String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
    }

    public static String getReason(int code){
        String reason = "";
        for (AuditDataStateEnum value : AuditDataStateEnum.values()) {
            if (value.code == code){
                reason = value.reason;
            }
        }
        return reason;
    }
}
