package com.htl.cloudmemory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htl.cloudmemory.core.exception.SystemInfo;
import com.htl.cloudmemory.core.log.LogMark;
import com.htl.cloudmemory.core.log.LogVO;
import com.htl.cloudmemory.dao.GUserMapper;
import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.service.IUserService;
import com.htl.cloudmemory.util.PageDetail;

@Service
public class UserServiceImpl implements IUserService{
	
	public void aa(){
		
	}

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
	    if(pageNum == pageSize){
	    	throw new SystemInfo("系统错误");
	    }
	    return new PageDetail(rList);
	}
	
	@LogMark
	@Override
	public String test(){
		LogVO.setLog("发起一个新的日志");
		aa();
		return null;
	}

}
