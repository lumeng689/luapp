package org.luapp.blog.controllers;

import org.luapp.blog.domain.TreeNode;
import org.luapp.blog.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/")
    @ResponseBody
    public List<User> getNodes() {
        List<User> users = new ArrayList<>();

        return users;
    }

    @RequestMapping(value = "/getNodes")
    @ResponseBody
    public List<TreeNode> getNodes(@RequestParam(defaultValue = "-1") String id, @RequestParam(defaultValue = "") String n, String lv) {

        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nodes.add(new TreeNode(id + "." + i, n + "_" + i, true));
        }

        return nodes;
    }
}
