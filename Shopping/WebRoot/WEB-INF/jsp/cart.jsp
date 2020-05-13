<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css">


</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.action"> 
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/mycar.png"
					alt="唯唯乐购">
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障">
			</div>
		</div>
		
		<%@ include file="head.jsp" %>

	</div>
	<div class="container cart">
	
	<s:if test="#session.cart.cartItems.size() != 0">
		<div class="span24">
			<div class="step step1">
				<h2 style="font-family:微软雅黑;color:red;">我的购物车清单</h2>
			</div>
			<table>
				<tbody>
					<tr>
						<th>宝贝模样</th>
						<th>宝贝名称</th>
						<th>乐购单价</th>
						<th>选购数量</th>
						<th>花费小计</th>
						<th>操作</th>
					</tr>
					
					<s:iterator var="cartItem" value="#session.cart.cartItems">
						<tr>
							<td width="60">
								<img src="${pageContext.request.contextPath}/<s:property value="#cartItem.product.image"/>" />
							</td>
							<td>
								<a target="_blank">
									<s:property value="#cartItem.product.pname"/>
								</a>
							</td>
							<td>￥<s:property value="#cartItem.product.shop_price"/></td>
							<td class="quantity" width="60">
								<s:property value="#cartItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#cartItem.subtotal"/></span>
							</td>
							<td>
								<a href="${pageContext.request.contextPath}/cart_RemoveMyCart.action?pid=<s:property value="#cartItem.product.pid"/>" class="delete">删除</a>
							</td>
						</tr>
					</s:iterator>
				
				</tbody>
			</table>
		 	<dl id="giftItems" class="hidden" style="display: none;"></dl>
			<div class="total">
				<em id="promotion"></em> 
				<em> 登录后确认是否享有优惠 </em> 
				赠送积分: 
				<em id="effectivePoint"><s:property value="#session.cart.total"/></em> 
				商品金额: 
				<strong id="effectivePrice">￥<s:property value="#session.cart.total"/>元</strong>
			</div>
			<div class="bottom">
				<a href="${ pageContext.request.contextPath }/cart_ClearMyCart.action" id="clear" class="clear">清空购物车</a> 
				<a href="${ pageContext.request.contextPath }/order_saveOrder.action" id="submit" class="submit">提交订单</a>
			</div>
		</div>
	</s:if>	
	<s:else>
		<div style="height:400px;width:100%;">
		<br /><br /><br />
			<h1 style="font-family:隶书;color:#C71585;align:center;">亲，购物车空空如也！先去商城选购宝贝吧···<br /></h1>
		</div>
	</s:else>
		
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>