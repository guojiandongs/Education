<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增资讯</c:when>
		<c:when test="${action=='update'}">编辑资讯</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/jquery-validation/validate.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/messages_zh.js" ></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/additional-methods.min.js" ></script>
	<link rel="stylesheet" href="${ctx}/static/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/static/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx}/static/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${ctx}/static/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/static/kindeditor/plugins/code/prettify.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">首页管理</a></li>
			<li><a href="${ctx}/infor">资讯分类</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增资讯</c:when>
					<c:when test="${action=='update'}">编辑资讯</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/infor/${action}" method="post" enctype="multipart/form-data">
				<legend>资讯</legend>
				<input type="hidden" name="id" value="${info.id}" />
				
				<div class="form-group">
					<label class="col-sm-2 control-label">资讯名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name"
							value="${info.name}" placeholder="请输入资讯名称" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">资讯内容</label>
					<input type="hidden" id="content" name="content"/>
					<div class="col-sm-8">
					  <textarea class="form-control required" name="contents" id="idcontent"
							 placeholder="请输入新闻内容" >${info.content}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">主题分类</label>
					<div class="col-sm-8">
						<select class="form-control required" name="subjectid">
							<option value="">请选择</option>
							<c:forEach var="s" items="${subjectClasslist}">
							    <option <c:if test="${info.subjectid == s.id}"> selected="selected"</c:if> value="${s.id}">${s.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">所属分区</label>
					<div class="col-sm-8">
						<select class="form-control required" name="infornationtypeid">
							<option value="">请选择</option>
							<c:forEach var="i" items="${informationTypelist}">
							    <option <c:if test="${info.infornationtypeid == i.id}"> selected="selected"</c:if> value="${i.id}">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="status">
							<option
								<c:if test="${info.status == '1'}"> selected="selected"</c:if>
								value="1">正常</option>
							<option
								<c:if test="${info.status == '0'}"> selected="selected"</c:if>
								value="0">停用</option>
						</select>
					</div>
				</div>
				<div class="form-group">
				<label class="col-sm-2 control-label">主题图片</label>
				<input class="col-sm-6" type="file" name="file" onchange='PreviewImage(this)'/> 
				<div class="col-sm-8" id="imgPreview" style='display: none'> 
					<img src="" id="pathRel"/> 
				</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button onclick="submitimformation();" class="btn btn-primary" >保存</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
	
		function submitimformation(){
			$('#idcontent').text(editor1.html());
			var content = editor1.html();
			$('#content').val(content);
			//$("#inputForm").submit();
		}
	
	
		$(document).ready(function(){
			$("#inputForm").validate();
		var action = "${action}";
		//为inputForm注册validate函数
			if (action == "create") {
				//自定义验证
			}else{
				$("#imgPreview").show();
				var info = '${info.apFileEnclosure}';
				if(info){
					var pathRel = '${info.apFileEnclosure.pathRel}';
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
		
		
		KindEditor.ready(function(K) {
  		editor1 = K.create('textarea[name="contents"]', {
  		/*cssPath : '${ctx}/static/kindeditor/plugins/code/prettify.css',*/
  		uploadJson : '${ctx}/static/kindeditor/jsp/upload_json.jsp',
  		fileManagerJson : '${ctx}/static/kindeditor/jsp/file_manager_json.jsp', 
  		allowFileManager : true,
  		afterCreate : function() {
		  		var self = this;
		  		 K.ctrl(document, 13, function() {
		  		self.sync();
		  		document.forms['example'].submit();
		  		});
		  		K.ctrl(self.edit.doc, 13, function() {
		  		self.sync();
		  		document.forms['example'].submit();
		  		}); 
	  		}
  		});
  		
  		prettyPrint();
  	});
	</script>
</body>
</html>