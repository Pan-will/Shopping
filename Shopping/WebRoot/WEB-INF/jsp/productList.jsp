<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css" />

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
	<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">

			<s:iterator var="c" value="#session.Clist">
				<dl>
					<dt>
						<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
					</dt>
					<s:iterator var="cs" value="#c.categorySeconds">
					<dd>
						<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
					</dd>
					</s:iterator>
				</dl>
			</s:iterator>

			</div>
		</div>
		
		<div class="span18 last">
			<form id="productForm" action="#" method="post">
				<div id="result" class="result table clearfix">
					<ul>
						<!-- 从值栈中取值不用加：# -->
						<s:iterator var="pList" value="pageBean.list">
							<li>
							<a href="${pageContext.request.contextPath}/product_FindByPid.action?pid=<s:property value="#pList.pid" />&page=1"> 
								<img src="${pageContext.request.contextPath}/<s:property value="#pList.image"/>" width="170" height="170" style="display: inline-block;" /> 
									<span style='color:green'><s:property value="#pList.pname"/></span> 
									<span class="price"> 商城价： ￥<s:property value="#pList.shop_price"/>/件 </span>
							</a>
							</li>
						</s:iterator>
					</ul>
				</div>
				
				<div class="pagination">
					<span>第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页</span>
					
					<s:if test="cid != null">
						<!-- 判断若当前页等于首页则前一页、首页图标不显示 -->
						<s:if test="pageBean.page != 1">
							<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="cid"/>&page=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						
						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<!-- 判断若当前页等于选中页则选中页图标取消超链接 -->
							<s:if test="pageBean.page != #i">
							<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
							<span class="currentPage"><s:property value="#i"/></span>
							</s:else> 
						</s:iterator>
						
						<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
						<s:if test="pageBean.page != pageBean.totalPage">
							<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>" class="nextPage">&nbsp;</a> 
							<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>" class="lastPage">&nbsp;</a>
						</s:if>
					</s:if>
					
					<s:if test="csid != null">
						<!-- 判断若当前页等于首页则前一页、首页图标不显示 -->
						<s:if test="pageBean.page != 1">
							<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="csid"/>&page=1" class="firstPage">&nbsp;</a>
							<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page-1"/>" class="previousPage">&nbsp;</a>
						</s:if>
						
						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<!-- 判断若当前页等于选中页则选中页图标取消超链接 -->
							<s:if test="pageBean.page != #i">
							<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
							</s:if>
							<s:else>
							<span class="currentPage"><s:property value="#i"/></span>
							</s:else> 
						</s:iterator>
						
						<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
						<s:if test="pageBean.page != pageBean.totalPage">
							<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>" class="nextPage">&nbsp;</a> 
							<a href="${pageContext.request.contextPath}/product_FindByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>" class="lastPage">&nbsp;</a>
						</s:if>
					</s:if>
					
				</div>
				
			</form>
		</div>
	</div>
	
	
	<%@ include file="foot.jsp" %>
	
</body>
</html>
