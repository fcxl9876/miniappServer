package xin.fcxl9876.common.constant;

/**
 * 最基础的StringKeyValue
 * @author lijianhua
 */
public enum BaseError implements ErrorCode {

    /**
     * 操作成功
     */
    OK_KEY_VALUE("200","操作成功!"),

    BI_STENCH_EXCEPTION("9010","BI-STENCH服务接口运行异常"),
    BI_QUARTZ_EXCEPTION("9009","BI-QUARTZ服务接口运行异常"),
    BI_RSA_KEY_GEN_ERROR("9100","RSA秘钥生成失败"),
    AUTH_INTERFACE_ERROR("9200","权限系统接口异常："),
    BI_AUTH_SIGN_EMPTY("9101","接口签名sign为空！"),
    BI_MESSAGE_CONVERSION_EXCEPTION("9102","参数类型转换错误"),
    BI_ARGUMENT_NOT_VALID("9103","参数校验不通过"),

    QUERY_DATA_EMPTY("10001","查询数据为空"),

    DELETE_DATA_NOT_EXISTS("11001","删除的记录不存在！"),

    INVALID_TOKEN("1001","无效的token"),
    INVALID_SIGN("1003","无效的签名"),
    DECRYPT_ERROR("1004","解密失败"),

    USER_PASSWORD_ERROR("1002","用户名密码错误"),

    USER_ROLE_EMPTY("1005","用户未授权角色！"),

    THIRD_API_INVOKE_ERROR("3000","第三方接口调用异常！"),

    THIRD_USER_UNAUTHORIZED("3001","第三方用户未授权！"),



    /**
     * 内部服务器错误
     */
    INNER_ERROR("500","内部服务器错误!"),

    /**
     * 远程服务异常
     */
    FEIGN_ERROR("520","远程服务异常"),

    /**
     * 远程服务失败
     */
    API_ERROR("521","远程服务失败"),

    /**
     * 对象不存在
     */
    NOT_EXIST("601","对象不存在"),

    /**
     * 操作失败
     */
    OPT_FALID_VALUE("604","操作失败"),

    /**
     * 参数验证失败
     */
    VALID_ERROR("602","参数验证失败"),

    /**
     * 唯一性验证失败
     */
    UNIQUE_ERROR("603","唯一性验证失败"),

    PARAMS_EMPTY("604","必要参数不能为空"),


    UNKNOWN_ACCOUNT("201","未知账号"),


    /**
     * excel导入
     */
    EXCEL_IMPORT_ERROR("505","Excel导入失败"),
    EXCEL_IMPORT_SUCCESS("200","Excel导入成功"),

    /**
     * 未知异常
     */
    ERROR_KEY_VALUE("999","未知异常");




    /**
     * 编码
     */
    private String code;

    private String msg;


    BaseError(String code, String msg) {
        this.code = code;
        this.msg=msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {

        return this.msg;
    }

}
