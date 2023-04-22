package xin.fcxl9876.common.enums;

/**
 * @author ZY
 * @date 2018年4月8日
 */
public enum ServiceReturnEnum {

        /** 系统错误 **/
        ERROR("9999", "系统走神了,请稍后再试"),
        /** 成功 **/
        SUCCESS("0", "成功"),
        /** 登录失败 **/
        LOGIN_FAIL("1000", "登录失败,账号不存在或密码错误"),
        /** session失效 **/
        SESSION_FAILURE("1001", "session失效了, 请重新登录"),
        /** 用户无权限 **/
        NO_PERMISSION("1002", "您没有操作此功能的权限"),
        /** 用户未登录 **/
        NO_LOGIN("1003", "您没有登录,请先登录");
        private ServiceReturnEnum(String code, String msg) {
                this.code = code;
                this.msg = msg;
        }

        private String code;
        private String msg;

        public String getCode() {
                return this.code;
        }

        public String getMsg() {
                return this.msg;
        }
}
