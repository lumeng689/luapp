package org.luapp.core.thrift;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by lum on 2015/5/19.
 */
public class PoolConfiguration extends GenericObjectPoolConfig{
    private int timeout;
    private boolean failover;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isFailover() {
        return failover;
    }

    public void setFailover(boolean failover) {
        this.failover = failover;
    }
}
