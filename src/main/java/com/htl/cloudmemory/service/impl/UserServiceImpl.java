package com.htl.cloudmemory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htl.cloudmemory.dao.GUserMapper;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;
import com.htl.cloudmemory.util.PageDetail;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private GUserMapper userMapper;
	
	public void save(GUser user) {
		userMapper.insert(user);
		System.out.println("success");
	}
	
	@Override
	public PageDetail findByName(String name, Integer pageNum, Integer pageSize){
	    PageHelper.startPage(pageNum, pageSize);
	    List<GUser> userList = userMapper.selectByName(name);
	    PageInfo<GUser> rList = new PageInfo<>(userList);
	    return new PageDetail(rList);
	}

}
