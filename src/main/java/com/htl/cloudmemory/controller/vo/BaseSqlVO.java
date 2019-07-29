package com.htl.cloudmemory.controller.vo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class BaseSqlVO {
	
	@NotNull(message = "selectSql 不能为null")
	private String selectSql;
	
	@NotEmpty(message = "tag 不能为空")
	private String tag;

	public String getSelectSql() {
		return selectSql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
		
}
