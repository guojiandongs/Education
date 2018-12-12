<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>字典</title>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">系统设置</a></li>
			<li class="active">字典</li>
		</ol>
		<div class="panel-body">
			<form class="form-inline" role="form">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">编号</div>
						<input class="form-control" name="search_LIKE_keyword" type="text"
							placeholder="输入编号" value="${param.search_LIKE_keyword}">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input class="form-control" type="text" name="search_LIKE_des"
							placeholder="输入名称" value="${param.search_LIKE_des}">
					</div>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
				<div class="pull-right">
					<div class="btn-group">
						<a href="${ctx}/sys/dict/create" role="button"
							class="btn btn-primary">新增字典</a>
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
						<th>编号</th>
						<th>名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dicts}" var="dict">
						<tr>
							<td><a href="${ctx}/sys/dict/update/${dict.keyword}">编辑</a>
							<a href="javascript:void(0)"
								onclick="openDeleteModal('${dict.keyword}')">删除</a></td>
							<td>${dict.keyword}</td>
							<td>${dict.des}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${dicts.size()==0||dicts.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
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
						onclick="deleteUser()">我确定</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function openDeleteModal(code) {
			$('#deleteModal').modal();
			$('#deletecode').text(code)
		}
		function deleteUser() {
			$('#deleteModal').modal('hide');
			window.location.href = '/sys/dict/delete/'
					+ $('#deletecode').text();
		}
	</script>
</body>
</html>