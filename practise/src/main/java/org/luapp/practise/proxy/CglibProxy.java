package org.luapp.practise.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lumeng on 2015/3/7.
 */
public class CglibProxy implements MethodInterceptor {

    public Object getProxy(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    /**
     * @param o           目标类实例
     * @param method      目标方法方法
     * @param args        参数
     * @param methodProxy 代理类实例
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before train:");
        methodProxy.invokeSuper(o, args);
        System.out.println("After train");
        return null;
    }
}
