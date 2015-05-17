package org.luapp.practise.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/5/16.
 */
public class ZkClientTest {

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);

        System.out.println("Zookeeper session established!");

        String path = "/zk-test/c1";
        zkClient.createPersistent(path, true);

        List<String> children = zkClient.getChildren(path);

        for (String child : children) {
            System.out.print(child + "  ");
        }

        System.out.println();

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath + "路径下子节点有变化！");
                for (String child : children) {
                    System.out.print(child + "  ");
                }

                System.out.println();
            }
        });

        zkClient.createPersistent(path + "/t1");

        System.out.println(zkClient.exists(path + "/t1") ? "t1 节点已存在" : "t1 节点不存在");

        TimeUnit.SECONDS.sleep(1);

        zkClient.delete(path + "/t1");

        TimeUnit.SECONDS.sleep(1);

        zkClient.createPersistent(path + "/t2");
        zkClient.subscribeDataChanges(path + "/t2", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("节点：" + dataPath + "数据发生变化，新值为：" + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("节点: " + dataPath + " 已经被删除！");
            }
        });

        System.out.println(zkClient.readData(path + "/t2") == null ? "null" : zkClient.readData(path + "/t2").toString());

        zkClient.writeData(path + "/t2", "123");

        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(path + "/t2");

        // 清理原有数据
        zkClient.deleteRecursive(path);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
