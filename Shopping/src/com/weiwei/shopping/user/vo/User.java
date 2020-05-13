package com.weiwei.shopping.user.vo;

import java.util.HashSet;
import java.util.Set;

import com.weiwei.shopping.comment.vo.Comment;
import com.weiwei.shopping.order.vo.Order;

/**
 * 用户实体类
 * @author 潘唯
 */
public class User {
	private Integer uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String address;
	private Integer state;
	private String code;
	// 一个用户对应多个订单:
	private Set<Order> orders = new HashSet<Order>();
	/*配置商品评价的集合*/
	private Set<Comment> comments = new HashSet<Comment>();
	
	/*无参构造器*/
	public User() {
	}
	/*全参构造器*/
	public User(Integer uid, String username, String password, String name,
			String email, String phone, String address, Integer state,
			String code) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.state = state;
		this.code = code;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
