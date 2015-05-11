package org.luapp.cms.controllers;

import org.javasimon.aop.Monitored;
import org.luapp.cms.services.HelloService;
import org.luapp.cms.services.UserService;
import org.luapp.cms.utils.MessageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lumeng on 2015/1/24.
 */
@Controller
public class HomeController {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private HelloService helloService;

    @Monitored
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        log.info(messageHelper.getMessage("hello"));
        log.info("22222222222222");
        userService.test();
        helloService.sayHello();

        model.addAttribute("user", "11111");
        return "index";
    }

    /**
     * 保存文章后转向查看页面
     *
     * @param ra
     * @return
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public String saveAndView(RedirectAttributes ra) {

        // 返回的url不要直接拼接参数，因为spring会对url进行缓存，容易导致内存泄露
        ra.addAttribute("articleId", "111");
        return "redirect:/view?articleId={articleId}";
    }
}
