package com.htl.cloudmemory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/save")
	public String saveUser(String name, Integer age){
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
	
}
