package com.fellowrobots;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

public class RobotServerHandler extends MessageInbound {

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		// not implemented
	}

	@Override
	protected void onTextMessage(CharBuffer text) throws IOException {
		Message message = new Message(text.toString());
		RoboBase robo_base = RoboBase.getInstance();
		String status;
		switch (message.getCommand()) {
			case LOGIN:
				if (robo_base.login(message.getRobotName(), this)) {
					status = "ok";
				} else {
					status = "error: duplicate login";
				}
				break;
			case LOGOUT:
				robo_base.logout(message.getRobotName());
				status = "ok";
				break;
			default:
				status = "error: unsupported command";
				break;
		}
		getWsOutbound().writeTextMessage(
				CharBuffer.wrap("<message><status>" + status + "</status></message>"));
	}
	
	public void sendCommand(String command) {
		try {
			getWsOutbound().writeTextMessage(
						CharBuffer.wrap("<message><command>" + command + "</command></message>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
