package org.luapp.practise.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.luapp.practise.proto.ResponseProto;

/**
 * Created by lum on 2015/5/26.
 */
public class LdapClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                            }
                            // 负责通过4字节Header指定的Body长度将消息切割
                            p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
                            // 负责将frameDecoder处理后的完整的一条消息的protobuf字节码转成Student对象
                            p.addLast("protobufDecoder", new ProtobufDecoder(ResponseProto.Response.getDefaultInstance()));
                            // 负责将写入的字节码加上4字节Header前缀来指定Body长度
                            p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                            // 负责将Response对象转成protobuf字节码
                            p.addLast("protobufEncoder", new ProtobufEncoder());
                            p.addLast(new LdapClientHandler());
                        }
                    });

            // Start the connection attempt.
            b.connect(HOST, PORT).sync().channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}