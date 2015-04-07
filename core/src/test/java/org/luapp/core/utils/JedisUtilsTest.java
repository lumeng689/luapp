package org.luapp.core.utils;

import org.assertj.core.api.Condition;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by lum on 2015/3/23.
 */
public class JedisUtilsTest {

    private final static Logger logger = LoggerFactory.getLogger(JedisUtilsTest.class);

    private JedisPool pool;
    private Jedis jedis;

    /**
     * 注意：该方法是静态的
     */
    @BeforeClass
    public static void beforeClass() {
        logger.info("before Class");
    }

    @Before
    public void setUp() {
        logger.info("setUp");
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, "127.0.0.1");
        jedis = pool.getResource();
    }

    @After
    public void tearDown() {
        logger.info("tearDown");
    }

    /**
     * 注意：该方法是静态的
     */
    @AfterClass
    public static void afterClass() {
        logger.info("after class!");
    }

    @Test
    public void testBasicString() {
        String key = "aa";
        final String value = "test";
        jedis.set(key, value);
        assertThat(jedis.get(key)).is(new Condition<String>() {
            @Override
            public boolean matches(String v) {
                return value.equals(v);
            }
        });

        jedis.append("aa", "-2");
        assertThat(jedis.get(key)).isEqualTo(value + "-2");

        jedis.del(key);
        assertThat(jedis.get(key)).isNull();

        /**
         * mset相当于
         * jedis.set("name","minxr");
         * jedis.set("jarorwar","闵晓荣");
         */
        jedis.mset("zhangsan", "张三", "lisi", "李四", "wangwu", "王五");
        assertThat(jedis.mget("zhangsan", "lisi", "wangwu")).containsExactly("张三", "李四", "王五");

        Map<String,String> user=new HashMap<String,String>();
        user.put("name","minxr");
        user.put("pwd","password");
        jedis.hmset("user",user);
        assertThat(jedis.hmget("user","pwd")).isEqualTo("password");

        assertThat(jedis.hlen("user")).isEqualTo(2);
        assertThat(jedis.exists("user")).isTrue();
        assertThat(jedis.hkeys("user")).containsExactly("name","pwd");
        assertThat(jedis.hvals("user")).containsExactly("minxr","password");

        jedis.hdel("user","pwd");
        assertThat(jedis.hmget("user","pwd")).isNull();

        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        assertThat(jedis.lrange("java framework",0,-1)).hasSize(3);

        //添加
        jedis.sadd("sname","minxr");
        jedis.sadd("sname","jarorwar");
        jedis.sadd("sname","cccc");
        jedis.sadd("sanme","noname");

        jedis.srem("sanme","noname");

        assertThat(jedis.smembers("sname")).hasSize(3);
        assertThat(jedis.sismember("sname","minxr")).isTrue();
        System.out.println(jedis.srandmember("sname"));
        assertThat(jedis.scard("sname")).isEqualTo(3);
        System.out.println(jedis.sort("sname"));

        //keys中传入的可以用通配符
        System.out.println(jedis.keys("*")); //返回当前库中所有的key  [sose, sanme, name, jarorwar, foo, sname, java framework, user, braand]
        System.out.println(jedis.keys("*name"));//返回的sname   [sname, name]
        System.out.println(jedis.del("sanmdde"));//删除key为sanmdde的对象  删除成功返回1 删除失败（或者不存在）返回 0
        System.out.println(jedis.ttl("sname"));//返回给定key的有效时间，如果是-1则表示永远有效
        jedis.setex("timekey", 10, "min");//通过此方法，可以指定key的存活（有效时间） 时间为秒
        try {
            Thread.sleep(5000);//睡眠5秒后，剩余时间将为<=5
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis.ttl("timekey"));   //输出结果为5
        jedis.setex("timekey", 1, "min");        //设为1后，下面再看剩余时间就是1了
        System.out.println(jedis.ttl("timekey"));  //输出结果为1
        System.out.println(jedis.exists("key"));//检查key是否存在
        System.out.println(jedis.rename("timekey","time"));
        System.out.println(jedis.get("timekey"));//因为移除，返回为null
        System.out.println(jedis.get("time")); //因为将timekey 重命名为time 所以可以取得值 min
    }
}
