<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<form method="post" action="${pageContext.request.contextPath}/comment_save.action">
<table cellspacing="0" cellpadding="1" rules="all"
		bordercolor="gray" border="1" id="DataGrid1"
		style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
	<tr>
		<td align="center">编号</td>
		<td align="center">评价类型</td>
		<td align="center">商品评价</td>
		<td>&nbsp;</td>
	</tr>
	
	<s:iterator var="product" value="product">
		<tr>
			<td align="center" width="10%">
				<s:property value="#product.pid"/>
			</td>
			<td width="20%">
				<input type = "radio" name = "type" value="1"/>好评&nbsp;
				<input type = "radio" name = "type" value="2"/>中评&nbsp;
				<input type = "radio" name = "type" value="3"/>差评
			</td>
			<td align="center" width="50%">
				<textarea id="comment" name="comment" rows="3" cols="70"></textarea>
				<input type="hidden" id="pid" name="product.pid" value="<s:property value="#product.pid"/>" />
			</td>
			<td align="center" width="20%">
				<input type="submit" value="提  交"/>
			</td>
		</tr>
	</s:iterator>
</table>
</form>