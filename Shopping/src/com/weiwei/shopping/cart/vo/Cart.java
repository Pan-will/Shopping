package com.weiwei.shopping.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车实体类
 * @author 潘唯
 */

/*实现序列化的接口，以免服务器抛未序列化异常*/
public class Cart implements Serializable{
	/*购物项集合、总计只是购物车的属性*/
	
	/*购物项集合:
	 * key:商品的id
	 * value：购物项*/
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	/*只需要map的value，所以将value转成单列集合，这样在页面上便于获取。
	 * Cart对象中有一个叫cartItems属性
	 * */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}

	/*购物的总金额：总计，提供set、get方法*/
	private double total;
	public double getTotal() {
		return total;
	}
	
	/*
	 * 购物车的功能
	 * */
	/*功能1：将购物项添加到购物车*/
	public void addCart(CartItem cartItem){
		/*判断购物车中是否已经存在该购物项
		 * 	存在：
		 * 		数量增加
		 * 		总计 = 总计 + 购物项小计
		 * 不存在：
		 * 		向map中添加购物项
		 * 		总计 = 总计 + 购物项小计
		 * */
		//获得商品id
		Integer pid = cartItem.getProduct().getPid();
		//判断购物车中是否存在该购物项：
		if(map.containsKey(pid)){
			//存在
			//获取原来的购物项
			CartItem OcartItem = map.get(pid);
			OcartItem.setCount(OcartItem.getCount() + cartItem.getCount());
		} else {
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计的值
		total += cartItem.getSubtotal();
	}
	/*功能2：从购物车中移出购物项*/
	public void removeCart(Integer pid){
		//将购物项移出购物车
		CartItem cartItem = map.remove(pid);
		//总计=总计-移出的购物项小计
		total -= cartItem.getSubtotal();
	}
	/*功能3：清空购物车*/
	public void clearCart(){
		//将所有购物项清空
		map.clear();
		//将总计置0
		total = 0;
	}

}
