<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增用户</c:when>
		<c:when test="${action=='update'}">编辑用户</c:when>
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
			<li><a href="">系统管理</a></li>
			<li><a href="${ctx}/user">用户管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增用户</c:when>
					<c:when test="${action=='update'}">编辑用户</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/user/${action}" method="post">
				<legend>用户信息</legend>
				<input type="hidden" name="id" value="${user.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name"
							 placeholder="请输入姓名" autofocus value="${user.name}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">账户名</label>
					<div class="col-sm-8">
                       <input type="text" id="code" class="form-control required" name="code"
							 placeholder="请输入账户名" value="${user.code}"
							 />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">密码</label>
					<div class="col-sm-8">
						<input type="password" class="form-control required" name="pwd"
							value="${user.pwd}" placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机</label>
					<div class="col-sm-8">
						<input type="text" class="form-control " name="mobile"
							value="${user.mobile}" placeholder="请输入11位手机号">
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="mail"
							value="${user.mail}" placeholder="请输入电子邮箱">
					</div>
				</div> --%>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">头像</label>
					<div class="col-sm-7">
						<input type="text" class="form-control " name="headimg"
							value="${user.headimg}" placeholder="上传头像">
					</div>
					<div class="col-sm-1"><button type="button" onclick="" style="">传头像</button></div>
				</div> --%>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">账号状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="status">
							<option
								<c:if test="${user.status == '1'}"> selected="selected"</c:if>
								value="1">可用</option>
							<option
								<c:if test="${user.status == '0'}"> selected="selected"</c:if>
								value="0">不可用</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">账号创建渠道</label>
					<div class="col-sm-8">
						<select class="form-control required" name="createchannel">
						    <option
								<c:if test="${user.createchannel == '3'}"> selected="selected"</c:if>
								value="3">pc web</option>
							<option
								<c:if test="${user.createchannel == '1'}"> selected="selected"</c:if>
								value="1">android</option>
							<option
								<c:if test="${user.createchannel == '2'}"> selected="selected"</c:if>
								value="2">ios</option>
							<option
								<c:if test="${user.createchannel == '4'}"> selected="selected"</c:if>
								value="4">wap</option>
							<option
								<c:if test="${user.createchannel == '5'}"> selected="selected"</c:if>
								value="5"> 微信</option>
						</select>
					</div>
				</div>
				<%-- <div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">账户类型</label>
					<div class="col-sm-8">
						<select class="form-control required" name="type">
							<option
								<c:if test="${user.type == '1'}"> selected="selected"</c:if>
								value="1">免费</option>
							<option
								<c:if test="${user.type == '2'}"> selected="selected"</c:if>
								value="2">付费中</option>
							<option
								<c:if test="${user.type == '3'}"> selected="selected"</c:if>
								value="3">付费过期</option>
						</select>
					</div>
				</div> --%>
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
	$(document).ready(function() {
		//为inputForm注册validate函数
		$("#inputForm").validate({  
            rules : {  
                "code" : {  
                    required : true,  
                    checknames : true//期望的是true,如果为false则展示提示信息  
                }  
            }});
		
		$.validator.addMethod("checknames", function(value) {
			var code = '${user.code}';
 			$.ajax({
 				url: '${ctx}/user/checkUsername', //存在输出1，不存在输出0
 				data: "username="+value+"&name="+code,
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
</body>
</html>