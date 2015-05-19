package org.luapp.core.thrift;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.transport.TTransport;

/**
 * Created by lum on 2015/5/19.
 */
public interface ThriftClientFactory {
    /**
     * 创建客户端
     *
     * @param transport
     * @return
     */
    public TServiceClient create(TTransport transport);
}
