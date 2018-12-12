<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>退单</title>
<script language="JavaScript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li class="active">订单管理</li>
			<li>退款订单</li>
		</ol>
		<div class="panel-body">
			<%-- <tags:property /> --%>
			<!-- <div class="col-sm-10"> -->
				<!-- <legend>
					<strong>客户</strong>
				</legend> -->
			<form class="form-inline form-group" role="form">
				<div style="margin-bottom: 15px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">产品名称</div>
							<input class="form-control" name="search_LIKE_order.product.name" type="text"
								placeholder="输入产品名称" value="${productname}">
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">订单号</div>
							<input class="form-control" name="search_LIKE_notest" type="text"
								placeholder="输入订单号" value="${param.search_LIKE_notest}">
						</div>
					</div>
					<%-- <div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">客户名称</div>
							<input class="form-control" name="search_LIKE_order." type="text"
								placeholder="输入订单号" value="${param.search_LIKE_notest}">
						</div>
					</div> --%>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">退单状态</div>
							<select class="form-control" name="search_EQ_step">
								<option value="">请选择状态</option>
								<option <c:if test="${param.search_EQ_state == '1'}"> selected="selected"</c:if> value="0">未审核</option>
								<option <c:if test="${param.search_EQ_state == '2'}"> selected="selected"</c:if> value="1">退款完成</option>
							</select>
						</div>
					</div>
					
					</div>
					<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">退款日期</div>
						<div class="input-group date form_datetime"
							data-link-field="bir_inputs" data-link-format="yyyy-mm-dd">
							<input class="form-control col-sm-2" id="bir_inputs"
								name="search_GTE_returntime" value="${gteodate}" placeholder="请选择时间" readonly/>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
					<div class="input-group date form_datetime col-sm-3"
						data-link-field="bir_input" data-link-format="yyyy-mm-dd">
						<input class="form-control col-sm-2" id="bir_input"
							name="search_LTE_returntime" value="${lteodate}" type="text" placeholder="请选择时间" readonly/>
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar"></span></span>
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
					<button type="button" class="btn btn-default" onclick="rest()">重置</button>
					<%-- <div class="pull-right">
						<div class="btn-group">
							<a href="${ctx}/store/create"
								role="button" class="btn btn-primary">新增票务</a>
						</div>
					</div> --%>
				</form>
				<c:if test="${not empty message}">
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>成功!</strong>${message}
					</div>
				</c:if>
				<c:if test="${not empty fail}">
					<div class="alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<strong>失败!</strong>${fail}
					</div>
				</c:if>
				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>操作</th>
								<th>退单号</th>
								<th>订单号</th>
								<th>产品名称</th>
								<th>退款金额</th>
								<th>姓名</th>
								<th>手机号</th>
								<th>退单状态</th>
								<th>退款日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderreturnlist.content}" var="orderre">
								<tr>
									<td><%-- <a href="${ctx}/order/${order.id}/update">编辑</a>
										<a href="javascript:void(0)" onclick="openDeleteModal('${order.id}')">删除</a> --%>
										<a href="${ctx}/orderreturn/${orderre.id}/detail">详情</a>
										</td>
									<%-- <td><c:if test="${order.utype==1}">分销商</c:if><c:if test="${order.utype==2}">导游</c:if>
									<c:if test="${order.utype==3}">咨客</c:if><c:if test="${order.utype==4}">其他</c:if>
									</td> --%>
									<td>${orderre.notest}</td>
									<td>${orderre.tno}</td>
									<td>${orderre.order.product.name}</td>
									<td>${orderre.returnmoney}</td>
									<td>${orderre.order.buyusername}</td>
									<td>${orderre.order.buyuserphone}</td>
									<td><c:if test="${orderre.state==0}"><label style="color:red;">退款中</label></c:if>
									<c:if test="${orderre.state==1}"><label style="color:green">退款完成</label></c:if>
									<td><fmt:formatDate value="${orderre.returntime}"
									pattern="yyyy-MM-dd" /></td>
									<%-- <td><c:if test="${order.isget==0}">是</c:if><c:if test="${order.isget==1}">否</c:if></td> --%>
									<%-- <td>${order.realdate}</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<c:if
						test="${orderreturnlist.content.size()==0||orderreturnlist.content.size()==null}">
						<label class="center-block text-center">没有数据</label>
					</c:if>
				</div>
				<c:if test="${orderreturnlist.content.size()>0}">
					<tags:sort />
					<tags:pagination page="${orderreturnlist}" paginationSize="5" />
				</c:if>
			<!-- </div> -->
		</div>
	</div>
	<!-- 删除确认模态窗口 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">需要您确认</h4>
				</div>
				<div class="modal-body">该操作不可逆转，删除的数据也无法恢复，您确定要这么做吗？</div>
				<span id="deletecode" style="display: none"></span>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">算了</button>
					<button type="button" class="btn btn-primary" id="confirm"
						onclick="deleteCham()">我确定</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function rest(){
			window.location.href = '${ctx}/orderreturn/';
		}

		$(document).ready(function(){
			$('.form_datetime').datetimepicker({
		         weekStart: 1,  
		         autoclose: true,  
		         startView: 2,  
		         minView: 2,  
		         forceParse: false,  
		         language: 'zh-CN'  
	    	}); 
		});
	</script>
</body>
</html>