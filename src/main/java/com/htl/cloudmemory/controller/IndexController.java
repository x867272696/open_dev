package com.htl.cloudmemory.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
    @RequestMapping("/index")
    public ModelAndView index(HashMap<String, Object> map, HttpServletRequest request) {
        return new ModelAndView("index");
    }
	
}
