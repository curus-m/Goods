package com.myServer.util;

import com.myServer.controller.ErogeController;

public class Logger {

	StringBuilder sb;
	String className;
	public Logger(Class<ErogeController> className) {
		this.className = className.getName();
	}
	public void Log(String content) {
		sb = new StringBuilder();
		sb.setLength(0);
		sb.append(className+" ");
		sb.append(content);
		System.out.println(sb.toString());
	}
	
}
