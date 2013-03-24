package com.fellowrobots;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Message {
	public enum Command {
		UNKNOWN,
		LOGIN,
		LOGOUT,
		TURN_RIGHT,
	}
	
	private long message_id;
	private String robot_name;
	private Command command;
	
	public Message(String message_xml) {
		message_id = 0;
		robot_name = "";
		command = Command.UNKNOWN;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
	        InputSource is = new InputSource(new StringReader(message_xml));
	        Document message = builder.parse(is);
	        message_id = Long.parseLong(getTagValue(message, "id"));
	        robot_name = getTagValue(message, "robotname");
	        command = parseCommand(getTagValue(message, "command"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long getMessageId() {
		return message_id;
	}
	
	public String getRobotName() {
		return robot_name;
	}
	
	public Command getCommand() {
		return command;
	}
	
	private String getTagValue(Document message, String tag_name) {
		NodeList nodes = message.getElementsByTagName(tag_name);
		if (nodes.getLength() == 0) return "";
		return nodes.item(0).getFirstChild().getNodeValue().trim();
	}
	
	private Command parseCommand(String command_text) {
		if (command_text.equals("login")) return Command.LOGIN;
		else if (command_text.equals("logout")) return Command.LOGOUT;
		else if (command_text.equals("right")) return Command.TURN_RIGHT;
		else return Command.UNKNOWN;
	}
}
