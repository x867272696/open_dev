package com.htl.cloudmemory.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.htl.cloudmemory.util.RequestUtils;

@RestController
@RequestMapping("/call")
public class CallController {
	
	@GetMapping("/get")
	public void get(String urlPath, String rootSep, String sep, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if(StringUtils.isEmpty(urlPath) || StringUtils.isEmpty(sep)){
			RequestUtils.writeStringToResponse(response, "您必须填写访问路径和路径分隔符.");
			return ;
		}
		
		String rootFilePath = urlPath.replaceAll(rootSep, "\\:/");
		String filePath = rootFilePath.replaceAll(sep, "/");
		
		File file = new File(filePath);
		if(filePath.endsWith(".mp4")){
			response.setContentType("video/mp4");
		}
		if(filePath.endsWith(".txt")){
			response.setContentType("text/plain");
		}
		
		response.setContentLengthLong(file.length());
		
		FileInputStream fis = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		byte[] fileByte = new byte[2000];
		while(fis.read(fileByte) != -1){
			os.write(fileByte);
		}
		os.flush();
		System.out.println(filePath);
		
	}
	
}
