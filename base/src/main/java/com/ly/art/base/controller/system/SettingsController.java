package com.ly.art.base.controller.system;

import com.ly.art.framework.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettingsController extends BaseController {

    @RequestMapping(value = "/system/settings/display", method = RequestMethod.GET)
    public ModelAndView display() {
        return respMav("system/settings/display");
    }

}
