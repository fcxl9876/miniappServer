package xin.fcxl9876.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author shaolay
 * @date 2023/3/27 14:50
 */

public enum AuditStateEnum {

    UNAUDITED(0, "未审核"),
    AUTO_AUDIT(1, "系统已审核"),
    RESIDENT_REVIEW(2, "驻站已审核"),
    MAIN_REVIEW(3, "总站已审核"),
    TIMEOUT_AUTO(4, "超时完成");

    @Getter
    private final int code;
    @Getter
    private final String reason;

    private AuditStateEnum(int statusCode, String reasonPhrase) {
        this.code = statusCode;
        this.reason = reasonPhrase;
    }

    public static String getReason(int code){
        String reason = "";
        for (AuditStateEnum value : AuditStateEnum.values()) {
            if (value.code == code){
                reason = value.reason;
            }
        }
        return reason;
    }
}
