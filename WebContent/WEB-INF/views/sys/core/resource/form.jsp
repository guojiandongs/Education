<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${ctx}/static/jquery-validation/validate.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/messages_zh.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/additional-methods.min.js" ></script>
<title><c:choose>
		<c:when test="${action=='create'}">新增菜单</c:when>
		<c:when test="${action=='update'}">编辑菜单</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">系统管理</a></li>
			<li><a href="${ctx}/coreResource/list">菜单管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增菜单</c:when>
					<c:when test="${action=='update'}">编辑菜单</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/coreResource/save" method="post">
				<legend>菜单信息</legend>
				<input type="hidden" name="id" value="${resource.id}" />
				 
				<div class="form-group">
					<label class="col-sm-2 control-label">资源名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name"
							value="${resource.name}" autofocus placeholder="请输入菜单名称" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否根节点</label>
					<div class="col-sm-8">
						<select id="leaf" class="form-control required"
							name="leaf" onchange="changes(this)" >
							<option <c:if test="${resource.leaf == '0'}"> selected="selected" </c:if>  value="0">根节点</option>
							<option <c:if test="${resource.leaf == '1'}"> selected="selected" </c:if> value="1">子节点</option>
						</select>
					</div>
				</div>
				<div class="form-group" id="parentids">
					<label class="col-sm-2 control-label">所属模块</label>
					<div class="col-sm-8">
						<select id="parentid" class="form-control required"
							name="parentid">
							<c:forEach items="${parentres}" var="par">
								<option <c:if test="${resource.parentid == par.id}"> selected="selected"</c:if> 
									value="${par.id}">${par.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label">排序</label>
					<div class="col-sm-8">
					    <input type="text" class="form-control required" name="xh"
							 placeholder="请输入排序"  value="${resource.xh}"/> 
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">连接地址</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="url"
							value="${resource.url}" placeholder="请输入链接地址" 
							/>
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
			var s = '${action=="create"}';
			if(s=='true'){
				$("#parentids").hide();
			}else{
				var a = '${resource.leaf==0}';
				if(a=='true'){
					$("#parentids").hide();
				}else{
					$("#parentids").show();
				}
			}
		});
		
		function changes(va){
			if(va.value=='1'){
				$("#parentids").show();
			}else{
				$("#parentids").hide();
			}
		}
	</script>
</body>
</html>