<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增商户</c:when>
		<c:when test="${action=='update'}">编辑商户</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/jquery-validation/validate.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/messages_zh.js" ></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/additional-methods.min.js" ></script>
	<script>
		$(document).ready(function() {
			$("#inputForm").validate({  
	            rules : {  
	                "username" : {  
	                    required : true,  
	                    checknames : true//期望的是true,如果为false则展示提示信息  
	                }  
	            }});
			
			$.validator.addMethod("checknames", function(value) {
			var name = '${usinesses.username}';
	 			$.ajax({
	 				url: '${ctx}/user/checkUsername', //存在输出1，不存在输出0
	 				data: "username="+value+"&name="+name,
	                async: false, /////////关键，设置为同步
	                type: 'POST',
	                dataType: 'text',
	                success: function (data) {
	                    ok = data == 1 ? false : true;
	                },
	                error: function (xhr) {
	                    alert('动态页有问题！\n' + xhr.responseText);
	                    ok = false;
	                }
	 			});
	    		return ok;
			}, '账号已存在');
		});
	</script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">客户管理</a></li>
			<li><a href="${ctx}/usinesses">商户管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增商户</c:when>
					<c:when test="${action=='update'}">编辑商户</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body"><input type="hidden" id="uploadtoken" value="${uploadtoken}" />
			<form id="inputForm" class="form-horizontal" role="form"
			action="${ctx}/usinesses/${action}" method="post" enctype="multipart/form-data" >
				<legend>商户信息</legend>
				<input type="hidden" name="id" value="${usinesses.id}" />
				<input type="hidden" name="status" value="${usinesses.status}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name" maxlength="50"
							 placeholder="请输入名称" autofocus value="${usinesses.name}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" id="username" name="username"
							 placeholder="请输入登陆名" value="${usinesses.username}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登陆密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control required" id="upassword" name="upassword"
							 placeholder="请输入密码" value="${usinesses.upassword}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">简介</label>
					<div class="col-sm-8">
						<textarea class="form-control" name="describes" maxlength="500"
							 placeholder="请输入简介">${usinesses.describes}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-8">
						<textarea class="form-control" name="address" maxlength="500"
							 placeholder="请输入地址">${usinesses.address}</textarea>
					</div>
				</div>
				<%-- <div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="status">
							<option
								<c:if test="${usinesses.status == '0'}"> selected="selected"</c:if>
								value="0">未审核</option>
							<option
								<c:if test="${usinesses.status == '1'}"> selected="selected"</c:if>
								value="1">已通过</option>
							<option
								<c:if test="${usinesses.status == '2'}"> selected="selected"</c:if>
								value="1">未通过</option>
						</select>
					</div>
				</div> --%>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="submit" class="btn btn-primary" onclick="submits()">保存</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
	
</body>
</html>