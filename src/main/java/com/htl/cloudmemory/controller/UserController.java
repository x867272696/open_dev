package com.htl.cloudmemory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codingapi.tx.annotation.TxTransaction;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;
import com.htl.cloudmemory.util.PageDetail;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/save")
	public String saveUser(String name, Integer age){
		logger.info("执行新增操作.");
		GUser user = new GUser();
		user.setName(name);
		user.setAge(age);
		userService.save(user);
		return "success";
	}
	
	@GetMapping("/getName")
	public String getName(String name){
		System.out.println("{" + "name:" + "\"" + name + "\"" + "}");
		return "{" + "\"name\":" + "\"" + name + "\"" + "}";
	}
	
	@TxTransaction(isStart=true)
	@Transactional
	@GetMapping("/findByName")
	public PageDetail findByName(String name, Integer pageNum, Integer pageSize){
		return userService.findByName(name, pageNum, pageSize);
	}
	
}
