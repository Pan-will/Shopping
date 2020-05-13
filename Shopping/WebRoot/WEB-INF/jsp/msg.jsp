<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center"><table width="60%"
						border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="width:98"><img
								src="${pageContext.request.contextPath}/images/IconTexto_WebDev_009.jpg"
								width="128" height="128" /></td>
							<td style="padding-top:30px"><font
								style="font-weight:bold; color:#FF0000"> 
								<s:actionmessage />
								<s:actionerror />
							</font> <br /> <br /> 
							<a style="text-decoration: none;" href="${ pageContext.request.contextPath }/index.action">【唯唯乐购】首页&nbsp;&nbsp;</a>|
							<a style="text-decoration: none;" href="${ pageContext.request.contextPath }/user_loginPage.action">&nbsp;&nbsp;登录&nbsp;&nbsp;</a>|
							<a style="text-decoration: none;" href="${ pageContext.request.contextPath }/user_registPage.action">&nbsp;&nbsp;注册&nbsp;&nbsp;</a>|
							<a style="text-decoration: none;" onclick='back()'>&nbsp;&nbsp;返回</a>
							<script>
								function back(){
									location.href=history.go(-1);
								}
							</script>
							</td>
						</tr>
					</table>
					<h1>&nbsp;</h1></td>
			</tr>
		</table>
	</div><br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> 
	
	<%@ include file="foot.jsp" %>
	
</body>
</html>