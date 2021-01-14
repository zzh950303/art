package com.ly.art.shiro;

import com.ly.art.framework.shiro.LoginUserInfo;
import com.ly.art.framework.shiro.LoginUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Override
    public LoginUserInfo validateLoginUser(String account) throws AuthenticationException {
        LoginUserInfo loginUserInfo = null;
        // TODO 判断用户是否存在，不存在 throw new UnknownAccountException();
        // TODO 判断用户状态，不可用 throw new DisabledAccountException();

        loginUserInfo = new LoginUserInfo();
        loginUserInfo.setAccount("admin");
        loginUserInfo.setNickname("管理员");
        loginUserInfo.setLoginPwd("123456asdfgh");
        loginUserInfo.setSalt("asdfgh");
        loginUserInfo.setMerchantNo("0000001");

        return loginUserInfo;
    }

    @Override
    public boolean validatePassword(String account, String plainPassword, String salt, String encPassword) {
        // TODO
        String encPwd = plainPassword + salt;

        return encPwd.equals(encPassword);
    }

    @Override
    public LoginUserInfo getAuthorizationInfo(String account) {
        // TODO
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setAccount("admin");
        loginUserInfo.setNickname("管理员");
        loginUserInfo.setLoginPwd("qwertyuiop");
        loginUserInfo.setSalt("asdfgh");
        loginUserInfo.setMerchantNo("0000001");

        return loginUserInfo;
    }

}
