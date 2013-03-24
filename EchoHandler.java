package com.fellowrobots;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

public class EchoHandler extends MessageInbound {

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		// not supported
	}

	@Override
	protected void onTextMessage(CharBuffer text) throws IOException {
		getWsOutbound().writeTextMessage(text);
	}

}
