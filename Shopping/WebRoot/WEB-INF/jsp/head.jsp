<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
			<s:if test="#session.existUser == null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_loginPage.action">登录</a> | 
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_registPage.action">注册</a> | 
				</li>
				<li><a href="javascript:void(0)">会员中心</a> | </li>
				<li><a href="javascript:void(0)">购物指南</a> | </li>
				<li><a href="javascript:void(0)">关于我们</a> | </li>
				<li><a href="${pageContext.request.contextPath}/AdminIndex_loginPage.action">管理员</a></li>
			</s:if>
			<s:else>
				<li id="headerUsername" class="headerUsername" style="display: list-item;">
					<s:property value="#session.existUser.name" /> | 
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/order_FindByUid.action?page=1">我的订单</a> | 
				</li>
				<li><a href="javascript:void(0)">会员中心</a> | </li>
				<li><a href="javascript:void(0)">购物指南</a> | </li>
				<li id="headerUsername" class="headerUsername" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user_quit.action">【退出】</a>
				</li>
			</s:else>
			
			
		</ul>
	</div>
	<div class="cart">
		<a href="${pageContext.request.contextPath}/cart_MyCart.action">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>15097686925</strong>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${pageContext.request.contextPath}/index.action">首页</a> |</li>
		<s:iterator var="c" value="#session.Clist">
			<li><a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a> |</li>
		</s:iterator>
	</ul>
</div>