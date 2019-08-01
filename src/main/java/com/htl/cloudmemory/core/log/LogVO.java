package com.htl.cloudmemory.core.log;
public class LogVO {
	
	public static ThreadLocal<String> log = new ThreadLocal<String>();

	public static String getLog() {
		return log.get();
	}

	public static void setLog(String log) {
		LogVO.log.set(log);
	}

}
