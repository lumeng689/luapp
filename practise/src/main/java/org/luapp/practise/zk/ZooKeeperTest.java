package org.luapp.practise.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2015/5/10.
 */
public class ZooKeeperTest
        implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }

    public void connect(String url) {
        try {
            zooKeeper = new ZooKeeper(url, 5000, new ZooKeeperTest());
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


    public void createEphemeralNodeSync(String path) {
        if (zooKeeper == null) {
            System.out.println("=============================");
            return;
        }
        try {
            String znode = zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("成功创建 znode: " + znode);
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("创建znode失败: " + path);
        } catch (InterruptedException e) {
            System.out.println("创建znode失败: " + path);
            e.printStackTrace();
        }

    }

    public void createEphemeralSeqNodeSync(String path) {
        if (zooKeeper == null) {
            System.out.println("=============================");
            return;
        }

        try {
            String znode = zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("成功创建 znode: " + znode);
        } catch (KeeperException e) {
            System.out.println("创建znode失败: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("创建znode失败" + path);
            e.printStackTrace();
        }
    }

    public void createEphemeralNodeAsync(String path) {
        if (zooKeeper == null) {
            System.out.println("=============================");
            return;
        }

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "I am context");
    }

    public void createEphemeralSeqNodeAsync(String path) {
        if (zooKeeper == null) {
            System.out.println("=============================");
            return;
        }

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallback(), "I am context");

    }

    private static class IStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
        }
    }

    public static void main(String[] args) {

        ZooKeeperTest test = new ZooKeeperTest();
        test.connect("127.0.0.1:2181");

        // 临时节点
        test.createEphemeralNodeSync("/zk-test-ephemeral-");
        // 临时顺序节点
        test.createEphemeralSeqNodeSync("/zk-test-ephemeral-");

        // 临时节点
        test.createEphemeralNodeAsync("/zk-test-ephemeral2-");
        // 临时顺序节点
        test.createEphemeralSeqNodeAsync("/zk-test-ephemeral2-");
    }
}

