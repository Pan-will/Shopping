<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.action"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/mycar.png"
					alt="唯唯乐购" />
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>

		<%@ include file="head.jsp"%>

	</div>

	<div class="container cart">

		<div class="span24">

			<table>
				<tbody>
					<tr>
						<th colspan="5">订单编号：<s:property value="model.oid"/></th>
					</tr>
					<tr>
						<th>宝贝模样</th>
						<th>宝贝名称</th>
						<th>宝贝单价/元</th>
						<th>购买数量</th>
						<th>花费小计</th>
					</tr>
				<s:iterator var="orderItem" value="model.orderItems">
					<tr>
						<td width="60">
							<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>" />
						</td>
						<td>
							<a target="_blank"><s:property value="#orderItem.product.pname" /></a>
						</td>
						<td><s:property value="#orderItem.product.shop_price" />元/件</td>
						<td class="quantity" width="60">
							<s:property value="#orderItem.count" />
						</td>
						<td width="140"><span class="subtotal">￥<s:property value="#orderItem.subtotal" /></span></td>
					</tr>
				</s:iterator>	
				</tbody>
			</table>
			<dl id="giftItems" class="hidden" style="display: none;">
			</dl>
			<div class="total">
				<em id="promotion"></em> 购物车总额: <strong id="effectivePrice">￥<s:property value="model.total" />元</strong>
			</div>
			<form id="orderForm" action="${pageContext.request.contextPath}/order_PayOrder.action" method="post">
				<input type="hidden" name="oid" value="<s:property value="model.oid"/>" />
				<div class="span24">
					<p>
						收货地址：
						<input name="address" id="address" type="text" value="<s:property value="model.user.address"/>" style="width:350px" /> <br /> 
						收货人&nbsp;&nbsp;&nbsp;：
						<input name="name" id="name" type="text" value="<s:property value="model.user.name"/>" style="width:150px" /> <br /> 
						联系方式：
						<input name="phone" id="phone" type="text" value="<s:property value="model.user.phone"/>" style="width:150px" />
					</p>
					<hr />
					<p>
						选择银行：<br /> 
						<input type="radio" name="bank" value="ICBC-NET-B2C" checked="checked" />
						工商银行 <img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bank" value="BOC-NET-B2C" />
						中国银行 <img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bank" value="ABC-NET-B2C" />
						农业银行 <img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle" /> <br /> 
						<input type="radio" name="bank" value="BOCO-NET-B2C" />
						交通银行 <img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bank" value="PINGANBANK-NET" />
						平安银行 <img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bank" value="CCB-NET-B2C" />
						建设银行 <img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle" /> <br /> 
						<input type="radio" name="bank" value="CEB-NET-B2C" />
						光大银行 <img src="${pageContext.request.contextPath}/bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="bank" value="CMBCHINA-NET-B2C" />
						招商银行 <img src="${pageContext.request.contextPath}/bank_img/cmb.bmp" align="middle" />
					</p>
					<hr />
					<p style="text-align:right">
						<a href="javascript:document.getElementById('orderForm').submit();">
							<img src="${pageContext.request.contextPath}/images/finalbutton.gif" width="204" height="51" border="0" />
						</a>
					</p>
				</div>
			</form>
		</div>

	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
