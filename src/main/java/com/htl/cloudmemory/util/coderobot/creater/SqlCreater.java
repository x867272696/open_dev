package com.htl.cloudmemory.util.coderobot.creater;

import java.util.List;
import com.htl.cloudmemory.util.coderobot.bean.CommonResource;
import com.htl.cloudmemory.util.coderobot.bean.Table;
import com.htl.cloudmemory.util.coderobot.util.StringFormat;

/**
 * 
 * @author htl
 * @since CodeRobot 1.0
 */
public class SqlCreater{

	/**
	 * @TODO sql构造方法
	 */
	public String parse(String order) {
		String sql=null;
		String orderArray[]=order.split(" ");
		if(orderArray[1].equals("query")){
			Table table=CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2]));
			String selectSql="\t<select id=\"query" + StringFormat.initcap(StringFormat.getColnameToCamelCase(orderArray[2])) + "\" parameterType=\"" + StringFormat.initcap(StringFormat.getColnameToCamelCase(CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2])).getTableName())) + "Page\"" + " resultType=\"" + StringFormat.initcap(StringFormat.getColnameToCamelCase(CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2])).getTableName())) + "Page" +  "\">\r\n";
			selectSql=selectSql + "\t\tselect " + "\r\n\t\t";
			for(int j=0;j<table.getColumnName().size();j++){
				selectSql=selectSql + table.getColumnName().get(j) + " as " + StringFormat.getColnameToCamelCase(table.getColumnName().get(j)) + ",";
				if(j==(table.getColumnName().size()-1)){
					selectSql=selectSql.substring(0,selectSql.length()-1);
				}
				if(j%3==0 && j!=0 && j!=(table.getColumnName().size()-1)){
					selectSql=selectSql + "\r\n\t\t";
				}
			}
			selectSql=selectSql + " from " + table.getTableName() + " where " + "1=1\r\n";
			String queryColums[]=orderArray[3].split(",");
			for(int i=0;i<queryColums.length;i++){
				if(table.getColumnMapper().get(queryColums[i])!=null){
					selectSql=selectSql + "\t\t<if test=\"" + "query" + StringFormat.initcap(StringFormat.getColnameToCamelCase(queryColums[i])) + "!=null and query" + StringFormat.initcap(StringFormat.getColnameToCamelCase(queryColums[i])) + "!='' \">\r\n";
					selectSql=selectSql + "\t\t\tand " + orderArray[2] + "." + queryColums[i] + "=#{" + "query" + StringFormat.initcap(StringFormat.getColnameToCamelCase(queryColums[i])) + "}\r\n";
					selectSql=selectSql + "\t\t</if>\r\n";
				}
			}
			selectSql=selectSql + "\t</select>\r\n";
			sql=selectSql;
		}
		else if(orderArray[1].equals("select")){
			Table table=CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2]));
			List<String> columnlName=table.getColumnName();
			String selectSql="\t<select id=\"get" + StringFormat.initcap(StringFormat.getColnameToCamelCase(table.getTableName())) + "By" + StringFormat.initcap(StringFormat.getColnameToCamelCase(orderArray[3])) + "\" parameterType=\"" + table.getJavaClass().get(table.getColumnMapper().get(orderArray[3])) + "\"" + " resultType=\"" + StringFormat.initcap(StringFormat.getColnameToCamelCase(table.getTableName())) + "\"" + ">\r\n";
			selectSql=selectSql + "\t\tselect " + "\r\n\t\t";
			for(int j=0;j<columnlName.size();j++){
				selectSql=selectSql + columnlName.get(j) + " as " + StringFormat.getColnameToCamelCase(columnlName.get(j)) + ",";
				if(j==(columnlName.size()-1)){
					selectSql=selectSql.substring(0,selectSql.length()-1);
				}
				if(j%3==0 && j!=0 && j!=(columnlName.size()-1)){
					selectSql=selectSql + "\r\n\t\t";
				}
			}
			selectSql=selectSql + " from " + table.getTableName() + " where " + orderArray[3] + "=#{0}" + "\r\n\t";
			selectSql=selectSql + "</select>\r\n";
			return selectSql;
		}
		else if(orderArray[1].equals("update")){
			Table table=CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2]));
			String updateSql="\t<update id=\"update" + StringFormat.initcap(StringFormat.getColnameToCamelCase(orderArray[2])) + "By" + StringFormat.initcap(StringFormat.getColnameToCamelCase(orderArray[3])) + "\" parameterType=\"" + StringFormat.initcap(StringFormat.getColnameToCamelCase(table.getTableName())) + "\">\r\n";
			updateSql= updateSql + "\t\tupdate " + table.getTableName() + " set \r\n\t\t";
			for(int j=0;j<table.getColumnName().size();j++){
				if(!table.getColumnName().get(j).equals("id")){
					updateSql=updateSql + table.getColumnName().get(j) + "=" + "#{" + StringFormat.getColnameToCamelCase(table.getColumnName().get(j)) + "}" + ",";
				}
				if(j==(table.getColumnName().size()-1)){
					updateSql=updateSql.substring(0,updateSql.length()-1);
				}
				if(j%3==0 && j!=0 && j!=(table.getColumnName().size()-1)){
					updateSql=updateSql + "\r\n\t\t";
				}
			}
			updateSql=updateSql + " where " + orderArray[3] + "=#{" + StringFormat.getColnameToCamelCase(orderArray[3]) + "}" + "\r\n\t";
			updateSql=updateSql + "</update>\r\n";
			return updateSql;
		}
		else if(orderArray[1].equals("insert")){
			Table table=CommonResource.tables.get(CommonResource.columnMapper.get(orderArray[2]));
			String nowSql="\t<insert id=\"save" + StringFormat.initcap(StringFormat.getColnameToCamelCase(orderArray[2])) + "\" parameterType=\"" + StringFormat.initcap(StringFormat.getColnameToCamelCase(table.getTableName())) + "\">\r\n";
			nowSql=nowSql + "\t\tinsert into " + orderArray[2] + "(\r\n\t\t";
			String valueSql="values(\r\n\t\t";
			for(int i=0;i<table.getColumnName().size();i++){
				if(!table.getColumnName().get(i).equals("id")){
					nowSql=nowSql + table.getColumnName().get(i) + ",";
					valueSql=valueSql + "#{" + StringFormat.getColnameToCamelCase(table.getColumnName().get(i)) + "}" + ",";
				}
				if(i==(table.getColumnName().size()-1)){
					nowSql=nowSql.substring(0,nowSql.length()-1) + ")";
					valueSql=valueSql.substring(0,valueSql.length()-1) + ")";
				}
				if(i%3==0 && i!=0 && i!=(table.getColumnName().size()-1)){
					nowSql=nowSql + "\r\n\t\t";
					valueSql=valueSql + "\r\n\t\t";
				}
			}
			return nowSql + valueSql + "\r\n\t" + "</insert>\r\n";
		}
		return sql;
	}
}
