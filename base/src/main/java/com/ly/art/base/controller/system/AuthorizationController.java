package com.ly.art.base.controller.system;

import com.ly.art.base.consts.ResultCodeMessage;
import com.ly.art.base.controller.system.req.LoginReq;
import com.ly.art.base.srv.system.UserAccountService;
import com.ly.art.base.utils.AppBizExceptionUtil;
import com.ly.art.captcha.model.common.ResponseModel;
import com.ly.art.captcha.model.vo.CaptchaVO;
import com.ly.art.captcha.service.CaptchaService;
import com.ly.art.framework.base.AppBizException;
import com.ly.art.framework.base.controller.BaseController;
import com.ly.art.framework.base.resp.AjaxResult;
import com.ly.art.framework.log.Log;
import com.ly.art.framework.shiro.ShiroUtils;
import com.ly.art.framework.utils.ServletUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthorizationController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    @Lazy
    private CaptchaService captchaService;

    @RequestMapping(value = {"/loginPage"})
    public String loginView() {
        boolean isLogin = false;
        try {
            isLogin = ShiroUtils.isLogin();
        } catch (Exception e) {
            // EMPTY
        }

        if (isLogin) {
            return "redirect:/homePage";
        } else {
            if (ServletUtils.isAjaxRequest(httpServletRequest)) {
                return "redirect:/expireByAjax";
            } else {
                return "redirect:/login";
            }
        }
    }

    @RequestMapping(value = "/login")
    public ModelAndView loginPage() {
        return respMav("login");
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    @Log(desc = "登录")
    public AjaxResult signin(@Valid LoginReq request, BindingResult validResult) throws AppBizException {
        if (validResult.hasErrors()) {
            throw AppBizExceptionUtil.build(ResultCodeMessage.INVALID_PARAMETER);
        }

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(request.getCaptchaVerification());
        ResponseModel response = captchaService.verification(captchaVO);
        if (!response.isSuccess()) {
            throw AppBizExceptionUtil.build(ResultCodeMessage.CAPTCHA_VERIFY_ERROR);
        }

        userAccountService.login(request);

        return AjaxResult.success();
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public AjaxResult signout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (null != subject) {
                subject.logout();
            }
        } catch (Exception e) {
            // EMPTY
        }

        return AjaxResult.success();
    }

    @RequestMapping(value = "/expireByAjax")
    @ResponseBody
    public AjaxResult expireByAjax() {
        return AjaxResult.error(ResultCodeMessage.UNAUTHORIZED.getCode(), ResultCodeMessage.UNAUTHORIZED.getMessage());
    }

}
