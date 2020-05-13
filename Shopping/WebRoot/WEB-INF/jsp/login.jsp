<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/publicjs.js"></script>

<style type="text/css">
	/*错误提示框样式*/
	.infoError{
			color: red;
	  		font-style: italic;
	  		background-color: #FFFFCD;
			font-size: 12px;
			padding: 3px;
			width: 200px;
			height: 14px;
			border: 2px #E7C437 solid;
	}
	/*错误提示框正确验证时的样式*/
	.infoTrue{
			color: green;
	  		font-style: italic;
	  		font-size: 12px;
	  		border: 2px #E7C437 solid;
	  		background-color: #FFFFCD;
	}
</style>
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
		<%@ include file="head.jsp"%>
	</div>
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg"
					width="500" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<form id="loginForm" action="${pageContext.request.contextPath}/user_login.action" method="post" novalidate="novalidate" onsubmit="return CheckLogin()">
						<table>
							<tbody>
								<tr>
									<th>用户名/E-mail:</th>
									<td>
										<input type="text" id="username" name="username" class="text" maxlength="20" onblur="validateUser()" />
										<span id="usernameError"></span>
									</td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td>
										<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off" onblur="CheckPsw()" />
										<span id="passwordError"></span>
									</td>
								</tr>
								
								<tr>
									<th>&nbsp;</th>
									<td>
									<label> 
									<input type="checkbox" id="isRememberUsername" name="isRememberUsername"
											value="true">记住用户名
									</label>
									<label>&nbsp;&nbsp;
									<a href="javascript:void(0)">找回密码</a>&nbsp;&nbsp;
									</label></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
										<input type="submit" class="submit" value="登 录" />
									</td>
									<span style="font-size:14px;color:red;"><s:actionmessage /></span>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！ <a href="${pageContext.request.contextPath}/user_registPage.action">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="foot.jsp" %>

</body>
</html>