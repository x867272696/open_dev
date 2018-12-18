package com.htl.cloudmemory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htl.cloudmemory.dao.GUserMapper;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private GUserMapper userMapper;
	
	public void save(GUser user) {
		userMapper.insert(user);
		System.out.println("success");
	}

}
