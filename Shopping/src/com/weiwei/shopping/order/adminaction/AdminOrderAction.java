package com.weiwei.shopping.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.order.service.OrderService;
import com.weiwei.shopping.order.vo.Order;
import com.weiwei.shopping.order.vo.OrderItem;
import com.weiwei.shopping.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
	/*模型驱动使用的对象*/
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	/*注入OrderService*/
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/*接收前台传来的参数*/
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/*分页查询所有订单的Action*/
	public String findAllByPage(){
		PageBean<Order> pageBean = orderService.findAll(page);
		// 将数据存入到值栈中保存到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAllByPage";
	}
	
	/*修改订单状态的Action*/
	public String updateState(){
		Order oldOrder = orderService.FindByOid(order.getOid());
		oldOrder.setState(3);
		orderService.update(oldOrder);
		// 页面跳转
		return "updateStateSuccess";
	}
	
	/*根据订单的oid查询订单项的Action*/
	public String findOrderItem(){
		// 根据订单oid查询订单项
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// 将集合放到值栈，从而显示到页面
		ActionContext.getContext().getValueStack().set("list", list);
		// 页面跳转
		return "findOrderItem";
	}
	
	/*根据订单编号删除订单的Action*/
	public String delete(){
		order = orderService.FindByOid(order.getOid());
		orderService.delete(order);
		//页面跳转
		return "deleteSuccess";
	}
	
	/*根据订单状态分页查找订单*/
	public String findByState(){
		PageBean<Order> pageBean = orderService.findByState(order.getState(), page);
		// 将数据存入到值栈中保存到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findByState";
	}
}
