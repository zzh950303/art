package com.ly.art.framework.base.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 *
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -7372562122912991207L;

    private boolean success;

    private String resultCode;

    private String message;

    public Map<String, Object> data;

    public Result() {
        this(false);
    }

    public Result(boolean success) {
        super();
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Result data(String name, Object value) {
        if (this.data == null) {
            this.data = new HashMap<String, Object>();
        }
        this.data.put(name, value);

        return this;
    }

}
