package com.htl.cloudmemory.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageDetail {
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	private Long total;
	
	private Integer pages;
	
	private List<?> list;
	
	public PageDetail(PageInfo<?> pageInfo){
		this.setPageNum(pageInfo.getPageNum());
		this.setPageSize(pageInfo.getPageSize());
		this.setTotal(pageInfo.getTotal());
		this.setPages(pageInfo.getPages());
		this.setList(pageInfo.getList());
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
		
}
