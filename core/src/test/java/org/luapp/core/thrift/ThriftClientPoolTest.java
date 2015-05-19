package org.luapp.core.thrift;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.junit.*;
import org.luapp.core.thrift.service.EchoService;
import org.luapp.core.thrift.service.EchoServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Repeat;

import java.util.Arrays;
import java.util.List;


/**
 * Created by lum on 2015/5/19.
 */
public class ThriftClientPoolTest {

    private static Logger logger = LoggerFactory.getLogger(ThriftClientPoolTest.class);

//    @Rule
//    public RepeatRule repeatRule = new RepeatRule();


    private static TThreadPoolServer server = null;

    private static ThriftClientPool<EchoService.Client> pool;
    private static final int port = 9092;

    @BeforeClass
    public static void setUp() {
        // 启动thrift
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            Args processor = new
                    Args(serverTransport).
                    inputTransportFactory(new TFramedTransport.Factory()).
                    outputTransportFactory(new TFramedTransport.Factory()).
                    processor(new EchoService.Processor(new EchoServiceHandler()));
            server = new TThreadPoolServer(processor);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    logger.info("thrift server starting......");
                    server.serve();
                }
            }).start();
            logger.info("thrift server is running......");
        } catch (TTransportException e) {
            logger.error("Exception detail :", e);
        }


        List<ServerInfo> serverList = Arrays.asList(new ServerInfo("127.0.0.1", port));
        PoolConfiguration config = new PoolConfiguration();
        config.setFailover(true);
        config.setTimeout(1000);

        pool = new ThriftClientPool(serverList, new ThriftClientFactory() {
            @Override
            public TServiceClient create(TTransport transport) {
                return new EchoService.Client(new TBinaryProtocol(new TFramedTransport(transport)));
            }
        }, config);
    }

    @AfterClass
    public static void tearDown() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    @Repeat(10000)
    public void testReuseIFace() throws TException {
        EchoService.Iface iface = pool.iface();
        logger.info(iface.echo("aaaa" + RandomUtils.nextInt()));
    }

    @Test
    public void testEcho() {

    }
}
