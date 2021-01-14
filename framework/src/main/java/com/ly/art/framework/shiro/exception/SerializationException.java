package com.ly.art.framework.shiro.exception;

public class SerializationException extends Exception {

    private static final long serialVersionUID = 4902657527235692295L;

    public SerializationException(String msg) {
        super(msg);
    }

    public SerializationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
