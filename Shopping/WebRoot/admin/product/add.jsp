<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" rel="stylesheet">
<!-- jquery -->
<link href="${pageContext.request.contextPath }/js/jquery-1.8.3.js" rel="stylesheet">
</meta>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_save.action" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong>添加商品</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input class="form-control" type="text" name="pname" value="" id="" class="bg" />
					</td>
					<td width="18%" colspan="2">
						&nbsp;
					</td>
				</tr>
				
				<tr>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属二级分类：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<select name="categorySecond.csid">
							<s:iterator var="cs" value="csList">
								<option value='<s:property value="#cs.csid" />'><s:property value="#cs.csname"/></option>
							</s:iterator>
						</select>
					</td>
				</tr>
				
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品市场价格：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input class="form-control" type="text" name="market_price" value="" id="" class="bg" />
					</td>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						乐购价格：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input class="form-control" type="text" name="shop_price" value="" id="" class="bg" />
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						此商品是否热门？
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<select name="is_hot">
							<option value="1"/>是</option>
							<option value="0"/>否</option>
						</select>
					</td>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="file" name="upload" />
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品描述：
					</td>
					<td class="ta_01" bgColor="#ffffff" rolspan="5">
						<textarea rows="5" cols="27" name="pdesc"></textarea>
					</td>
					<td width="18%">&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
				
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button class="btn btn-primary" type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="微软雅黑">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="btn btn-primary">&#37325;&#32622;</button>
						<FONT face="微软雅黑">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>