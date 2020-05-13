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
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />

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

			<div class="step step1">
				<ul>
					<li class="current"></li>
					<li><h3>我的订单列表</h3></li>
				</ul>
			</div>

			<table>
				<tbody>

					<s:iterator var="orderList" value="pageBean.list">
						<tr>
							<th colspan="2">
								订单编号：<s:property value="#orderList.oid" />
							</th>
							<th colspan="3">
								订单状态： <s:if test="#orderList.state == 1">
								待付款&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/order_FindByOid.action?oid=<s:property value="#orderList.oid" />">
									<font color="red">去付款</font>
								</a>
								</s:if> 
								<s:if test="#orderList.state == 2">
									待发货&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath}/order_CuiDan.action">
										<font color="red">催单</font>
									</a>
								</s:if> <s:if test="#orderList.state == 3">
									已发货&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath}/order_updateState.action?oid=<s:property value="#orderList.oid" />">
										<font color="red">确认收货</font>
									</a>
								</s:if> 
								<s:if test="#orderList.state == 4">
									已完成&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${pageContext.request.contextPath}/order_comment.action?oid=<s:property value="#orderList.oid" />">
										<font color="red">去评价</font>
									</a>
								</s:if>
							</th>
						</tr>


						<tr>
							<th>宝贝模样</th>
							<th>宝贝名称</th>
							<th>宝贝单价/元</th>
							<th>购买数量</th>
							<th>花费小计</th>
						</tr>

						<s:iterator var="orderItem" value="#orderList.orderItems">
							<tr>
								<td width="60">
									<img
									src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>" />
								</td>
								<td><a target="_blank"><s:property
											value="#orderItem.product.pname" /></a></td>
								<td><s:property value="#orderItem.product.shop_price" />元/件</td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" /></td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#orderItem.subtotal" /></span></td>
							</tr>
						</s:iterator>
					</s:iterator>
						<tr>
							<td colspan="5">
								<div class="pagination">
									<span>第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页</span>
									
									<!-- 判断若当前页等于首页则前一页、首页图标不显示 -->
									<s:if test="pageBean.page != 1">
										<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=1" class="firstPage">&nbsp;</a>
										<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
									</s:if>
									
									<s:iterator var="i" begin="1" end="pageBean.totalPage">
										<!-- 判断若当前页等于选中页则选中页图标取消超链接 -->
										<s:if test="pageBean.page != #i">
										<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
										</s:if>
										<s:else>
										<span class="currentPage"><s:property value="#i"/></span>
										</s:else> 
									</s:iterator>
									
									<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
									<s:if test="pageBean.page != pageBean.totalPage">
										<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=<s:property value="pageBean.page+1"/>" class="nextPage">&nbsp;</a> 
										<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=<s:property value="pageBean.totalPage"/>" class="lastPage">&nbsp;</a>
									</s:if>
								</div>
							</td>
						</tr>
				</tbody>
			</table>

		</div>

	</div>
	<%@ include file="foot.jsp"%>
</body>
</html>
