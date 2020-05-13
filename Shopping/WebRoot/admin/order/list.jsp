<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			/*AJAX异步操作*/
			function showDetail(oid){
				//获得按钮的对象
				var but = document.getElementById("but"+oid);
				//获得div的对象
				var div1 = document.getElementById("div"+oid);
				if(but.value == "订单详情"){
					// 1.创建异步对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								div1.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/adminOrder_findOrderItem.action?oid="+oid+"&time="+new Date().getTime(),true);
					// 4.发送
					xhr.send(null);
					but.value = "关闭";
				} else {
					div1.innerHTML = "";
					but.value="订单详情";
				}
				
			}
			/*创建异步对象的方法*/
			function createXmlHttp(){
				   var xmlHttp;
				   try{ // Firefox, Opera 8.0+, Safari
				        xmlHttp=new XMLHttpRequest();
				    }
				    catch (e){
					   try{// Internet Explorer
					         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					      }
					    catch (e){
					      try{
					         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					      }
					      catch (e){}
					      }
				    }
					return xmlHttp;
				 }
		</script>
	</HEAD>
	<body>
		<br/>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="30px">
										序号
									</td>
									<td align="center" width="55px">
										订单编号
									</td>
									<td align="center" width="70px">
										订单金额<br/>/元
									</td>
									<td align="center" width="80px">
										收货人<br/>联系电话
									</td>
									
									<td align="center" width="80px">
										收货地址
									</td>
									<td align="center" width="65px">
										订单状态
									</td>
									<td align="center" width="45%">
										详情
									</td>
								</tr>
									<s:iterator var="order" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center" >
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#order.oid"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#order.total"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#order.name"/>
												<br/>
												<s:property value="#order.phone"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:property value="#order.address"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center">
												<s:if test="#order.state==1">
													未付款
												</s:if>
												<s:if test="#order.state==2">
													<!-- 在这里点击发货则改变订单状态为3 -->
													<a href="${ pageContext.request.contextPath }/adminOrder_updateState.action?oid=<s:property value="#order.oid"/>"><font color="blue">发货</font></a>
												</s:if>
												<s:if test="#order.state==3">
													待收货
												</s:if>
												<s:if test="#order.state==4">
													已完成<br/><a href="${ pageContext.request.contextPath }/adminOrder_delete.action?oid=<s:property value="#order.oid"/>"><font color="red">删除</font></a>
												</s:if>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<input class="button blue" type="button" value="订单详情" id="but<s:property value="#order.oid"/>" onclick="showDetail(<s:property value="#order.oid"/>)"/>
												<div id="div<s:property value="#order.oid"/>"></div>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
						
					</tr>
					<tr align="center">
						<td class="ta_01" align="center" bgColor="#afd1f3" colspan="7">
							<span>【&nbsp;第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;】</span>
					
						<s:if test="pageBean.page != 1">
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?page=1" class="firstPage"> 首页</a> | 
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?page=<s:property value="pageBean.page-1"/>" class="previousPage">上一页</a>&nbsp;&nbsp;|&nbsp;&nbsp; 
						</s:if>
						<s:else>
							<span>&nbsp;&nbsp;首页&nbsp;&nbsp;|&nbsp;&nbsp;</span>
							<span>上一页&nbsp;&nbsp;|&nbsp;&nbsp;</span>
						</s:else>
						<!-- 判断若当前页等于最后页则后一页、尾页图标不显示 -->
						<s:if test="pageBean.page != pageBean.totalPage">
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?page=<s:property value="pageBean.page+1"/>" class="nextPage">下一页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
							<a style="text-decoration: none;" href="${pageContext.request.contextPath}/adminOrder_findAllByPage.action?page=<s:property value="pageBean.totalPage"/>" class="lastPage">尾页</a>
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

