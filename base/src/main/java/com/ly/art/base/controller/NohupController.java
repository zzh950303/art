package com.ly.art.base.controller;

import com.ly.art.framework.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NohupController extends BaseController {

    @RequestMapping(value = "/nohup.html", method = RequestMethod.GET)
    public ModelAndView nohup() {
        return respMav("nohup");
    }
}
