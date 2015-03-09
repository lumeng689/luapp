package org.luapp.practise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by lumeng on 2015/3/7.
 */
public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler h = new TimeHandler(car);
        Movable m= (Movable) Proxy.newProxyInstance(Test.class.getClassLoader(), car.getClass().getInterfaces(), h);
        m.move();

        // cglib代理
        CglibProxy proxy = new CglibProxy();
        Train t = (Train) proxy.getProxy(Train.class);
        t.move();

        /**
         * 1.声明一段源码
         * 2.编译源码
         * 3.装载类，产生对象
         */
    }
}
