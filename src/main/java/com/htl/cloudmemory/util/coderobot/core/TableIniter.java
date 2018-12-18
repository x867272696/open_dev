package com.htl.cloudmemory.util.coderobot.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.htl.cloudmemory.util.coderobot.bean.CommonResource;
import com.htl.cloudmemory.util.coderobot.bean.Table;
import com.htl.cloudmemory.util.coderobot.util.StringFormat;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0 TODO 初始化指定数据库中的所有的表模型
 */
public class TableIniter{

	public static void initAllTables(Connection conn, String userName) {
		CommonResource.tables = new ArrayList<Table>();
		try {
			try {
				DatabaseMetaData dbmd = conn.getMetaData();
				ResultSet resultSet = null;
				if(CommonResource.databaseType.equals("mysql")){
					resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
				}
				else if(CommonResource.databaseType.equals("oracle")){
					resultSet = dbmd.getTables("null", userName.toUpperCase(), "%", new String[] { "TABLE" });
				}
				int tableIndex = 0;
				while (resultSet.next()) {
					String tableName = resultSet.getString("TABLE_NAME");
					if (true) {
						System.out.println("初始化表:" + tableName);
						ResultSet rs = null;
						if(CommonResource.databaseType.equals("mysql")){
							rs = dbmd.getColumns(null, "%", tableName, "%");
						}
						else if(CommonResource.databaseType.equals("oracle")){
							rs = conn.getMetaData().getColumns(null, getSchema(conn),tableName.toUpperCase(), "%");
						}
						List<String> columnName = new ArrayList<String>();
						List<String> remarks = new ArrayList<String>();
						List<String> type = new ArrayList<String>();
						List<Integer> size = new ArrayList<Integer>();
						List<String> camelName = new ArrayList<String>();
						List<String> javaClass = new ArrayList<String>();
						List<String> importClass = new ArrayList<String>();
						Map<String, String> importIf = new HashMap<String, String>();
						Map<String, Integer> columnMapper = new HashMap<String, Integer>();
						int k = 0;
						Map<String, String> hc = new HashMap<String, String>();
						while (rs.next()) {
							if(hc.get(rs.getString("COLUMN_NAME")) == null){
								hc.put(rs.getString("COLUMN_NAME"), "true");
							}else{
								continue;
							}
							columnName.add(rs.getString("COLUMN_NAME").toLowerCase());
							camelName.add(StringFormat.getColnameToCamelCase(rs.getString("COLUMN_NAME")));
							remarks.add(rs.getString("REMARKS"));
							type.add(rs.getString("TYPE_NAME"));
							if(!(rs.getString("COLUMN_SIZE")==null) && !rs.getString("COLUMN_SIZE").equals("")){
								size.add(Integer.parseInt(rs.getString("COLUMN_SIZE")));
							}
							else{
								size.add(null);
							}
							if (rs.getString("TYPE_NAME").equals("VARCHAR") || rs.getString("TYPE_NAME").equals("CHAR")
									|| rs.getString("TYPE_NAME").equals("TEXT") || rs.getString("TYPE_NAME").equals("VARCHAR2")) {
								javaClass.add("String");
							} else if (rs.getString("TYPE_NAME").equals("DATETIME")
									|| rs.getString("TYPE_NAME").equals("DATE")) {
								javaClass.add("Date");
								if (importIf.get("java.util.Date") == null) {
									importClass.add("java.util.Date");
									importIf.put("java.util.Date", "1");
								}
							} else if (rs.getString("TYPE_NAME").equals("INT") || rs.getString("TYPE_NAME").equals("INTEGER") || rs.getString("TYPE_NAME").equals("INT UNSIGNED") || rs.getString("TYPE_NAME").equals("TINYINT UNSIGNED")  || rs.getString("TYPE_NAME").equals("TINYINT")) {
								javaClass.add("Integer");
							} else if (rs.getString("TYPE_NAME").equals("SMALLINT")) {
								javaClass.add("Short");
							} else if (rs.getString("TYPE_NAME").equals("DECIMAL")) {
								javaClass.add("double");
/*								if (importIf.get("java.math.BigDecimal") == null) {
									importClass.add("java.math.BigDecimal");
									importIf.put("java.math.BigDecimal", "1");
								}*/
							}else if(rs.getString("TYPE_NAME").equals("TIMESTAMP")){
								javaClass.add("Date");
								if (importIf.get("java.util.Date") == null) {
									importClass.add("java.util.Date");
									importIf.put("java.util.Date", "1");
								}
							}else {
								javaClass.add("UnKnown");
								System.out.println("unknown type:" + rs.getString("TYPE_NAME"));
							}
							columnMapper.put(rs.getString("COLUMN_NAME"), k);
							k++;
						}
						Table tb = new Table();
						tb.setTableName(tableName);
						tb.setColumnName(columnName);
						tb.setColumnDesc(remarks);
						tb.setColumnType(type);
						tb.setColumnSize(size);
						tb.setJavaClass(javaClass);
						tb.setImportClass(importClass);
						tb.setColumnMapper(columnMapper);
						tb.setCamelName(camelName);
						tb.setTableDesc(resultSet.getString(5));
						CommonResource.tables.add(tb);
						CommonResource.columnMapper.put(tableName, tableIndex);
						tableIndex++;
						rs.close();
					}
				}
				System.out.println("共初始化" + CommonResource.tables.size() + "张表.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	} 
	
	public static void initAllCompareTables(Connection conn, String userName) {
		try {
			try {
				DatabaseMetaData dbmd = conn.getMetaData();
				ResultSet resultSet = null;
				if(CommonResource.databaseType.equals("mysql")){
					resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
				}
				else if(CommonResource.databaseType.equals("oracle")){
					resultSet = dbmd.getTables("null", userName.toUpperCase(), "%", new String[] { "TABLE" });
				}
				int tableIndex = 0;
				while (resultSet.next()) {
					String tableName = resultSet.getString("TABLE_NAME");
					if (true) {
						System.out.println("初始化表:" + tableName);
						ResultSet rs = null;
						if(CommonResource.databaseType.equals("mysql")){
							rs = dbmd.getColumns(null, "%", tableName, "%");
						}
						else if(CommonResource.databaseType.equals("oracle")){
							rs = conn.getMetaData().getColumns(null, getSchema(conn),tableName.toUpperCase(), "%");
						}
						List<String> columnName = new ArrayList<String>();
						List<String> remarks = new ArrayList<String>();
						List<String> type = new ArrayList<String>();
						List<Integer> size = new ArrayList<Integer>();
						List<String> camelName = new ArrayList<String>();
						List<String> javaClass = new ArrayList<String>();
						List<String> importClass = new ArrayList<String>();
						Map<String, String> importIf = new HashMap<String, String>();
						Map<String, Integer> columnMapper = new HashMap<String, Integer>();
						int k = 0;
						Map<String, String> hc = new HashMap<String, String>();
						while (rs.next()) {
							if(hc.get(rs.getString("COLUMN_NAME")) == null){
								hc.put(rs.getString("COLUMN_NAME"), "true");
							}else{
								continue;
							}
							columnName.add(rs.getString("COLUMN_NAME").toLowerCase());
							camelName.add(StringFormat.getColnameToCamelCase(rs.getString("COLUMN_NAME")));
							remarks.add(rs.getString("REMARKS"));
							type.add(rs.getString("TYPE_NAME"));
							if(!(rs.getString("COLUMN_SIZE")==null) && !rs.getString("COLUMN_SIZE").equals("")){
								size.add(Integer.parseInt(rs.getString("COLUMN_SIZE")));
							}
							else{
								size.add(null);
							}
							if (rs.getString("TYPE_NAME").equals("VARCHAR") || rs.getString("TYPE_NAME").equals("CHAR")
									|| rs.getString("TYPE_NAME").equals("TEXT") || rs.getString("TYPE_NAME").equals("VARCHAR2")) {
								javaClass.add("String");
							} else if (rs.getString("TYPE_NAME").equals("DATETIME")
									|| rs.getString("TYPE_NAME").equals("DATE")) {
								javaClass.add("Date");
								if (importIf.get("java.util.Date") == null) {
									importClass.add("java.util.Date");
									importIf.put("java.util.Date", "1");
								}
							} else if (rs.getString("TYPE_NAME").equals("INT") || rs.getString("TYPE_NAME").equals("INTEGER") || rs.getString("TYPE_NAME").equals("INT UNSIGNED") || rs.getString("TYPE_NAME").equals("TINYINT UNSIGNED")  || rs.getString("TYPE_NAME").equals("TINYINT")) {
								javaClass.add("Integer");
							} else if (rs.getString("TYPE_NAME").equals("SMALLINT")) {
								javaClass.add("Short");
							} else if (rs.getString("TYPE_NAME").equals("DECIMAL")) {
								javaClass.add("double");
/*								if (importIf.get("java.math.BigDecimal") == null) {
									importClass.add("java.math.BigDecimal");
									importIf.put("java.math.BigDecimal", "1");
								}*/
							}else if(rs.getString("TYPE_NAME").equals("TIMESTAMP")){
								javaClass.add("Date");
								if (importIf.get("java.util.Date") == null) {
									importClass.add("java.util.Date");
									importIf.put("java.util.Date", "1");
								}
							}else {
								javaClass.add("UnKnown");
								System.out.println("unknown type:" + rs.getString("TYPE_NAME"));
							}
							columnMapper.put(rs.getString("COLUMN_NAME"), k);
							k++;
						}
						Table tb = new Table();
						tb.setTableName(tableName);
						tb.setColumnName(columnName);
						tb.setColumnDesc(remarks);
						tb.setColumnType(type);
						tb.setColumnSize(size);
						tb.setJavaClass(javaClass);
						tb.setImportClass(importClass);
						tb.setColumnMapper(columnMapper);
						tb.setCamelName(camelName);
						tb.setTableDesc(resultSet.getString(5));
						CommonResource.compareTables.add(tb);
						CommonResource.compareColumnMapper.put(tableName, tableIndex);
						tableIndex++;
						rs.close();
					}
				}
				System.out.println("共初始化" + CommonResource.tables.size() + "张表.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	} 
	
    private static String getSchema(Connection conn) throws Exception {  
        String schema;  
        schema = conn.getMetaData().getUserName();  
        if ((schema == null) || (schema.length() == 0)) {  
            throw new Exception("获取oracle schema时发生了一个错误!");  
        }  
        return schema.toUpperCase().toString();  
  
    }  
}
