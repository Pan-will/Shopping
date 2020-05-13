package com.weiwei.shopping.order.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.order.service.OrderService;
import com.weiwei.shopping.order.vo.Order;
import com.weiwei.shopping.utils.PaymentUtil;

public class PayorderAction extends ActionSupport implements ModelDriven<Order>{
	/*用于接收模型驱动*/
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/*接收支付通道编码*/
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	/*接收付款成功后的相应数据*/
	private String r3_Amt;
	private String r6_Order;
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public String PayOrder() throws IOException{
		//根据订单编号查找到该订单
		Order currorder = orderService.FindByOid(order.getOid());
		//接收订单详情并set到order对象中
		currorder.setAddress(order.getAddress());
		currorder.setName(order.getName());
		currorder.setPhone(order.getPhone());
		//修改订单——主要是为该订单添加收货地址等详情
		orderService.update(currorder);
		
		String 	p0_Cmd="Buy",
				p1_MerId="10000940764",
				p2_Order=order.getOid().toString(),
				p3_Amt="0.01",
				p4_Cur="CNY",
				p5_Pid="",
				p6_Pcat="",
				p7_Pdesc="",
				p8_Url="http://localhost:8080/Shopping/order_callBack.action",
				p9_SAF="",
				pa_MP="",
				pd_FrpId=this.pd_FrpId,
				pr_NeedResponse="1";
		String keyValue="w0P75wMZ203fr46r5i70V556WHFa94j14yW5J6vuh4yo3nRl5jsqF3c41677";
		String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		String url="https://www.yeepay.com/app-merchant-proxy/node?"+
		"&p0_Cmd="+p0_Cmd+
		"&p1_MerId="+p1_MerId+
		"&p2_Order="+p2_Order+
		"&p3_Amt="+p3_Amt+
		"&p4_Cur="+p4_Cur+
		"&p5_Pid="+p5_Pid+
		"&p6_Pcat="+p6_Pcat+
		"&p7_Pdesc="+p7_Pdesc+
		"&p8_Url="+p8_Url+
		"&p9_SAF="+p9_SAF+
		"&pa_MP="+pa_MP+
		"&pd_FrpId="+pd_FrpId+
		"&pr_NeedResponse="+pr_NeedResponse+
		"&hmac="+hmac;
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(url);
		return NONE;
	}
	
	/*付款成功后的一个转向操作*/
	public String callBack(){
		//修改订单的状态:未付款 --> 已付款
		Order payOrder = orderService.FindByOid(Integer.parseInt(r6_Order));
		payOrder.setState(2);
		orderService.update(payOrder);
		//在页面上显示付款成功的提示
		this.addActionMessage(r6_Order +"号订单付款成功！<br />付款金额："+r3_Amt+"元。");
		return "msg";
	}
}
