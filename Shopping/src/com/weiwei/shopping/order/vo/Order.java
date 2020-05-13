package com.weiwei.shopping.order.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.weiwei.shopping.user.vo.User;

/**
 * 订单的实体类
 * @author 潘唯
 */
public class Order{
	private Integer oid;
	private Double total;
	private Date ordertime;
	private Integer state;/*1:未付款   2:订单已经付款   3:已经发货   4:订单结束*/
	private String name;
	private String phone;
	private String address;
	/*User的外键*/
	private User user;
	/*配置订单项的集合*/
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	/*提供get、set方法*/
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
}
