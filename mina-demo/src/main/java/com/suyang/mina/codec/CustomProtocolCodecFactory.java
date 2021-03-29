package com.suyang.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CustomProtocolCodecFactory implements ProtocolCodecFactory {
	private final CustomProtocolDecoder decoder = new CustomProtocolDecoder();
	private final CustomProtocolEncoder encoder = new CustomProtocolEncoder();

	public CustomProtocolCodecFactory() {
	}

	public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
		return encoder;
	}
}