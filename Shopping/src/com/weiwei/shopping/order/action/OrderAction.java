package com.weiwei.shopping.order.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.cart.vo.Cart;
import com.weiwei.shopping.cart.vo.CartItem;
import com.weiwei.shopping.order.service.OrderService;
import com.weiwei.shopping.order.vo.Order;
import com.weiwei.shopping.order.vo.OrderItem;
import com.weiwei.shopping.user.vo.User;
import com.weiwei.shopping.utils.PageBean;
import com.weiwei.shopping.utils.PaymentUtil;
/**
 * 订单模块：OrderAction
 * @author 潘唯
 */

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	/*用于接收数据的模型驱动*/
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	/*注入OrderService,提供set方法*/
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/*接收前台传来的page:当前页,提供set方法*/
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/*接收支付通道编码(就是银行)，并提供set方法*/
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

	/*提交订单的action*/
	public String saveOrder() {
		// 设置订单的总金额:订单的总金额应该是购物车中总金额:
		// 购物车在session中,从session总获得购物车信息.
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲，购物车空空如也！先去商城选购宝贝吧···");
			return "msg";
		}
		
		/**
		 * 若订单编号是字符串类型，则用系统时间格式化后作为订单号，这样可以保证订单号唯一。
		 * order.setOid(new SimpleDateFormat("yyyyMMddHHMM").format(new Date()));
		 * 
		 * 若将时间戳转为int类型，不太可取，可能订单号重合或数据过大产生溢出问题。
		 * 
		 */
		
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		order.setState(1); /*1:未付款；2：已付款待发货；3：付款已发货；4：订单完成；*/
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!请您先登录!");
			return "login";
		}
		order.setUser(existUser);
		// 设置订单项集合
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// 清空购物车
		cart.clearCart();
		// 页面需要回显订单信息:
		// 使用模型驱动就不必用值栈保存了
		// ActionContext.getContext().getValueStack().set("order", order);
		return "saveOrder";
	}
	
	/*根据用户id分页查询我的订单的Action*/
	public String FindByUid(){
		//获得session中的user
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//调用OrderService
		PageBean<Order> pageBean = orderService.FindByUid(existUser.getUid(), page);
		//将pageBean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "FindByUid";
	}
	
	/*根据订单编号查询该订单 · 将该订单信息转到订单详情页面进行显示*/
	public String FindByOid(){
		order = orderService.FindByOid(order.getOid());
		return "FindByOid";
	}
	
	/*确认订单，付款的action
	 * 先修改订单上的客户信息*/
	public String PayOrder() throws IOException{
		//根据订单编号查找到该订单
		Order payorder = orderService.FindByOid(order.getOid());
		//接收订单详情并set到order对象中
		payorder.setAddress(order.getAddress());
		payorder.setName(order.getName());
		payorder.setPhone(order.getPhone());
		//修改订单——主要是为该订单添加收货地址等详情
		orderService.update(payorder);
		//为订单付款.中文需转码，非必要字段省略不传
		
		String 	p0_Cmd="Buy",//业务类型
				p1_MerId="10000940764",//商户编号
				p2_Order=order.getOid().toString(),//订单编号
				p3_Amt="0.01",//支付金额order.getTotal().toString();
				p4_Cur="CNY",//交易币种，人民币
				p5_Pid="",//商品名称
				p6_Pcat="",//商品种类
				p7_Pdesc="",//商品描述
				p8_Url="http://localhost:8080/Shopping/order_callBack.action",//商户接收支付成功数据的地址
				p9_SAF="",//送货地址
				pa_MP="",//商户扩展信息
				pd_FrpId=this.pd_FrpId,//支付通道编码
				pr_NeedResponse="1";//应答机制,固定值
		
		String keyValue = "w0P75wMZ203fr46r5i70V556WHFa94j14yW5J6vuh4yo3nRl5jsqF3c41677";//密钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, 
				p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//签名数据,由算法和密钥产生的.
		
		//向易宝传数据
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
		System.out.println("商户编号："+p1_MerId);
		System.out.println("密钥："+keyValue);
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
	
	/*催单*/
	public String CuiDan(){
		this.addActionMessage("客官别急！系统已提醒店家发货！");
		return "msg";
	}
	
	/*确认收货的Action*/
	public String updateState(){
		Order oldOrder = orderService.FindByOid(order.getOid());
		oldOrder.setState(4);
		orderService.update(oldOrder);
		return "updateStateOK";
	}
	
	/*跳转倒评论页面*/
	public String comment(){
		order = orderService.FindByOid(order.getOid());
		return "comment";
	}
	
}
