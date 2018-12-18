package com.htl.cloudmemory.util.coderobot.bean;

import java.util.List;

import java.util.Map;
/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class Table {
	//表名
	private String tableName;
	//表注释
	private String tableDesc;
	//表中的所有字段名
	private List<String> columnName;
	//是否主键
	private List<Integer> isPrimaryKey;
	//是否允许为空
	private List<Integer> allowNull;
	//默认值
	private List<String> defaultValue;
	//表中的所有字段对应的驼峰名
	private List<String> camelName;
	//表中的所有字段的对应注释
	private List<String> columnDesc;
	//表中的所有字段的对应class类型
	private List<String> columnType;
	//表中所有字段的对应大小
	private List<Integer> columnSize;
	//表中所有字段对应java中的class类型
	private List<String> javaClass;
	//本表对应实体类需要引入的java中的class
	private List<String> importClass;
	//从字段名到List的index的映射
	private Map<String,Integer> columnMapper;
	
	public String getCreateStatement(){
		String sql="";
		sql=sql + "CREATE TABLE " + "`" + this.getTableName() + "`" + "(\r\n";
		for(int i=0;i<this.getColumnName().size();i++){
			String name="\t" + "`" + this.getColumnName().get(i) + "`";
			String type="";
			String nullAble="";
			String comment;
			if(this.getColumnSize().get(i)!=null){
				type=" " + this.getColumnType().get(i) + "(" + this.getColumnSize().get(i) + ")";
			}else{
				type=" " + this.getColumnType().get(i);
			}
			if(this.getColumnName().get(i).equals("id")){
				nullAble=" NOT NULL AUTO_INCREMENT";
			}else{
				nullAble=" DEFAULT NULL";
			}
			comment=" COMMENT " + "'" + this.getColumnDesc().get(i) + "',\r\n";
			sql=sql + name + type + nullAble + comment;
		}
		sql=sql + "\tPRIMARY KEY (`id`)\r\n";
		sql=sql + ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='自动生成的表';\r\n";
		return sql;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getColumnName() {
		return columnName;
	}
	public void setColumnName(List<String> columnName) {
		this.columnName = columnName;
	}
	public List<String> getCamelName() {
		return camelName;
	}
	public void setCamelName(List<String> camelName) {
		this.camelName = camelName;
	}
	public List<String> getColumnDesc() {
		return columnDesc;
	}
	public void setColumnDesc(List<String> columnDesc) {
		this.columnDesc = columnDesc;
	}
	public List<String> getColumnType() {
		return columnType;
	}
	public void setColumnType(List<String> columnType) {
		this.columnType = columnType;
	}
	public List<Integer> getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(List<Integer> columnSize) {
		this.columnSize = columnSize;
	}
	public List<String> getJavaClass() {
		return javaClass;
	}
	public void setJavaClass(List<String> javaClass) {
		this.javaClass = javaClass;
	}
	public List<String> getImportClass() {
		return importClass;
	}
	public void setImportClass(List<String> importClass) {
		this.importClass = importClass;
	}
	public Map<String, Integer> getColumnMapper() {
		return columnMapper;
	}
	public void setColumnMapper(Map<String, Integer> columnMapper) {
		this.columnMapper = columnMapper;
	}
	public List<Integer> getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(List<Integer> isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public List<Integer> getAllowNull() {
		return allowNull;
	}
	public void setAllowNull(List<Integer> allowNull) {
		this.allowNull = allowNull;
	}
	public List<String> getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(List<String> defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	
}
