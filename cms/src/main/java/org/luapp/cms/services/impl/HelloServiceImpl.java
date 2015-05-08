package org.luapp.cms.services.impl;

import org.luapp.cms.script.Messager;
import org.luapp.cms.services.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lum on 2015/5/7.
 */
@Service
public class HelloServiceImpl implements HelloService {

    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Autowired
    private Messager messager;

    @Override
    public void sayHello() {
        logger.info("=========={}", messager.getMessage());
    }
}
