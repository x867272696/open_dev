package com.htl.cloudmemory.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.htl.cloudmemory.core.log.LogMark;
import com.htl.cloudmemory.core.log.LogVO;
import com.htl.cloudmemory.service.IUserService;

@RestController
public class IndexController {
	
	@Autowired
	private IUserService userService;
	
    @RequestMapping("/index")
    public ModelAndView index(HashMap<String, Object> map, HttpServletRequest request) {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/test")
    public Integer test(@NotEmpty(message = "sql不能为空") String sql) {
    	userService.test();
    	return 2;
    }
	
}
