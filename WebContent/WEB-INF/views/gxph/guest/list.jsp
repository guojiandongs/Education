<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>游客</title>
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
			<li><a href="">基础数据</a></li>
			<li class="active">游客</li>
		</ol>
		<div class="panel-body">
			<form class="form-inline" role="form">
				<div class="form-group col-sm-3" style="margin-bottom: 15px;">
					<div class="input-group">
						<div class="input-group-addon">微信昵称</div>
						<input class="form-control" name="search_LIKE_nickname" type="text"
							placeholder="输入姓名" value="${param.search_LIKE_nickname}">
					</div>
				</div>
				<div class="form-group col-sm-4" style="margin-bottom: 15px;">
					<div class="input-group">
						<div class="input-group-addon">注册时间</div>
						<div class="input-group date form_datetime"
							data-link-field="bir_inputs" data-link-format="yyyy-mm-dd">
							<input class="form-control col-sm-2" id="bir_inputs"
								name="search_LT_buytimes" value="<fmt:formatDate value="${param.search_LT_nickname}" type='date' pattern="yyyy-MM-dd"/>" type="text" placeholder="请选择时间" readonly/><%-- <fmt:formatDate value='${product.buytime}' type='date' pattern="yyyy-MM-dd"/> --%>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
						<div class="input-group date form_datetime col-sm-3"
							data-link-field="bir_input" data-link-format="yyyy-mm-dd" style="margin-bottom: 15px;">
							<input class="form-control col-sm-2" id="bir_input"
								name="search_GT_buytimes" value="<fmt:formatDate value="${param.search_GT_nickname}" type='date' pattern="yyyy-MM-dd"/>" type="text" placeholder="请选择时间" readonly/><%-- <fmt:formatDate value='${product.buytime}' type='date' pattern="yyyy-MM-dd"/> --%>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
				<div class="form-group col-sm-3" style="margin-bottom: 15px;">
					<div class="input-group">
						<div class="input-group-addon">手机号</div>
						<input class="form-control" name="search_LIKE_mobile" type="text"
							placeholder="输入姓名" value="${param.search_LIKE_mobile}">
					</div>
				</div>
				<div class="form-group col-sm-4" style="margin-bottom: 15px;">
					<div class="input-group">
						<div class="input-group-addon">客户类型</div>
						<select class="form-control" name="search_EQ_usertype">
							<option value="">请选择类型</option>
							<option <c:if test="${param.search_EQ_usertype == '1'}"> selected="selected"</c:if> value="1">消费客户</option>
							<option <c:if test="${param.search_EQ_usertype == '0'}"> selected="selected"</c:if> value="0">游客</option>
						</select>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 15px;">
					<div class="input-group">
						<div class="input-group-addon">账号状态</div>
						<select class="form-control" name="search_EQ_state">
							<option value="">请选择状态</option>
							<option <c:if test="${param.search_EQ_state == '1'}"> selected="selected"</c:if> value="1">停用</option>
							<option <c:if test="${param.search_EQ_state == '0'}"> selected="selected"</c:if> value="0">正常</option>
						</select>
					</div>
				</div>
				
				<button type="submit" class="btn btn-default" style="margin-bottom: 15px;">搜索</button>
				<button type="button" class="btn btn-default" onclick="rest()" style="margin-bottom: 15px;">重置</button>
				<%-- <div class="pull-right">
					<div class="btn-group">
						<a href="${ctx}/guest/create" role="button"
							class="btn btn-primary">新增直客</a>
					</div>
				</div> --%>
			</form>
		</div>
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<strong>成功!</strong>${message}
			</div>
		</c:if>
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover table-striped sortable">
				<thead>
					<tr>
						<th data-defaultsort='disabled' class="col-md-1 col-xs-1" style="text-align: center;">操作</th>
						<!-- <th>姓名</th> -->
						<th style="text-align: center;">昵称</th>
						<th style="text-align: center;">微信标识</th>
						<th style="text-align: center;">手机</th>
						<th style="text-align: center;">省</th>
						<th style="text-align: center;">市</th>
						<th style="text-align: center;">状态</th>
						<th style="text-align: center;">客户类型</th>
						<th style="text-align: center;">注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${guests.content}" var="guest">
						<tr align="center">
							<%--<td>
							<a href="${ctx}/guest/update/${guest.id}">编辑</a>
							 <a href="javascript:void(0)"
								onclick="openDeleteModal('${guest.id}')">删除</a>
							</td> --%>
							<td><a href="${ctx}/guest/update/${guest.id}">资金明细</a></td>
							<td>${guest.nickname}</td>
							<td>${guest.openid}</td>
							<td>${guest.mobile}</td>
							<td>${guest.province}</td>
							<td>${guest.city}</td>
							<td><c:if test="${guest.state == '1'}">停用</c:if>
							<c:if test="${guest.state == '0'}">正常</c:if></td>
							<td><c:if test="${guest.state == '1'}">消费客户</c:if>
							<c:if test="${guest.state == '0'}">游客</c:if></td>
							<td><fmt:formatDate value="${guest.regtime}"
									pattern="yyyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${guests.content.size()==0||guests.content.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
		</div>
		<c:if test="${guests.content.size()>0}">
			<tags:sort />
			<tags:pagination page="${guests}" paginationSize="5" />
		</c:if>
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
						onclick="deleteguest()">我确定</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function openDeleteModal(code) {
			$('#deleteModal').modal();
			$('#deletecode').text(code)
		}
		function deleteguest() {
			$('#deleteModal').modal('hide');
			window.location.href = '${ctx}/guest/delete/'
				+ $('#deletecode').text();
		}
		function rest(){
			window.location.href = '${ctx}/guest/';
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
			$("#inputForm").validate();
		});
	</script>
</body>
</html>