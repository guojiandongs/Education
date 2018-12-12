<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>资讯</title>
<script language="JavaScript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">首页管理</a></li>
			<li><a href="">资讯分类</a></li>
		</ol>
		<div class="panel-body ">
			<form class="form-inline" role="form">
				<div class="form-group">
					<div class="input-group">
					   <div class="input-group-addon">资讯名称</div>
					   <input class="form-control" name="search_LIKE_name" type="text"
						  placeholder="输入资讯名称" value="${param.search_LIKE_name}">
				   </div>
				 </div>
				<div class="form-group">
					<div class="input-group">
					   	<label class="input-group-addon">主题分类</label>
						<select class="form-control" name="search_EQ_subjectid"   id="subjectid">
							<option value="">请选择</option>
							<c:forEach var="s" items="${subjectClasslist}">
							    <option <c:if test="${param.search_EQ_subjectid == s.id}"> selected="selected"</c:if> value="${s.id}">${s.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">所属分区</label>
						<select class="form-control" name="search_EQ_infornationtypeid"   id="infornationtypeid">
							<option value="">请选择</option>
							<c:forEach var="i" items="${informationTypelist}">
							    <option <c:if test="${param.search_EQ_infornationtypeid == i.id}"> selected="selected"</c:if> value="${i.id}">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
				<button type="button" class="btn btn-default" onclick="rest()">重置</button>
				<div class="pull-right">
					<div class="btn-group">
						<a href="${ctx}/infor/create" role="button"
							class="btn btn-primary">新增资讯</a>
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
		<c:if test="${not empty fail}">
		    <div class="alert alert-danger alert-dismissible" role="alert">
		       <button type="button" class="close" data-dismiss="alert">
		          <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		       </button>
		       <strong>失败!</strong>${fail}
		    </div>
		</c:if>
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover table-striped sortable">
				<thead>
					<tr>
						<th data-defaultsort='disabled' class="col-md-1 col-xs-1">操作</th>
						<th>资讯名称</th>
						<th>主题分类</th>
						<th>所属分区</th>
						<th>状态</th>
						<th>操作用户</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${infor.content}" var="info">
						<tr>
							<td><a href="${ctx}/infor/update/${info.id}">编辑</a>
							<a href="javascript:void(0)" onclick="openDeleteModal('${info.id}')">删除</a></td>
							<td>${info.name}</td>
							<td><c:forEach items="${subjectClasslist}" var="s">
									<c:choose>
										<c:when test="${s.id==info.subjectid}">
											${s.name}
										</c:when>
									</c:choose>
								</c:forEach></td>
								<td><c:forEach items="${informationTypelist}" var="i">
									<c:choose>
										<c:when test="${i.id==info.infornationtypeid}">
											${i.name}
										</c:when>
									</c:choose>
								</c:forEach></td>
							<td><c:if test="${info.status == '0'}">停用</c:if>
							<c:if test="${info.status == '1'}">正常</c:if></td>
							<td>${info.recordusername}</td>
							       <td><fmt:formatDate value="${info.recordtime}"
									pattern="yyyy-MM-dd  HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${infor.content.size()==0||infor.content.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
		</div>
		<c:if test="${infor.content.size()>0}">
					<tags:sort />
					<tags:pagination page="${infor}" paginationSize="5" />
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
						onclick="deleteSubject()">我确定</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function openDeleteModal(code) {
			$('#deleteModal').modal();
			$('#deletecode').text(code);
		}
		function deleteSubject() {
			$('#deleteModal').modal('hide');
			window.location.href = '${ctx}/infor/delete/'
				+ $('#deletecode').text();
		}
		function rest(){
			window.location.href = '${ctx}/infor/';
		}
	</script>
</body>
</html>