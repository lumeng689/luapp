package org.luapp.core.filters;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * Created by lum on 2015/4/30.
 */
public class GZipFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(GZipFilter.class);

    private boolean enable = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        enable = BooleanUtils.toBoolean(filterConfig.getInitParameter("enable"), "true", "false");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!enable) {
            chain.doFilter(request, response);
        } else {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest req = (HttpServletRequest) request;
                String ae = req.getHeader("accept-encoding");
                if (ae != null && ae.indexOf("gzip") != -1) {
                    GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(
                            (HttpServletResponse) response);
                    chain.doFilter(req, wrappedResponse);
                    wrappedResponse.finishResponse();
                    return;
                }
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private static class GZIPResponseWrapper extends HttpServletResponseWrapper {

        /**
         * HttpServletResponse
         */
        protected HttpServletResponse origResponse = null;
        /**
         * ServletOutputStream
         */
        protected ServletOutputStream stream = null;
        /**
         * PrintWriter
         */
        protected PrintWriter writer = null;

        /**
         * 通过HttpServletResponse构造GZIPResponseWrapper
         *
         * @param response HttpServletResponse
         */
        public GZIPResponseWrapper(HttpServletResponse response) {
            super(response);
            origResponse = response;
        }


        /**
         * 获取ServletOutputStream
         *
         * @return ServletOutputStream
         * @throws IOException IO异常
         */
        public ServletOutputStream createOutputStream() throws IOException {
            return (new GZIPResponseStream(origResponse));
        }

        /**
         * 结束Response
         */
        public void finishResponse() {
            try {
                if (writer != null) {
                    writer.close();
                } else {
                    if (stream != null) {
                        stream.close();
                    }
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        /**
         * 强制写出
         *
         * @throws IOException IO异常
         */
        public void flushBuffer() throws IOException {
            stream.flush();
        }

        /**
         * 获取ServletOutputStream对象
         *
         * @return ServletOutputStream对象
         * @throws IOException IO异常
         */
        public ServletOutputStream getOutputStream() throws IOException {
            if (writer != null) {
                throw new IllegalStateException("getWriter() has already been called!");
            }

            if (stream == null) {
                stream = createOutputStream();
            }
            return (stream);
        }

        /**
         * 获得PrintWriter对象
         *
         * @return PrintWriter对象
         * @throws IOException IO异常
         */
        public PrintWriter getWriter() throws IOException {
            if (writer != null) {
                return (writer);
            }

            if (stream != null) {
                throw new IllegalStateException("getOutputStream() has already been called!");
            }

            stream = createOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
            return (writer);
        }
    }

    private static class GZIPResponseStream extends ServletOutputStream {
        /**
         * ByteArrayOutputStream
         */
        protected ByteArrayOutputStream baos = null;
        /**
         * GZIPOutputStream
         */
        protected GZIPOutputStream gzipstream = null;
        /**
         * 关闭标识
         */
        protected boolean closed = false;
        /**
         * HttpServletResponse
         */
        protected HttpServletResponse response = null;
        /**
         * ServletOutputStream
         */
        protected ServletOutputStream output = null;

        /**
         * 构造方法
         *
         * @param response HttpServletResponse
         * @throws IOException IO异常
         */

        public GZIPResponseStream(HttpServletResponse response) throws IOException {
            super();
            closed = false;
            this.response = response;
            this.output = response.getOutputStream();
            baos = new ByteArrayOutputStream();
            gzipstream = new GZIPOutputStream(baos);
        }

        /**
         * 关闭所有流文件
         *
         * @throws IOException IO异常
         */
        public void close() throws IOException {
            if (closed) {
                throw new IOException("This output stream has already been closed");
            }
            gzipstream.finish();

            byte[] bytes = baos.toByteArray();

            response.addHeader("Content-Length", Integer.toString(bytes.length));
            response.addHeader("Content-Encoding", "gzip");
            output.write(bytes);
            output.flush();
            output.close();
            closed = true;
        }

        /**
         * 将流内内容强制写出
         *
         * @throws IOException IO异常
         */
        public void flush() throws IOException {
            if (closed) {
                throw new IOException("Cannot flush a closed output stream");
            }
            gzipstream.flush();
        }

        /**
         * 将整数写入流内
         *
         * @param b 整数
         * @throws IOException IO异常
         */
        public void write(int b) throws IOException {
            if (closed) {
                throw new IOException("Cannot write to a closed output stream");
            }
            gzipstream.write((byte) b);
        }

        /**
         * 将字节数组写入流内
         *
         * @param b 字节数组
         * @throws IOException IO异常
         */
        public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
        }

        /**
         * 将字节数组中的指定长度写入流内
         *
         * @param b   字节数组
         * @param off 开始地址
         * @param len 要写入的长度
         * @throws IOException IO异常
         */
        public void write(byte[] b, int off, int len) throws IOException {
            if (closed) {
                throw new IOException("Cannot write to a closed output stream");
            }
            gzipstream.write(b, off, len);
        }

        /**
         * 判断是否关闭了
         *
         * @return 是否关闭了标识
         */
        public boolean closed() {
            return (this.closed);
        }

        /**
         * reset
         */
        public void reset() {
            // noop
        }

    }
}
