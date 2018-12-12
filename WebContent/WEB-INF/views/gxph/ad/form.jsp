<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增广告</c:when>
		<c:when test="${action=='update'}">编辑广告</c:when>
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
		$("#inputForm").validate();
		var action = "${action}";
		//为inputForm注册validate函数
			if (action == "create") {
				//自定义验证
			}else{
				$("#imgPreview").show();
				var ap = '${ad.apFileEnclosure}';
				if(ap){
					var pathRel = '${ad.apFileEnclosure.pathRel}';
					var ctx = window.location.href;
					var path = ctx.split("/");
					var rel = "http://"+path[2]+"/"+pathRel;
					$("#pathRel").attr("src",rel);
				}
			}
	});
	
	function PreviewImage(imgFile){ 
		var exp = /.jpg$|.gif$|.png$|.bmp$/;
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if(!exp.exec(imgFile.value)){ 
			alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
			imgFile.focus(); 
		}else{ 
			var path; 
			if(document.all) { //IE
				imgFile.select(); 
				path = document.selection.createRange().text; 
				document.getElementById("imgPreview").innerHTML=""; 
				document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
			} else{//FF 
				path = URL.createObjectURL(imgFile.files[0]);
				document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>"; 
			} 
			$("#imgPreview").show();
		} 
	} 
	
	
	/* $("#inputForm").validate({
				rules : {
					seq: {
					required : true,
					remote : {
						url : "${ctx}/ad/check",
						type : "post",
						data : {
							seq : function() {
								return $("#seq").val();
							}
						}
					}
				}
			},
			messages : {
				seq : {
					remote : "排序号已存在"
				}
			}
			}); */
	</script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">首页管理</a></li>
			<li><a href="${ctx}/ad">广告管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增广告</c:when>
					<c:when test="${action=='update'}">编辑广告</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body"><input type="hidden" id="uploadtoken" value="${uploadtoken}" />
			<form id="inputForm" class="form-horizontal" role="form"
			action="${ctx}/ad/${action}" method="post" enctype="multipart/form-data" >
				<legend>广告信息</legend>
				<input type="hidden" name="id" value="${ad.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">名称${ad.apFileEnclosures}</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name" maxlength="50"
							 placeholder="请输入名称" autofocus value="${ad.name}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">排序</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" id="seq" name="seq" min="1" maxlength="10"
							 placeholder="请输入排序" value="${ad.seq}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">链接</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="url" name="url"
							 placeholder="请输入链接" value="${ad.url}"
						/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-8">
						<textarea class="form-control" name="memo" maxlength="500"
							 placeholder="请输入备注">${ad.memo}</textarea>
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">类型</label>
					<div class="col-sm-8">
						<select id="type" class="form-control required"
							name="type">
							<c:forEach items="${dicts}" var="type">
								<option <c:if test="${ad.type == type.id}"> selected="selected"</c:if> 
								value="${type.id}">
									<c:out value="${type.value}" />
								</option>
							</c:forEach>
						</select>
					</div>
				</div> --%>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="state">
							<option
								<c:if test="${ad.state == '1'}"> selected="selected"</c:if>
								value="1">正常</option>
							<option
								<c:if test="${ad.state == '0'}"> selected="selected"</c:if>
								value="0">停用</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
				<label class="col-sm-2 control-label">图片</label>
				<input class="col-sm-6" type="file" name="file" onchange='PreviewImage(this)'/> 
				<div class="col-sm-8" id="imgPreview" style='display: none'> 
					<img src="" id="pathRel"/> 
				</div>
				</div>
				
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