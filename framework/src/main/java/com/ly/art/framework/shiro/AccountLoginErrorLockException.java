package com.ly.art.framework.shiro;

import org.apache.shiro.authc.AccountException;

/**
 * 登录错误次数太多被锁定
 */
public class AccountLoginErrorLockException extends AccountException {

    private static final long serialVersionUID = 2909745933144545546L;

    public AccountLoginErrorLockException() {
        super();
    }

    public AccountLoginErrorLockException(String message) {
        super(message);
    }

    public AccountLoginErrorLockException(Throwable cause) {
        super(cause);
    }

    public AccountLoginErrorLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
