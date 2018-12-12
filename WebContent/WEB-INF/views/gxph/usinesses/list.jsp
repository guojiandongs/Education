<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>广告</title>
<script language="JavaScript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">客户管理</a></li>
			<li class="active">商户管理</li>
		</ol>
		<div class="panel-body">
			<form class="form-inline" role="form">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input class="form-control" name="search_LIKE_name" type="text"
							placeholder="输入名称" value="${param.search_LIKE_name}">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">状态</div>
						<select class="form-control" name="search_EQ_status">
							<option value="">请选择</option>
							<option <c:if test="${param.search_EQ_status == '0'}"> selected="selected"</c:if> value="0">未审核</option>
							<option <c:if test="${param.search_EQ_status == '1'}"> selected="selected"</c:if> value="1">已通过</option>
							<option <c:if test="${param.search_EQ_status == '2'}"> selected="selected"</c:if> value="2">未通过</option>
						</select>
					</div>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
				<button type="button" class="btn btn-default" onclick="rest()">重置</button>
				<div class="pull-right">
					<div class="btn-group">
						<a href="${ctx}/usinesses/create" role="button"
							class="btn btn-primary">新增商户</a>
					</div>
				</div>
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
						<th data-defaultsort='disabled' class="col-md-1 col-xs-1">操作</th>
						<th>名称</th>
						<th>登陆名</th>
						<th>状态</th>
						<th>注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${usinesses.content}" var="us">
						<tr>
							<td><c:if test="${us.status != '1'}"><a href="${ctx}/usinesses/update/${us.id}">编辑</a></c:if>
							<a href="javascript:void(0)"
								onclick="openDeleteModal('${us.id}')">删除</a>
							</td>
							<td>${us.name}</td>
							<td>${us.username}</td>
							<td><c:if test="${us.status == '0'}">未审核</c:if>
								<c:if test="${us.status == '1'}">已通过</c:if>
								<c:if test="${us.status == '2'}">未通过</c:if>
							</td>
					       <td><fmt:formatDate value="${us.regtime}"
									pattern="yyyy-MM-dd  HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${usinesses.content.size()==0||usinesses.content.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
		</div>
		<c:if test="${usinesses.content.size()>0}">
			<tags:sort />
			<tags:pagination page="${usinesses}" paginationSize="5" />
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
						onclick="deletead()">我确定</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function openDeleteModal(code) {
			$('#deleteModal').modal();
			$('#deletecode').text(code);
		}
		function deletead() {
			$('#deleteModal').modal('hide');
			window.location.href = '${ctx}/usinesses/delete/'
				+ $('#deletecode').text();
		}
		function rest(){
			window.location.href = '${ctx}/usinesses/'
		}
	</script>
</body>
</html>