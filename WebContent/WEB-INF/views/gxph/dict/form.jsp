<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增字典</c:when>
		<c:when test="${action=='update'}">编辑字典</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/jquery-validation/validate.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/messages_zh.js" ></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/additional-methods.min.js" ></script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">系统设置</a></li>
			<li><a href="${ctx}/dict">字典</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增字典</c:when>
					<c:when test="${action=='update'}">编辑字典</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/dict/${action}" method="post">
				<legend>字典信息</legend>
				<input type="hidden" name="id" value="${dict.id}" />
				<div class="form-group">
				    <label class="col-sm-2 control-label">字典编号</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control required" name="word"
							 placeholder="请输入字典编号" autofocus value="${dict.word}"
						/> 
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">编号</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="code"
							value="${dict.code}" placeholder="请输入字典编号" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">字典名称</label>
					<div class="col-sm-8">
							 <input type="text" class="form-control required" name="value"
							value="${dict.value}" placeholder="请输入字典名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">字典描述</label>
					<div class="col-sm-8">
							 <input type="text" class="form-control required" name="des"
							value="${dict.des}" placeholder="请输入字典描述">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="submit" class="btn btn-primary" >保存</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			$("#inputForm").validate();
		});
	</script>
</body>
</html>