package com.suyang.mina.server;

import java.nio.charset.Charset;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.suyang.mina.packet.Packet;

public class PackageHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (message == null)
			return;
		if (message instanceof Packet) {
			Packet packet = (Packet) message;
			String msg = packet.getMessage();
			byte[] data = packet.getData();
			String strData = new String(data, Charset.forName("UTF-8"));
			System.out.println("receive:");
			System.out.println("msg:" + msg);
			System.out.println("data:" + strData);
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		try {
			session.closeNow();
		} catch (Exception e) {
		}
	}
}
