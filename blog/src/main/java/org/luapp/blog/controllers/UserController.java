package org.luapp.blog.controllers;

import org.luapp.blog.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/7/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @InitBinder
    public void initBinder() {

    }

    @RequestMapping(value = "/")
    public PageResp getNodes(PageRequest pageRequest) {

        PageResp pr = new PageResp();

        pr.setCode(PageResp.RET_SUCCESS);
        pr.setMessage("OK");

        List<User> users = new ArrayList<>();
        users.add(new User(1L, "name1", "job1", "desc1"));

        PageResp.Pages pages = new PageResp.Pages<User>();
        pages.setCurPage(1);
        pages.setPerPage(15);
        pages.setTotalPages(100);
        pages.setTotalRecords(1000);
        pages.setRecords(users);
        pr.setPages(pages);

        return pr;
    }

    @RequestMapping(value = "/getNodes")
    public List<TreeNode> getNodes(@RequestParam(defaultValue = "1") String id, @RequestParam(defaultValue = "") String n, String lv) {

        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nodes.add(new TreeNode(id + "." + i, n + "_" + i, true));
        }

        return nodes;
    }

    /**
     * 获取用户数据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ObjResp<User> getUser(@PathVariable("id") long id) {
        ObjResp resp = new ObjResp<User>();
        resp.setCode(BaseResp.RET_SUCCESS);
        resp.setMessage("添加成功！");
        resp.setData(new User(1, "张三", "CEO", "公司BOSS"));

        return resp;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT})
    public BaseResp addUser(User user) {
        BaseResp resp = new BaseResp();
        resp.setCode(BaseResp.RET_SUCCESS);
        resp.setMessage("添加成功！");

        return resp;
    }

    /**
     * 编辑用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/edit", method = {RequestMethod.POST, RequestMethod.DELETE})
    public BaseResp editUser(@PathVariable("id") long id) {
        BaseResp resp = new BaseResp();
        resp.setCode(BaseResp.RET_SUCCESS);
        resp.setMessage("编辑成功！");

        return resp;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    public BaseResp viewUser(@PathVariable("id") long id) {
        BaseResp resp = new BaseResp();
        resp.setCode(BaseResp.RET_SUCCESS);
        resp.setMessage("删除成功！");

        return resp;
    }
}
