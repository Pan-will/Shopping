<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- import struts Tag Library -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>唯唯乐购</title>
<!-- 引入网站logo -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/mycarlogo.ico">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">

</head>
<body>
	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index.action">
					<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/mycar.png" alt="唯唯乐购">
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="image\r___________renleipic_01/header.jpg" alt="正品保障"
					title="正品保障" height="50" width="320">
			</div>
		</div>
		<%@ include file="head.jsp" %>
	</div>
	<div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator var="c" value="#session.product">
				<dl>
					<dt>
						<a href="${pageContext.request.contextPath}/product_FindByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
					</dt>
					<!-- 迭代二级分类 -->
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
			<div class="productImage">
				<a title="" style="outline-style: none; text-decoration: none;"
					id="zoom" href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
					<div class="zoomPad">
						<img style="opacity: 1;" title="商品大图" class="medium" src="${pageContext.request.contextPath}/<s:property value="#session.product.image"/>">
						<div
							style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
							class="zoomPup"></div>
						<div
							style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
							class="zoomWindow">
							<div style="width: 368px;" class="zoomWrapper">
								<div style="width: 100%; position: absolute; display: none;"
									class="zoomWrapperTitle"></div>
								<div style="width: 0%; height: 0px;" class="zoomWrapperImage">
									<img src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg"
										style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;">
								</div>
							</div>
						</div>
						<div
							style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;"
							class="zoomPreload">Loading zoom</div>
					</div>
				</a>
			</div>
			
			<div class="name"><s:property value="#session.product.pname"/></div>
			<div class="sn">
				<div>编号：<s:property value="#session.product.pid"/></div>
			</div>
			<div class="info">
				<dl>
					<dt>乐购价:</dt>
					<dd>
						<strong>￥：<s:property value="#session.product.shop_price"/>元/件</strong> 参 考 价：
						<del>￥<s:property value="#session.product.market_price"/>元/件</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2015-07-30 ~ 2017-01-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			
			<div class="action">
				<dl class="quantity">
					<dt>购买数量:</dt>
					<dd>
						<input type="text" id="quantity" name="count" value="1" maxlength="4" onpaste="return false;" />
						<input type="hidden" name="pid" value="<s:property value="#session.product.pid"/>" />
						<div>
							<span id="increase" class="increase">&nbsp;</span>
							<span id="decrease" class="decrease">&nbsp;</span>
						</div>
					</dd>
					<dd>件</dd>
				</dl>
			</div>
			
			<div id="bar" class="bar">
				<ul>
					<li id="introductionTab"><a href="#introduction">商品评价</a></li>
				</ul>
			</div>

			<div id="introduction" name="introduction" class="introduction">
				<div>
					<!-- 从值栈中取值不用加：# -->
					<s:iterator var="cList" value="#session.pageBean.list" status="status">
							<s:property value="#status.count"/>:
							<s:property value="#cList.user.name"/>:<br/>
							<strong><s:property value="#cList.comment" /></strong>
							<br/><br/>
					</s:iterator>
				</div>
				<!-- 分页 -->
				<div class="pagination">
					<span>第<s:property value="#session.pageBean.page"/>/<s:property value="#session.pageBean.totalPage"/>页</span>
					
					<!-- 判断若当前页等于首页则前一页、首页图标不显示 -->
					<s:if test="#session.pageBean.page != 1">
						<a href="${pageContext.request.contextPath}/comment_findByPid.action?pid=<s:property value="model.pid"/>&page=1" class="firstPage">&nbsp;</a>
						<a href="${pageContext.request.contextPath}/comment_findByPid.action?pid=<s:property value="model.pid"/>&page=<s:property value="#session.pageBean.page-1"/>" class="previousPage">&nbsp;</a>
					</s:if>
					
					<s:iterator var="i" begin="1" end="#session.pageBean.totalPage">
						<!-- 判断若当前页等于选中页则选中页图标取消超链接 -->
						<s:if test="#session.pageBean.page != #i">
							<a href="${pageContext.request.contextPath}/comment_findByPid.action?pid=<s:property value="model.pid"/>&page=<s:property value="#i"/>"><s:property value="#i"/></a>
						</s:if>
						<s:else>
							<span class="currentPage"><s:property value="#i"/></span>
						</s:else> 
					</s:iterator>
					
					<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
					<s:if test="#session.pageBean.page != #session.pageBean.totalPage">
						<a href="${pageContext.request.contextPath}/comment_findByPid.action?pid=<s:property value="model.pid"/>&page=<s:property value="#session.pageBean.page+1"/>" class="nextPage">&nbsp;</a> 
						<a href="${pageContext.request.contextPath}/comment_findByPid.action?pid=<s:property value="model.pid"/>&page=<s:property value="#session.pageBean.totalPage"/>" class="lastPage">&nbsp;</a>
					</s:if>
				</div>
				
			</div>
			
		</div>
	</div>
	
	<%@ include file="foot.jsp" %>
	
</body>
</html>
