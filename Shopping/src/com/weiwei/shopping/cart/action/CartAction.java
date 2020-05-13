package com.weiwei.shopping.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.weiwei.shopping.cart.vo.Cart;
import com.weiwei.shopping.cart.vo.CartItem;
import com.weiwei.shopping.product.service.ProductService;
import com.weiwei.shopping.product.vo.Product;

public class CartAction extends ActionSupport{
	/*接收商品pid、数量count，并提供set方法*/
	private Integer pid;
	private Integer count;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/*注入商品的ProductService，并提供set方法*/
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/*添加商品进购物车的Action*/
	public String AddCart(){
		//封装一个CartItem对象
		CartItem cartItem = new CartItem();
		//设置添加进的该商品数量
		cartItem.setCount(count);
		//调用productService,根据pid查询商品
		Product product = productService.FindByPid(pid);
		//设置该商品信息
		cartItem.setProduct(product);
		/*将购物项添加进购物车
		 * 首项应拿到购物车——从session中拿*/
		Cart cart = getCart();
		cart.addCart(cartItem);
		
		return "AddCart";
	}
	
	/**
	 * 从session中拿到购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			/*若session中没有购物车，则new一个购物车并添加到session中*/
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 * 跳转到我的购物车的Action
	 */
	public String MyCart(){
		return "MyCart";
	}
	
	/**
	 * 清空我的购物车的Action
	 */
	public String ClearMyCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "ClearMyCart";
	}
	
	/**
	 * 移出商品
	 */
	public String RemoveMyCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "RemoveMyCart";
	}
}
