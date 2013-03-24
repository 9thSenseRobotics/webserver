package com.fellowrobots;

import java.util.Dictionary;
import java.util.Hashtable;

public class RoboBase {
	private static RoboBase instance = null;
	
	public static synchronized RoboBase getInstance() {
		if (instance == null) instance = new RoboBase();
		return instance;
	}
	
	private Dictionary<String, Robot> robots;  // name -> robot
	
	private RoboBase() {
		robots = new Hashtable<String, Robot>();
	}
	
	public synchronized boolean login(String robot_name, RobotServerHandler handler) {
		if (isOnline(robot_name)) {
			return false;
		}
		robots.put(robot_name, new Robot(robot_name, handler));
		return true;
	}
	
	public synchronized void logout(String robot_name) {
		robots.remove(robot_name);
	}
	
	public synchronized boolean isOnline(String robot_name) {
		return robots.get(robot_name) != null;
	}
	
	public synchronized Robot getRobot(String robot_name) {
		return robots.get(robot_name);
	}
}
