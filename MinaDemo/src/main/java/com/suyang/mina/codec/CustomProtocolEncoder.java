package com.suyang.mina.codec;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.suyang.mina.packet.Packet;


public class CustomProtocolEncoder extends ProtocolEncoderAdapter {

	public CustomProtocolEncoder() {
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		if (!(message instanceof Packet)) {
			throw new IllegalArgumentException("message type is not packet!!!");
		}
		Packet p = (Packet) message;
		IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true);
		byte[] byteMessage = new byte[0];
		if(!StringUtils.isEmpty(p.getMessage())) {
			byteMessage = p.getMessage().getBytes("UTF-8");
		}
		buffer.putInt(4 + byteMessage.length + (p.getData() == null ? 0 : p.getData().length));
		buffer.putInt(byteMessage.length);
		if(byteMessage != null && byteMessage.length > 0) {
			buffer.put(byteMessage, 0, byteMessage.length);
		}
		if(p.getData() != null && p.getData().length > 0) {
			buffer.put(p.getData(),0,p.getData().length);
		}
		buffer.flip();
		out.write(buffer);
	}
}
