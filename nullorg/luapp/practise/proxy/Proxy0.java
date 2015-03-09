package org.luapp.practise.proxy;

/**
 * Created by lumeng on 2015/3/7.
 */
public class Proxy0 implements org.luapp.practise.proxy.Movable{
 private org.luapp.practise.proxy.Movable m;
	public $Proxy0(org.luapp.practise.proxy.Movable m) {
		super();
		this.m = m;
}
    @Override
    public void move() {
        System.out.println("Before......");
        m.move();
        System.out.println("After......");
    }
}
