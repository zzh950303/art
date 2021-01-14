package com.ly.art.framework.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomAuthenticationToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 7772110097543735442L;

    /**
     * 是否验证密码
     */
    private boolean validatePwd;

    public CustomAuthenticationToken(final String username, final String password, boolean validatePwd) {
        super(username, password);
        this.validatePwd = validatePwd;
    }

    public boolean isValidatePwd() {
        return validatePwd;
    }

    public void setValidatePwd(boolean validatePwd) {
        this.validatePwd = validatePwd;
    }

}
