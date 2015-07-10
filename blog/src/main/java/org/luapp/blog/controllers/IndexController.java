package org.luapp.blog.controllers;

import org.luapp.blog.domain.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lumeng on 2015/4/4.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index() {
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

        return menu;
    }
}
