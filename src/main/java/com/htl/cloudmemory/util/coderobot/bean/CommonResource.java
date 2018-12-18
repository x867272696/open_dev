package com.htl.cloudmemory.util.coderobot.bean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class CommonResource {
	//本数据库中所有的表
	public static List<Table> tables=new ArrayList<Table>();
	//从表名到对应list的index的map
	public static Map<String,Integer> columnMapper=new HashMap<String,Integer>();
	public static List<Table> compareTables = new ArrayList<Table>();
	public static Map<String,Integer> compareColumnMapper = new HashMap<String,Integer>();
	//命令工厂,该集合记录了所有从命令到解析器的映射
	public static Map<String,String> orderFactory=new HashMap<String,String>();
	//模型工厂,该集合记录了所有模型库
	public static Map<String,Model> modelFactiory=new HashMap<String,Model>();
	//数据库类型,目前支持msyql与oracle
	public static String databaseType;
	//数据库连接
	public static Connection connection = null;
}