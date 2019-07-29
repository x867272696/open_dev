package com.htl.cloudmemory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.htl.cloudmemory.core.apigroup.APIGroup_api_1_0;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;
import com.htl.cloudmemory.util.PageDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理相关接口", description = "用户管理相关接口")
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@APIGroup_api_1_0
	@ApiOperation(value = "新增一个用户")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", paramType = "query", value = "用户名字", dataType = "String"),
		@ApiImplicitParam(name = "age", paramType = "query", value = "用户年龄", dataType = "int")
	})
	@GetMapping("/save")
	public String saveUser(String name, Integer age, BindingResult bindingResult){
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
	
	@Transactional
	@GetMapping("/findByName")
	public PageDetail findByName(String name, Integer pageNum, Integer pageSize){
		return userService.findByName(name, pageNum, pageSize);
	}
	
}
