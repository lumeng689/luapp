package org.luapp.core.thrift.service;

import org.apache.thrift.TException;

/**
 * Created by lum on 2015/5/19.
 */
public class EchoServiceHandler implements EchoService.Iface {
    @Override
    public String echo(String message) throws TException {
        return "from " + message;
    }
}
