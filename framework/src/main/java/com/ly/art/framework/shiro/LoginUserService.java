package com.ly.art.framework.shiro;

import org.apache.shiro.authc.AuthenticationException;

public interface LoginUserService {

    /**
     * 获取管理员信息并且进行校验
     *
     * @param account
     * @return
     * @throws AuthenticationException
     */
    LoginUserInfo validateLoginUser(String account) throws AuthenticationException;

    /**
     * 校验密码是否正确
     *
     * @param account
     * @param plainPassword
     * @param salt
     * @param encPassword
     * @return
     */
    boolean validatePassword(String account, String plainPassword, String salt, String encPassword);

    /**
     * 获取授权信息
     *
     * @param account
     * @return
     */
    LoginUserInfo getAuthorizationInfo(String account);
}
