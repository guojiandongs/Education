<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">产品图片</c:when>
		<c:when test="${action=='update'}">产品图片</c:when>
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
	<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<style type="text/css">

	    .fileInputContainer{
	        width: 200px;
	        height:200px;
	        background:url(${ctx}/static/images/add_photo.png) no-repeat;
	        margin:30px;
	        position:relative;
	                       /*从content区域开始显示背景background-origin:content; */  
	    	          /*图片上下左右居中background-position:50% 50%;    */  
	    	background-size:contain;                  /*保持图像本身的宽高比例，将图片缩放到宽度或高度正好适应定义背景的区域*/  
	    }
	    .fileInput{
	    	width: 200px;
	        height:200px;
	        font-size: 1px;
	        position:relative;
			right:0;
	        top:0;
	        opacity: 0;
	        filter:alpha(opacity=0);
	        cursor:pointer;
	    }
	</style>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">客户管理</a></li>
			<li><a href="${ctx}/product">产品管理</a></li>
			<li class="active">查看产品图片</li>
		</ol>
		<div class="panel-body" >
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/product/uploadpic/${id}" method="post" enctype="multipart/form-data">
				<legend>上传图片(第一张图片为主显图片)</legend>
				
				<div class="container">
					<div class="fileInputContainer col-sm-4 required" id="appid1">
	    				<input class="fileInput" type="file" name="file1"/>
	    				<input type="hidden" id="file1" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate1" name="picstate" value="false"/>
					</div>
					<div class="fileInputContainer col-sm-4" id="appid2">
	    				<input class="fileInput" type="file" name="file2"/>
	    				<input type="hidden" id="file2" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate2" name="picstate" value="false"/>
					</div>
					<div class="fileInputContainer col-sm-4" id="appid3">
	    				<input class="fileInput" type="file" name="file3"/>
	    				<input type="hidden" id="file3" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate3" name="picstate" value="false"/>
					</div>
				</div>
				<div class="container">
					<div class="fileInputContainer col-sm-4" id="appid4">
	    				<input class="fileInput" type="file" name="file4"/>
	    				<input type="hidden" id="file4" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate4" name="picstate" value="false"/>
					</div>
					<div class="fileInputContainer col-sm-4" id="appid5">
	    				<input class="fileInput" type="file" name="file5"/>
	    				<input type="hidden" id="file5" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate5" name="picstate" value="false"/>
					</div>
					<div class="fileInputContainer col-sm-4" id="appid6">
	    				<input class="fileInput" type="file" name="file6"/>
	    				<input type="hidden" id="file6" name="picid" value="DEFAULT_VALUES"/>
	    				<input type="hidden" id="picstate6" name="picstate" value="false"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<!-- <button type="submit" class="btn btn-primary" >保存</button> -->
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		var json;
		$(document).ready(function(){
			$("#inputForm").validate();
			json = eval('${apFileList}');
			var listsize = '${listsize}';
				if(listsize>0){
					for (var i=0;i<listsize;i++){
						var pathRel = json[i].pathRel;
						var ctx = window.location.href;
						var path = ctx.split("/");
						var rel = "http://"+path[2]+"/"+pathRel;
						var appid = "appid"+(i+1);
						$("#file"+(i+1)).val(json[i].id);
						document.getElementById(appid).style.background = 'url('+rel+')';
					}
				}
		});
	
		function openupload(imgFile,appid){
			var a = appid+"";
			var exp = /.jpg$|.gif$|.png$|.bmp$/;
			var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
			if(!exp.exec(imgFile.value)){ 
				alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");  
				imgFile.focus(); 
			}else{ 
				var path;
				var id = a.slice(5);
				//var id = a.subString(a.length-1,1);
				$("#picstate"+id).val("true");
				if(document.all) { //IE
					imgFile.select(); 
					path = document.selection.createRange().text; 
					console.log(path);
					document.getElementById(appid).style.background = 'url('+path+')';
					//document.getElementById("imgPreview").innerHTML=""; 
					//document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果 
				} else{//FF 
					path = URL.createObjectURL(imgFile.files[0]);
					console.log(path);
					document.getElementById(appid).style.background = 'url('+path+')';
				}
			} 
	  	};
	</script>
</body>
</html>