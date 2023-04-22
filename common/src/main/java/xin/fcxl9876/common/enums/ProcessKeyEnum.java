package xin.fcxl9876.common.enums;

/**
 * @author shaolay
 * @date 2023/2/22 15:37
 */
public enum ProcessKeyEnum implements BaseEnum{
    testProcess("testProcess"),
    ;

    private String code;

    ProcessKeyEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
