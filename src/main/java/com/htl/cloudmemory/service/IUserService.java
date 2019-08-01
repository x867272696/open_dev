package com.htl.cloudmemory.service;

import com.htl.cloudmemory.entity.GUser;
import com.htl.cloudmemory.util.PageDetail;

public interface IUserService {
	
	void save(GUser user);

	PageDetail findByName(String name, Integer pageNum, Integer pageSize);

	String test();
	
}
