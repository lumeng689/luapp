package org.luapp.practise.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2015/5/10.
 */
public class ZookeeperConstructorUsageSimple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructorUsageSimple());
            System.out.println(zooKeeper.getState());

            connectedSemaphore.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

        System.out.println("连接zookeeper成功！");
    }
}
