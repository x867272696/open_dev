package com.htl.cloudmemory.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class RequestUtils {
	
	public static void writeStringToResponse(HttpServletResponse response, String content){
		if(response == null || content == null){
			return ;
		}
		response.setHeader("Content-Type","text/html;charset=UTF-8");
		try {
			OutputStream os = response.getOutputStream();
			os.write(content.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
