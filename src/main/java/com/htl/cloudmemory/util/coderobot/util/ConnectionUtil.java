package com.htl.cloudmemory.util.coderobot.util;  

import java.sql.Connection;  
import java.sql.DriverManager;
import java.util.Properties;
import com.htl.cloudmemory.util.coderobot.bean.CommonResource;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class ConnectionUtil {
	
	//mysql驱动定义
    public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    
	//oracle驱动定义
    public static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";

    public static Connection getConnection(String dbUrl,String dbUser,String dbPsw){  
          
        Connection conn = null;
        try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPsw);//连接数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
        CommonResource.connection = conn;
        return conn;
    }
    
    //获取连接  
    public static Connection getOracleConnection(String url,String user,String pwd){  
        Connection conn = null;  
        try {  
            Properties props = new Properties();  
            props.put("remarksReporting", "true");  
            props.put("user", user);  
            props.put("password", pwd);  
            Class.forName(ORACLEDRIVER);  
            conn = DriverManager.getConnection(url, props);  
        } catch (Exception e) {
        	e.printStackTrace();
        }
        CommonResource.connection = conn;
        return conn;  
    }
  
}  