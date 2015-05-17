package org.luapp.practise.zk;

import com.sun.jmx.snmp.tasks.ThreadService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/5/16.
 */
public class CuratorTest {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",5000,3000,retryPolicy);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        String path = "/zk-test/c2";

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, "init".getBytes());

        Stat stat = new Stat();

        System.out.println("节点信息：========");
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println(stat.getEphemeralOwner());
        System.out.println(stat.getVersion());

        client.setData().withVersion(stat.getVersion()).forPath(path, "init222".getBytes());

        System.out.println("节点信息：========");
        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
        System.out.println(stat.getEphemeralOwner());
        System.out.println(stat.getVersion());

        String path2 = "/zk-test/c3";
        CountDownLatch semaphore = new CountDownLatch(2);
        ExecutorService tp = Executors.newFixedThreadPool(2);

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("event[code: " + event.getResultCode() + ", type: " + event.getType() + "]");
            }
        }, tp).forPath(path2, "init".getBytes());

        client.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(path);

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
