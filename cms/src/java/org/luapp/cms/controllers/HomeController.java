package org.luapp.cms.controllers;

import org.luapp.cms.utils.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lumeng on 2015/1/24.
 */
@Controller
public class HomeController {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private MessageHelper messageHelper;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        log.info(messageHelper.getMessage("hello"));
        log.info("22222222222222");

        model.addAttribute("user", "11111");
        return "index";
    }
}
