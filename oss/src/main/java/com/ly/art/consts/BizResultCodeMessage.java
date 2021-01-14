package com.ly.art.consts;

/**
 * The Enum ResultCodeMessage.
 */
public enum BizResultCodeMessage {

    SUCC("0000", "成功"),

    FAIL("9999", "失败"),

    INVALID_PARAMETER("0001", "参数错误"),

    UPLOAD_IMG_ERROR("0002", "上传图片失败"),

    UPLOAD_IMG_SIZE_LIMIT("0003", "图片太大"),

    ;

    private String code;
    private String message;

    BizResultCodeMessage(String code, String message) {
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
