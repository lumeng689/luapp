package org.luapp.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by lum on 2015/5/11.
 */
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-mvc.xml"})
public class AbstractContextControllerTests {

    @Autowired
    protected WebApplicationContext wac;
}
