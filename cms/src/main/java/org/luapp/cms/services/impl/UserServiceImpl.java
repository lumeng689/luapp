package org.luapp.cms.services.impl;

import org.luapp.cms.services.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by lum on 2015/5/6.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        System.out.println("=====================");
    }
}
