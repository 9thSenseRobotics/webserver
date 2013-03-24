package com.fellowrobots;

public class Robot {
	private String name;
	private RobotServerHandler handler;
	
	public Robot(String name, RobotServerHandler handler) {
		this.name = name;
		this.handler = handler;
	}
	
	public String getName() {
		return name;
	}
	
	public void turnRight() {
		handler.sendCommand("right");
	}
}
