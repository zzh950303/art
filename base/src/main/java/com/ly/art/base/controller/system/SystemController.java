package com.ly.art.base.controller.system;

import com.ly.art.base.consts.ResultCodeMessage;
import com.ly.art.framework.base.controller.BaseController;
import com.ly.art.framework.base.resp.AjaxResult;
import com.ly.art.framework.base.resp.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController extends BaseController {

    @RequestMapping(value = "/ie")
    public ModelAndView ie() {
        return respMav("ie");
    }

    @RequestMapping(value = "/homePage")
    public ModelAndView homeView() {
        Result result = new Result();
        return respMav("index", result);
    }

    @RequestMapping(value = "/noPermission")
    public String noPermission() {
        return "redirect:/forbidden";
    }

    @RequestMapping(value = "/forbidden")
    public ModelAndView forbidden() {
        return respMav("forbidden");
    }

    @RequestMapping(value = "/noPermissionByAjax")
    @ResponseBody
    public AjaxResult noPermissionByAjax() {
        return AjaxResult.error(ResultCodeMessage.NO_PERMISSION.getCode(),
                ResultCodeMessage.NO_PERMISSION.getMessage());
    }
}
