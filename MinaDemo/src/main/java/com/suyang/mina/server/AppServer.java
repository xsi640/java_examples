package com.suyang.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.suyang.mina.codec.CustomProtocolCodecFactory;

public class AppServer {
	public static void main(String[] args) throws IOException {
		// 创建一个非阻塞的Server端socket，用NIO
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 创建接受数据的过滤器
		// TextLineCodecFactory lineCodec = new
		// TextLineCodecFactory(Charset.forName("UTF-8"));
		// lineCodec.setDecoderMaxLineLength(10 * 1024 * 1024);
		// lineCodec.setEncoderMaxLineLength(10 * 1024 * 1024);
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomProtocolCodecFactory()));
		// 指定业务逻辑处理器
		acceptor.setHandler(new PackageHandler());
		// 设置端口号
		acceptor.setDefaultLocalAddress(new InetSocketAddress(12345));
		acceptor.bind(); // 启动监听
	}
}
