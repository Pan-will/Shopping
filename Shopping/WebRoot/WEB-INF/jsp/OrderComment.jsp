<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
			/*AJAX异步操作*/
			function DoComment(pid){
				//获得按钮的对象
				var but = document.getElementById("but"+pid);
				//获得div的对象
				var div1 = document.getElementById("div"+pid);
				if(but.value == "评价"){
					// 1.创建异步对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								div1.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/product_findProductItem.action?pid="+pid+"&time="+new Date().getTime(),true);
					// 4.发送
					xhr.send(null);
					but.value = "关闭";
				} else {
					div1.innerHTML = "";
					but.value="评价";
				}
			}
			/*创建异步对象的方法*/
			function createXmlHttp(){
			   var xmlHttp;
			   try{ // Firefox, Opera 8.0+, Safari
			        xmlHttp=new XMLHttpRequest();
			    }
			    catch (e){
				   try{// Internet Explorer
				         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				      }
				    catch (e){
				      try{
				         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				      }
				      catch (e){}
				      }
			    }
				return xmlHttp;
			 }
		</script>
</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.action">
					<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/mycar.png"
					alt="唯唯乐购" />
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>

		<%@ include file="head.jsp"%>

	</div>

	<div class="container cart">

		<div class="span24">

			<table>
				<tbody>
					<tr>
						<th colspan="6">订单编号：<s:property value="model.oid"/></th>
					</tr>
					<tr>
						<th>宝贝模样</th>
						<th>宝贝名称</th>
						<th>宝贝单价/元</th>
						<th>购买数量</th>
						<th>花费小计</th>
						<th>评价</th>
					</tr>
				<s:iterator var="orderItem" value="model.orderItems">
					<tr>
						<td width="60">
							<img src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>" />
						</td>
						<td width="240">
							<a target="_blank"><s:property value="#orderItem.product.pname" /></a>
						</td>
						<td width="100"><s:property value="#orderItem.product.shop_price" />元/件</td>
						<td class="quantity" width="60">
							<s:property value="#orderItem.count" />
						</td>
						<td width="100"><span class="subtotal">￥<s:property value="#orderItem.subtotal" /></span></td>
						<td width="260">
							<input class="button blue" type="button" value="评价" onclick="DoComment(<s:property value="#orderItem.product.pid" />)" id="but<s:property value="#orderItem.product.pid" />"/> 
							<%-- <div id="div<s:property value="#orderItem.product.pid" />"></div> --%>
						</td>
					</tr>
					<tr>
						<td width="260" colspan="6">
							<div id="div<s:property value="#orderItem.product.pid" />"></div>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<hr/>
		</div>

	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
