package com.weiwei.shopping.comment.vo;

import java.util.Date;

import com.weiwei.shopping.product.vo.Product;
import com.weiwei.shopping.user.vo.User;

public class Comment {
	private Integer cid;
	private Date cdate;
	private Integer type;
	private String comment;
	/*外键：商品对象*/
	private Product product;
	/*外键：用户对象*/
	private User user;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
