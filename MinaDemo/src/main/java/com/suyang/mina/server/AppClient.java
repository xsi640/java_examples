package com.suyang.mina.server;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.suyang.mina.codec.CustomProtocolCodecFactory;
import com.suyang.mina.packet.Packet;

public class AppClient extends IoHandlerAdapter {

	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomProtocolCodecFactory()));
		connector.setHandler(new AppClient());
		
		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 12345);  
        ConnectFuture cf = connector.connect(addr);  
        try {  
            cf.awaitUninterruptibly();  
            
            Packet packet = new Packet();
            packet.setMessage("消息123");
            packet.setData("数据456".getBytes(Charset.forName("UTF-8")));
            cf.getSession().write(packet);  
            System.out.println("send message");  
        } catch (RuntimeIoException e) {  
            if (e.getCause() instanceof ConnectException) {  
                try {  
                    if (cf.isConnected()) {  
                        cf.getSession().closeNow();  
                    }  
                } catch (RuntimeIoException e1) {  
                }  
            }  
        }  
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("sessionIdle");
		try {
			releaseSession(session);
		} catch (RuntimeIoException e) {
		}
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("Receive Server message " + message);

		super.messageReceived(session, message);

		releaseSession(session);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("exceptionCaught");
		cause.printStackTrace();
		releaseSession(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
		super.messageSent(session, message);
	}

	private void releaseSession(IoSession session) {
		System.out.println("releaseSession");
		if (session.isConnected()) {
			session.closeOnFlush();
		}
	}
}
