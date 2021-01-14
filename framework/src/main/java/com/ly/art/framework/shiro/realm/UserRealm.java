package com.ly.art.framework.shiro.realm;

import com.ly.art.framework.shiro.CustomAuthenticationToken;
import com.ly.art.framework.shiro.LoginUserInfo;
import com.ly.art.framework.shiro.LoginUserService;
import com.ly.art.framework.shiro.consts.ShiroConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginUserService loginUserService;

    public void setLoginUserService(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    /**
     * 授权查询回调函数
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        LoginUserInfo loginUserInfo = (LoginUserInfo) session.getAttribute(ShiroConstants.SESSION_USER_KEY);
        if (loginUserInfo == null) {
            loginUserInfo = (LoginUserInfo) principals.getPrimaryPrincipal();
        }

        // 获取授权信息
        loginUserInfo = loginUserService.getAuthorizationInfo(loginUserInfo.getAccount());
        session.setAttribute(ShiroConstants.SESSION_USER_KEY, loginUserInfo);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (!CollectionUtils.isEmpty(loginUserInfo.getRoles())) {
            for (String role : loginUserInfo.getRoles()) {// 角色
                authorizationInfo.addRole(role);
            }
        }

        if (!CollectionUtils.isEmpty(loginUserInfo.getPermissions())) {
            authorizationInfo.addStringPermission("default");// 默认权限标识
            for (String permission : loginUserInfo.getPermissions()) {// 权限
                authorizationInfo.addStringPermission(permission);
            }
        }

        return authorizationInfo;
    }

    /**
     * 认证回调函数
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = token.getPrincipal().toString();
        LoginUserInfo loginUserInfo = loginUserService.validateLoginUser(account);

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroConstants.SESSION_USER_KEY, loginUserInfo);

        ByteSource credentialsSalt = null;
        if (StringUtils.isNotBlank(loginUserInfo.getSalt())) {
            credentialsSalt = ByteSource.Util.bytes(loginUserInfo.getSalt());
        }
        return new SimpleAuthenticationInfo(loginUserInfo, loginUserInfo.getLoginPwd(), credentialsSalt,
                this.getName());
    }

    /**
     * 设定密码校验算法
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new RetryLimitSimpleCredentialsMatcher());
    }

    class RetryLimitSimpleCredentialsMatcher extends SimpleCredentialsMatcher {
        @Override
        public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
            CustomAuthenticationToken token = (CustomAuthenticationToken) authcToken;
            boolean validatePwd = token.isValidatePwd();
            if (validatePwd) {
                String userName = token.getUsername();
                String plainPassword = String.valueOf(token.getPassword());

                SimpleAuthenticationInfo authenticationInfo = (SimpleAuthenticationInfo) info;
                String encPassword = String.valueOf(authenticationInfo.getCredentials());
                String salt = StringUtils.EMPTY;
                if (authenticationInfo.getCredentialsSalt() != null) {
                    salt = CodecSupport.toString(authenticationInfo.getCredentialsSalt().getBytes());
                }

                return loginUserService.validatePassword(userName, plainPassword, salt, encPassword);
            } else {
                return true;
            }
        }
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String account) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setAccount(account);
        SimplePrincipalCollection principals = new SimplePrincipalCollection(loginUserInfo, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
