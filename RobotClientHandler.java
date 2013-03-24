package com.fellowrobots;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

public class RobotClientHandler extends MessageInbound {

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
				status = robo_base.isOnline(message.getRobotName()) ? "ok" : "error: offline";
				break;
			case TURN_RIGHT:
				Robot robot = robo_base.getRobot(message.getRobotName());
		    	if (robot != null) {
		    		robot.turnRight();
		    		status = "ok";
		    	} else {
		    		status = "error: offline";
		    	}
				break;
			default:
				status = "error: unsupported command";
				break;
		}
		getWsOutbound().writeTextMessage(
				CharBuffer.wrap("<message><status>" + status + "</status></message>"));
	}
}
