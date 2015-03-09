package org.luapp.practise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lumeng on 2015/3/7.
 */
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @param proxy  被代理对象
     * @param method 被代理方法
     * @param args   方法参数
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before:");
        method.invoke(target, args);
        System.out.println("After");
        return null;
    }
}
