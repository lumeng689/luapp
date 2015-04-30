package org.luapp.core.aop;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 *自定义拦截器，拦截方法调用的信息
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TraceInterceptor.class);

    @Override
    protected void writeToLog(Log logger, String message) {
        logger.debug(message);
    }

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null) {
            logger.debug(message, ex);
        } else {
            logger.debug(message);
        }
    }

    @Override
    protected boolean isLogEnabled(Log logger) {
        return super.isLogEnabled(logger);
    }
}
