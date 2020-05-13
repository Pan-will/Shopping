<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<title>商城管理中心</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- <link href="${pageContext.request.contextPath }/css/general.css"
	rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" type="text/css" /> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/publicjs.js"></script>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" rel="stylesheet">
<!-- jquery -->
<link href="${pageContext.request.contextPath }/js/jquery-1.8.3.js" rel="stylesheet">


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

<body style="background: #278296">
	<form method="post" target="_parent" action="${pageContext.request.contextPath }/AdminIndex_login.action" onsubmit="return CheckLogin()">
			<table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
				<tr>
					<th colspan="2" height="20px">
						<span style="font-size:14px;color:red;"><s:actionmessage /><s:actionerror /></span>
					</th>
				</tr>
				<tr>
					<td>管理员账户：</td>
					<td>
						<input type="text" name="username" id="username" onblur="validateUser()"/>
						<span id="usernameError"></span>
					</td>
				</tr>
				<tr>
					<td height="20px">&nbsp;</td>
				</tr>
				<tr>
					<td>管理员密码：</td>
					<td>
						<input type="password" id="password" name="password" onblur="CheckPsw()"/>
						<span id="passwordError"></span>
					</td>
				</tr>
				<tr>
					<td height="20px">&nbsp;</td>
					<td height="20px">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" height="20px" align="center">
						<input type="submit" class="btn btn-default" value="登 录"/>
					</td>
				</tr>
				<tr>
					<td height="20px">&nbsp;</td>
					<td height="20px">&nbsp;</td>
				</tr>

				<tr>
					<td colspan="2" align="left">
						<a href="${pageContext.request.contextPath }/index.action" style="color:white">返回首页</a>
					</td>
					<td><a href="javascript:void(0)" style="color:white">忘记密码</a></td>
				</tr>
			</table>
		<input type="hidden" name="act" value="signin" />
	</form>
</body>