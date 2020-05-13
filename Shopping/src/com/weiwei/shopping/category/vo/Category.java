package com.weiwei.shopping.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.weiwei.shopping.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体类对象
 * @author 潘唯
 */
/*实现序列化的接口，以免服务器抛未序列化异常*/
public class Category implements Serializable{
	private Integer cid;
	private String cname;
	
	/*一级分类中应该放一个二级分类的集合*/
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	/*全参构造器*/
	public Category(Integer cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}
	/*无参构造器*/
	public Category() {
	}
	/*主键构造器*/
	public Category(Integer cid) {
		this.cid = cid;
	}
}
