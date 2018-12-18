package com.htl.cloudmemory.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htl.cloudmemory.controller.vo.BaseSqlVO;
import com.htl.cloudmemory.util.coderobot.bean.CommonResource;
import com.htl.cloudmemory.util.coderobot.core.TableIniter;
import com.htl.cloudmemory.util.coderobot.creater.SqlCreater;
import com.htl.cloudmemory.util.coderobot.util.ConnectionUtil;
import com.htl.cloudmemory.util.coderobot.util.StringFormat;

@RestController
@RequestMapping("/connection")
public class ConnectionController {
	
    @RequestMapping("/connect")
    public Object connect(String url, String userName, String password, HttpServletRequest request) {
    	url = new String(Base64.decode(url.getBytes()));
		if (StringFormat.strchks(url, "jdbc:", ":", 1).equalsIgnoreCase("mysql")) {
			CommonResource.databaseType = "mysql";
		}
		if (StringFormat.strchks(url, "jdbc:", ":", 1).equalsIgnoreCase("oracle")) {
			CommonResource.databaseType = "oracle";
		}
    	Connection conn = ConnectionUtil.getConnection(url, userName, password);
    	TableIniter.initAllTables(conn, userName);
        return CommonResource.tables;
    }
    
    @RequestMapping("/getBaseSql")
    public Object getBaseSql(String tableName, HttpServletRequest request){
    	String order = "map select " + tableName + " id";
    	SqlCreater sc = new SqlCreater();
    	String selectSql = sc.parse(order);
    	BaseSqlVO bsv = new BaseSqlVO();
    	bsv.setSelectSql(selectSql);
    	return bsv;
    }
	
}
