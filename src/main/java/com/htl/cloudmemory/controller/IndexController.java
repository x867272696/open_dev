package com.htl.cloudmemory.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.htl.cloudmemory.controller.vo.BaseSqlVO;

@RestController
public class IndexController {
	
    @RequestMapping("/index")
    public ModelAndView index(HashMap<String, Object> map, HttpServletRequest request) {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/test")
    public String test(@Validated BaseSqlVO bsv, BindingResult bindingResult) {
        return new String("index");
    }
	
}
