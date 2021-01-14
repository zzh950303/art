package com.ly.art.framework.shiro.exception;

public class PrincipalIdNullException extends RuntimeException {

    private static final long serialVersionUID = -1951849166056310369L;

    private static final String MESSAGE = "Principal Id shouldn't be null!";

    @SuppressWarnings("rawtypes")
    public PrincipalIdNullException(Class clazz, String idMethodName) {
        super(clazz + " id field: " + idMethodName + ", value is null\n" + MESSAGE);
    }
}
