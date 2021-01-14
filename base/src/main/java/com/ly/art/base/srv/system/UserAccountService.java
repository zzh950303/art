package com.ly.art.base.srv.system;

import com.ly.art.base.consts.ResultCodeMessage;
import com.ly.art.base.controller.system.req.LoginReq;
import com.ly.art.base.utils.AppBizExceptionUtil;
import com.ly.art.framework.base.AppBizException;
import com.ly.art.framework.shiro.CustomAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserAccountService {

    public boolean login(LoginReq request) throws AppBizException {
        String account = request.getAccount();
        String password = request.getPassword();

        // shiro登陆验证
        CustomAuthenticationToken token = new CustomAuthenticationToken(account, password, true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            // 验证是否登录成功
            if (currentUser.isAuthenticated()) {
                log.info("account:{} pass the login auth", account);
                return true;
            } else {
                token.clear();
                log.info("account:{} fail to login auth", account);
                return false;
            }
        } catch (UnknownAccountException e) {
            log.info("account:{} wasn't in the system", account);
            throw AppBizExceptionUtil.build(ResultCodeMessage.ACCOUNT_OR_PWD_ERROR);
        } catch (IncorrectCredentialsException ice) {
            log.info("account:{} password didn't match", account);
            throw AppBizExceptionUtil.build(ResultCodeMessage.ACCOUNT_OR_PWD_ERROR);
        } catch (DisabledAccountException dae) {
            log.info("account:{} disabled", account);
            throw AppBizExceptionUtil.build(ResultCodeMessage.ACCOUNT_INVAILD);
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            throw AppBizExceptionUtil.build(ResultCodeMessage.SERVER_ERROR);
        }
    }

}
