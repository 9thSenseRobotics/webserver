package com.fellowrobots;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class RobotClient extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3194640680668688560L;

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		return new RobotClientHandler();
	}

}
