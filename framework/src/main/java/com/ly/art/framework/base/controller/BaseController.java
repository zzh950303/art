package com.ly.art.framework.base.controller;

import com.ly.art.framework.base.req.BaseReq;
import com.ly.art.framework.base.resp.Result;
import com.ly.art.framework.properties.PropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller基类
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Autowired
    protected HttpServletResponse httpServletResponse;

    @Autowired
    protected PropertyConfig propertyConfig;

    protected ModelAndView respMav(String viewName) {
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("siteName", propertyConfig.getSiteName());
        mav.addObject("siteCopyright", propertyConfig.getSiteCopyright());
        mav.addObject("assetDomain", propertyConfig.getAssetDomain());
        mav.addObject("assetVersion", propertyConfig.getAssetVersion());
        return mav;
    }

    protected ModelAndView respMav(String viewName, Result result) {
        ModelAndView mav = respMav(viewName);
        mav.addObject("result", result);
        return mav;
    }

    protected ModelAndView respMav(String viewName, Result result, BaseReq request) {
        ModelAndView mav = respMav(viewName, result);
        mav.addObject("request", request);
        mav.addObject("condition", request.buildCondition());

        return mav;
    }

}
