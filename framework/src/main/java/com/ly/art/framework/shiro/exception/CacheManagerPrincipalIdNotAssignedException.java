package com.ly.art.framework.shiro.exception;

public class CacheManagerPrincipalIdNotAssignedException extends RuntimeException {

    private static final long serialVersionUID = -2860827385589146010L;

    private static final String MESSAGE = "CacheManager didn't assign Principal Id field name!";

    public CacheManagerPrincipalIdNotAssignedException() {
        super(MESSAGE);
    }
}
