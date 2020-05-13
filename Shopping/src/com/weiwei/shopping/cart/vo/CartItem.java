package com.weiwei.shopping.cart.vo;

import com.weiwei.shopping.product.vo.Product;
/**
 * 购物项实体类
 * @author 潘唯
 */
public class CartItem {
	private Product product;//购物项中的商品信息
	private int count;//购买某种商品的数量
	private double subtotal;//购买某种商品的小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/*小计是自动计算的*/
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	
}
