<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addCategorySe(){
				window.location.href = "${pageContext.request.contextPath}/adminCategorySecond_addPage.action";
			}
		</script>
	</head>
	<body>
		<br>
		<form id="Form" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品二级分类列表</strong>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addCategorySe()">
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="18%">
										序号
									</td>
									<td align="center" width="17%">
										二级分类名称
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<s:iterator var="cs" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
												<s:property value="#status.count"/>
											</td>
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
												<s:property value="#cs.csname"/>
											</td>
											
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminCategorySecond_edit.action?csid=<s:property value="#cs.csid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminCategorySecond_delete.action?csid=<s:property value="#cs.csid"/>">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<span>【&nbsp;第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;】</span>
					
						<s:if test="pageBean.page != 1">
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminCategorySecond_findAllByPage.action?page=1" class="firstPage"> 首页</a> | 
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminCategorySecond_findAllByPage.action?page=<s:property value="pageBean.page-1"/>" class="previousPage">上一页</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
						</s:if>
						<s:else>
							<span>&nbsp;&nbsp;首页&nbsp;&nbsp;|&nbsp;&nbsp;</span>
							<span>上一页&nbsp;&nbsp;|&nbsp;&nbsp;</span>
						</s:else>
						<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
						<s:if test="pageBean.page != pageBean.totalPage">
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminCategorySecond_findAllByPage.action?page=<s:property value="pageBean.page+1"/>" class="nextPage">下一页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminCategorySecond_findAllByPage.action?page=<s:property value="pageBean.totalPage"/>" class="lastPage">尾页</a>
						</s:if>
						<s:else>
							<span>下一页&nbsp;&nbsp;|&nbsp;&nbsp;</span>
							<span>尾页</span>
						</s:else>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
	</body>
</HTML>

