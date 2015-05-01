package org.luapp.practise.thread;

/**
 * Created by lumeng on 2015/4/12.
 */
public class SynchronizedDemo {

    // 共享变量
    private boolean ready = false;
    private int result =0;
    private int number = 1;

    public void write() {
        ready = true;
        number = 2;
    }

    public void read() {
        if(ready) {
           number = number * 3;
        }

        System.out.println("num = " + number);
    }

    private class ReadWriteThread extends Thread {

        private boolean flag;

        private ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                write();
            } else {
                read();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo syncDemo = new SynchronizedDemo();
        syncDemo.new ReadWriteThread(true).start();
        syncDemo.new ReadWriteThread(false).start();
    }
}
