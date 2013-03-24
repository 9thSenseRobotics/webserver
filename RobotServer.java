package com.fellowrobots;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

public class RobotServer extends WebSocketServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1289722160418991112L;

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		return new RobotServerHandler();
	}

}
