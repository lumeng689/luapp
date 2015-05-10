package org.luapp.cms.services.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.luapp.cms.entities.TUser;
import org.luapp.cms.repositories.UserDao;
import org.luapp.cms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by lum on 2015/5/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private LoadingCache<Long, TUser> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.SECONDS).build(new CacheLoader<Long, TUser>() {
        @Override
        public TUser load(Long key) throws Exception {
            return getUser(key);
        }
    });

    @Override
    public void test() {
        System.out.println("=====================");
    }

    /**
     * 查找特定的用户
     *
     * @param id
     * @return
     */
    public TUser getUser(Long id) {
        return userDao.get(id);
    }
}
