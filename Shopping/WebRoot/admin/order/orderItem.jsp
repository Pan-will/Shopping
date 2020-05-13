<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table cellspacing="0" cellpadding="1" rules="all"
		bordercolor="gray" border="1" id="DataGrid1"
		style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
	<tr>
		<td align="center">商品图片</td>
		<td align="center">商品名称</td>
		<td align="center">购买数量</td>
		<td align="center">账单小计</td>
	</tr>
	<s:iterator var="orderItem" value="list">
		<tr>
			<td align="center"><img width="40px" height="40px" src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
			<td align="center"><s:property value="#orderItem.product.pname"/></td>
			<td align="center"><s:property value="#orderItem.count"/></td>
			<td align="center"><s:property value="#orderItem.subtotal"/></td>
		</tr>
	</s:iterator>
</table>