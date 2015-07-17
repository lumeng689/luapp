package org.luapp.blog.controllers;

import org.luapp.blog.domain.MenuItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lum on 2015/7/16.
 */
@Controller
public class AppController {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object uploadApp(HttpServletRequest request) {

        if (!(request instanceof MultipartHttpServletRequest)) {
        }

         return null;
    }
}
