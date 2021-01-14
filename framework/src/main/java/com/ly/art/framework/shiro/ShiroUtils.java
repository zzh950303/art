package com.ly.art.framework.shiro;

import com.google.common.collect.Lists;
import com.ly.art.framework.shiro.consts.ShiroConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static boolean isLogin() {
        Subject us = SecurityUtils.getSubject();
        return us.isAuthenticated();
    }

    public static boolean checkPermission(String permissionCode) {
        if (StringUtils.isBlank(permissionCode)) {
            return true;
        }

        Subject currentUser = SecurityUtils.getSubject();
        boolean permitted = currentUser.isPermitted(permissionCode);
        return permitted;
    }

    public static boolean checkPermissionAny(String... permissionCodes) {
        if (permissionCodes == null || permissionCodes.length == 0) {
            return true;
        }

        Subject currentUser = SecurityUtils.getSubject();
        for (String permission : permissionCodes) {
            boolean permitted = currentUser.isPermitted(permission);// 判断是否有权限
            if (permitted) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkPermissionAll(String... permissionCodes) {
        if (permissionCodes == null || permissionCodes.length == 0) {
            return true;
        }

        Subject currentUser = SecurityUtils.getSubject();
        for (String permission : permissionCodes) {
            boolean permitted = currentUser.isPermitted(permission);// 判断是否有权限
            if (!permitted) {
                return false;
            }
        }

        return true;
    }

    public static LoginUserInfo getUserInfo() {
        LoginUserInfo loginUserInfo = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Session session = SecurityUtils.getSubject().getSession();
            loginUserInfo = (LoginUserInfo) session.getAttribute(ShiroConstants.SESSION_USER_KEY);
        }

        return loginUserInfo;
    }

    public static String getAccount() {
        String account = StringUtils.EMPTY;
        LoginUserInfo loginUserInfo = ShiroUtils.getUserInfo();
        if (null != loginUserInfo) {
            account = loginUserInfo.getAccount();
        }

        return account;
    }

    public static List<String> getRoleCodes() {
        LoginUserInfo loginUserInfo = ShiroUtils.getUserInfo();
        if (null != loginUserInfo) {
            return loginUserInfo.getRoleCodes();
        }

        return Lists.newArrayList();
    }
}
