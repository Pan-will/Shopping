package com.weiwei.shopping.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import com.weiwei.shopping.category.vo.Category;
import com.weiwei.shopping.product.vo.Product;

public class CategorySecond {
	private Integer csid;
	private String csname;
	
	/*所属的一级分类。存的是一级分类的对象*/
	private Category category;
	
	/*二级分类中应该放一个二级分类下商品的集合*/
	private Set<Product> products = new HashSet<Product>();
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
