package com.weiwei.shopping.order.vo;

import com.weiwei.shopping.product.vo.Product;

/**
 * 订单项的实体类
 * @author 潘唯
 */
public class OrderItem{
	private Integer itemid;
	private Integer count;
	private Double subtotal;
	/*外键 · 商品对象*/
	private Product product;
	/*外键 · 订单对象*/
	private Order order;
	
	/*提供get、set方法*/
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
