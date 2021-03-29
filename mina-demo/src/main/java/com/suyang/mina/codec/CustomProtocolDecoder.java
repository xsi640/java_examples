package com.suyang.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.suyang.mina.packet.Packet;

public class CustomProtocolDecoder extends ProtocolDecoderAdapter {

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "context");

	public CustomProtocolDecoder() {
	}

	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		Context ctx = getContext(session);
		int matchCount = ctx.getMatchLength();// 目前已获取的数据
		int length = ctx.getLength();// 数据总长度
		IoBuffer buffer = ctx.getBuffer();// 数据存入buffer

		if (length == 0) {
			length = in.getInt();
			ctx.setLength(length);
			matchCount = in.remaining();
			ctx.setMatchLength(matchCount);
		} else {
			matchCount += in.remaining();
			ctx.setMatchLength(matchCount);
		}

		if (in.hasRemaining()) {// 如果buff中还有数据
			buffer.put(in);// 添加到保存数据的buffer中
			if (matchCount >= length) {// 如果已经发送的数据的长度>=目标数据的长度,则进行解码
				buffer.flip();
				if (buffer.remaining() > 0) {
					decodePacket(buffer, out, length);
				}
				ctx.reset();
			} else {
				ctx.setBuffer(buffer);
			}
		}
	}

	private void decodePacket(IoBuffer in, ProtocolDecoderOutput out, int size) {
		try {
			int msgLen = in.getInt();
			if (msgLen >= 0) {
				Packet packet = new Packet();
				if (msgLen > 0) {
					byte[] msgBytes = new byte[msgLen];
					in.get(msgBytes, 0, msgBytes.length);
					packet.setMessage(new String(msgBytes, "UTF-8"));
				}
				if (in.hasRemaining()) {
					byte[] data = new byte[in.remaining()];
					in.get(data, 0, data.length);
					packet.setData(data);
				}
				out.write(packet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Context getContext(IoSession session) {
		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx == null) {
			ctx = new Context();
			session.setAttribute(CONTEXT, ctx);
		}
		return ctx;
	}

	private class Context {
		private IoBuffer buffer;
		private int length = 0;
		private int matchLength = 0;

		public Context() {
			buffer = IoBuffer.allocate(1024).setAutoExpand(true);
		}

		public void setBuffer(IoBuffer buffer) {
			this.buffer = buffer;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public void setMatchLength(int matchLength) {
			this.matchLength = matchLength;
		}

		public IoBuffer getBuffer() {

			return buffer;
		}

		public int getLength() {
			return length;
		}

		public int getMatchLength() {
			return matchLength;
		}

		public void reset() {
			this.buffer.clear();
			this.length = 0;
			this.matchLength = 0;
		}
	}
}