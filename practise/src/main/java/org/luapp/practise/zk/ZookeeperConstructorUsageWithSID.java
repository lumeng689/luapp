package org.luapp.practise.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/5/10.
 */
public class ZookeeperConstructorUsageWithSID implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            System.out.println("success");
        }
    }

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructorUsageWithSID());
            System.out.println(zooKeeper.getState());
            connectedSemaphore.await();

            long sessionId = zooKeeper.getSessionId();
            byte[] passwd = zooKeeper.getSessionPasswd();

            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructorUsageWithSID(), sessionId, "test".getBytes());
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new ZookeeperConstructorUsageWithSID(), sessionId, passwd);

            TimeUnit.SECONDS.sleep(5);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
