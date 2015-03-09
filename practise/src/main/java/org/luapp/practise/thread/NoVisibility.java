package org.luapp.practise.thread;

/**
 * Created by lumeng on 2015/3/7.
 */
public class NoVisibility {

    private static boolean ready = false;
    private static int number = 0;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                System.out.println("not ready");
                Thread.yield();// 让出cpu执行权
            }

            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
