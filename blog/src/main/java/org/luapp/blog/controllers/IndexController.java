package org.luapp.blog.controllers;

import org.luapp.blog.domain.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/4/4.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("lang","zh");
        return "index";
    }

    @RequestMapping(value = "/menu")
    @ResponseBody
    public List<MenuItem> getMenu() {
        List<MenuItem> menu = new ArrayList<MenuItem>();

        menu.add(new MenuItem("主页", "/index"));
        menu.add(new MenuItem("用户", "/user"));
        menu.add(new MenuItem("设备", "/device"));
        menu.add(new MenuItem("策略", "/policy"));
        menu.add(new MenuItem("合规", "/rule"));
        menu.add(new MenuItem("应用", "/app"));
        menu.add(new MenuItem("统计", "/dashboard"));

        return menu;
    }
}
