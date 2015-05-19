package org.luapp.core.thrift;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.PoolConfig;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by lum on 2015/5/19.
 */
public class ThriftClientPool<T extends TServiceClient> {
    private final static Logger logger = LoggerFactory.getLogger(ThriftClientPool.class);

    // 连接池配置
    private PoolConfiguration poolConfig;
    private ThriftClientFactory factory;
    private final GenericObjectPool<ThriftClient<T>> pool;
    private List<ServerInfo> servers;

    public ThriftClientPool(List<ServerInfo> servers, ThriftClientFactory factory) {
        this(servers, factory, new PoolConfiguration());
    }

    public ThriftClientPool(List<ServerInfo> servers, ThriftClientFactory factory,
                            final PoolConfiguration poolConfig) {
        if (servers == null || servers.size() == 0) {
            throw new IllegalArgumentException("servers is empty!");
        }

        if (factory == null) {
            throw new IllegalArgumentException("factory is empty!");
        }

        if (poolConfig == null) {
            throw new IllegalArgumentException("pool config is empty!");
        }

        this.servers = servers;
        this.factory = factory;
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestOnBorrow(true);
        this.poolConfig = poolConfig;

        this.pool = new GenericObjectPool(new BasePooledObjectFactory<ThriftClient<T>>() {

            @Override
            public ThriftClient<T> create() throws Exception {

                ServerInfo serverInfo = getRandomServer();
                TTransport transport = getTransport(serverInfo);

                try {
                    transport.open();
                } catch (TTransportException e) {
                    logger.info("transport open fail service: host={}, port={}",
                            serverInfo.getHost(), serverInfo.getPort());

                    if (poolConfig.isFailover()) {
                        int retry = 10;
                        while (retry > 0) {
                            serverInfo = getRandomServer();
                            transport = getTransport(serverInfo);

                            logger.info("try connect service: host={}, port={}",
                                    serverInfo.getHost(), serverInfo.getPort());
                            try {
                                transport.open();
                                break;
                            } catch (TTransportException ex) {
                                logger.info("transport open fail service: host={}, port={}",
                                        serverInfo.getHost(), serverInfo.getPort());
                            }
                            retry--;
                        }
                    } else {
                        logger.error("failed to connect ip:{}, port;{}", serverInfo.getHost(), serverInfo.getPort());
                        throw new RuntimeException(e);
                    }
                }

                ThriftClient<T> client = new ThriftClient<T>(factory.create(transport), pool, serverInfo);
                return client;
            }

            @Override
            public PooledObject<ThriftClient<T>> wrap(ThriftClient<T> obj) {
                return new DefaultPooledObject(obj);
            }

            @Override
            public boolean validateObject(PooledObject<ThriftClient<T>> p) {
                ThriftClient<T> client = p.getObject();

                if (!ThriftClientPool.this.servers.contains(client.getServerInfo())) {
                    logger.warn("remove old connections......");
                    try {
                        client.close();
                    } catch (IOException e) {
                        logger.error("exception detail:", e);
                    }
                    return false;
                }

                return super.validateObject(p);
            }

            @Override
            public void destroyObject(PooledObject<ThriftClient<T>> p) throws Exception {
                p.getObject().close();
                super.destroyObject(p);
            }
        }, poolConfig);
    }

    private ServerInfo getRandomServer() {
        if (servers.size() == 1) {
            return servers.get(0);
        } else {
            return servers.get(RandomUtils.nextInt(0, servers.size()));
        }
    }

    private TTransport getTransport(ServerInfo server) {
        if (server == null) {
            throw new IllegalArgumentException("client info cannot be ull");
        }

        TTransport transport = null;

        // 小于0 代表永不超时
        if (poolConfig.getTimeout() > 0) {
            transport = new TSocket(server.getHost(), server.getPort(), poolConfig.getTimeout());
        } else {
            transport = new TSocket(server.getHost(), server.getPort());
        }

        return transport;
    }

    public ThriftClient<T> getClient() throws Exception {
        return pool.borrowObject();
    }

    public <E> E iface() {
        final ThriftClient<T> client;

        try {
            client = pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AtomicBoolean returnToPool = new AtomicBoolean(false);
        return (E) Proxy.newProxyInstance(this.getClass().getClassLoader(), client.iFace().getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (returnToPool.get()) {
                            throw new IllegalStateException("Object returned via iface can only used once!");
                        }
                        boolean success = false;
                        try {
                            Object result = method.invoke(client.iFace(), args);
                            success = true;
                            return result;
                        } finally {
                            returnToPool.set(true);
                        }
                    }
                });
    }
//    private void removeFailServer(ServerInfo serverInfo) {
//        servers.remove(serverInfo);
//    }
}
