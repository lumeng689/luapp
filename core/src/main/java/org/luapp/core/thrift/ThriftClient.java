package org.luapp.core.thrift;

import org.apache.commons.pool2.ObjectPool;
import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by lum on 2015/5/19.
 */
public class ThriftClient<T extends TServiceClient> implements Closeable {

    private final static Logger logger = LoggerFactory.getLogger(ThriftClient.class);
    private TServiceClient client;
    private ObjectPool<ThriftClient<T>> pool;
    private ServerInfo serverInfo;

    private boolean finish;

    public ThriftClient(TServiceClient client, ObjectPool<ThriftClient<T>> pool, ServerInfo serverInfo) {
        this.client = client;
        this.pool = pool;
        this.serverInfo = serverInfo;
    }

    @Override
    public void close() throws IOException {
        try {
            if (finish) {
                logger.info("return object[{}] to pool", this);
                finish = false;
                pool.returnObject(this);
            } else {
                logger.warn("work is not finish");
                ThriftHelper.closeClient(client);
                pool.invalidateObject(this);
            }
        } catch (Exception e) {
            logger.error("return object failed!", e);
            // 为防止连接泄露，此处强制关闭
            ThriftHelper.closeClient(client);
        }
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public T iFace() {
        return (T) client;
    }
}
