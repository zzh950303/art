package com.ly.art.base.consts;

/**
 * The Enum ResultCodeMessage.
 */
public enum ResultCodeMessage {

    SUCC("0000", "成功"),

    FAIL("9999", "失败"),

    UNAUTHORIZED("OSS.401", "请登录后再试"),

    NO_PERMISSION("OSS.403", "无访问权限"),

    INVALID_PARAMETER("OSS.001", "参数错误"),

    /**
     * 1000-1009 Account
     */
    ACCOUNT_OR_PWD_ERROR("OSS.1000", "账号或密码错误"),

    VERIFY_CODE_ERROR("OSS.1001", "密码或验证码错误"),

    ACCOUNT_EXIST("OSS.1002", "账户已存在"),

    ACCOUNT_NOT_EXIST("OSS.1003", "账户不存在"),

    ACCOUNT_INVAILD("OSS.1004", "账户不可用"),

    CAPTCHA_VERIFY_ERROR("OSS.1005", "验证码验证失败，请重新验证"),

    SERVER_ERROR("OSS.500", "服务器异常，请稍后再试");

    private String code;
    private String message;

    ResultCodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
