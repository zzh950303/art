package com.ly.art.framework.advice;

import com.ly.art.framework.base.AppBizException;
import com.ly.art.framework.base.AppRtException;
import com.ly.art.framework.base.resp.AjaxResult;
import com.ly.art.framework.properties.PropertyConfig;
import com.ly.art.framework.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class WebExceptionHandle {

    private final static Logger LOG = LoggerFactory.getLogger(WebExceptionHandle.class);

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected PropertyConfig propertyConfig;

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object handleNotSupportedException(HttpRequestMethodNotSupportedException e) {
        LOG.warn("HttpRequestMethodNotSupportedException occurred", e);
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            return AjaxResult.error("不支持 " + e.getMethod() + " 请求");
        } else {
            return this.errorView(e);
        }
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntimeException(RuntimeException e) {
        LOG.error("RuntimeException occurred", e);
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            return AjaxResult.error(e.getMessage());
        } else {
            return this.errorView(e);
        }
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        LOG.warn("BindException occurred", e);
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            String message = e.getAllErrors().get(0).getDefaultMessage();
            return AjaxResult.error(message);
        } else {
            return this.errorView(e);
        }
    }

    @ExceptionHandler(AppBizException.class)
    public AjaxResult handleAppBizException(AppBizException ex) {
        LOG.warn("AppBizException occurred", ex);
        Locale locale = RequestContextUtils.getLocale(httpServletRequest);

        String code = ex.getCode();
        String msg = StringUtils.EMPTY;
        try {
            msg = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e1) {
            // EMPTY
        }
        if (StringUtils.isBlank(msg)) {
            msg = ex.getMessage();
        }

        return AjaxResult.error(code, msg);
    }

    @ExceptionHandler(AppRtException.class)
    public Object handleAppRtException(AppRtException e) {
        LOG.error("AppRtException occurred", e);
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            return AjaxResult.error(e.getMessage());
        } else {
            return this.errorView(e);
        }
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        LOG.error("Exception occurred", e);
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            return AjaxResult.error(e.getMessage());
        } else {
            return this.errorView(e);
        }
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public ModelAndView authorizationException() {
        if (ServletUtils.isAjaxRequest(httpServletRequest)) {
            return new ModelAndView("redirect:/noPermissionByAjax");
        } else {
            return new ModelAndView("redirect:/forbidden");
        }
    }

    private ModelAndView errorView(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("assetDomain", propertyConfig.getAssetDomain());
        mav.addObject("exception", e);

        return mav;
    }

}
