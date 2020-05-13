package com.weiwei.shopping.utils;

import java.util.List;

/**
 * 分页工具的封装类
 * @author 潘唯
 */
public class PageBean<T> {
	private int page;//当前页
	private int totalCount;//总记录数
	private int totalPage;//总页数
	private int limit;//每页显示的记录数
	/*使用泛型*/
	private List<T> list;//每页显示的数据集合
	
	/*无参构造器*/
	public PageBean() {
		super();
	}
	/*全参构造器*/
	public PageBean(int page, int totalCount, int totalPage, int limit,
			List<T> list) {
		super();
		this.page = page;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.limit = limit;
		this.list = list;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
