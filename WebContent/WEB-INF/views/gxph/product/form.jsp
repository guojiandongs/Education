<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增产品</c:when>
		<c:when test="${action=='update'}">编辑产品</c:when>
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
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">客户管理</a></li>
			<li><a href="${ctx}/productr">产品管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增产品</c:when>
					<c:when test="${action=='update'}">编辑产品</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/product/${action}" method="post" enctype="multipart/form-data">
				<legend>产品</legend>
				<input type="hidden" name="id" value="${product.id}" />
				<%-- <input type="hidden" name="recorduserid" value="${product.recorduserid}" />
				<input type="hidden" name="recordusername" value="${product.recordusername}" />
				<input type="hidden" name="recordtime" value="${product.recordtime}" /> --%>
				<input type="hidden" name="issingular" value="${product.issingular}" />
				<input type="hidden" name="status" value="${product.status}" />
				<input type="hidden" name="usinessesid" value="${product.usinessesid}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="name"
							value="${product.name}" placeholder="请输入产品名称" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">产品分类</label>
					<div class="col-sm-8">
						<select class="form-control required" name="informationtypeid">
							<c:forEach var="i" items="${informationTypelist}">
							    <option <c:if test="${product.informationtypeid == i.id}"> selected="selected"</c:if> value="${i.id}">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">产品主题</label>
					<div class="col-sm-8">
						<select class="form-control" name="subjectid">
							<option value="">请选择</option>
							<c:forEach var="s" items="${subjectClasslist}">
							    <option <c:if test="${product.subjectid == s.id}"> selected="selected"</c:if> value="${s.id}">${s.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">所属地区</label>
					<div class="col-sm-8">
						<select class="form-control required" name="areacode">
							<c:forEach var="i" items="${areaList}">
							    <option <c:if test="${product.areacode == i.code}"> selected="selected"</c:if> value="${i.code}">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品标签</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="producttag"
							value="${product.producttag}" placeholder="请输入产品标签" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品原价</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required digits" name="marketprice"
							value="${product.marketprice}" placeholder="请输入产品原价" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品单价</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required digits" name="sanprice" 
							value="${product.sanprice}" placeholder="请输入产品单价" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买人数</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required digits" name="buynumber"
							value="${product.buynumber}" placeholder="请输入购买人数" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">库存数量</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required digits" name="store"
							value="${product.store}" placeholder="请输入库存数量" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单次购买数量</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required digits" name="singlenumber"
							value="${product.singlenumber}" placeholder="请输入单次购买数量" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">销售截止时间</label>
					<div class="col-sm-8">
						<div class="input-group date form_datetime"
							data-link-field="bir_input" data-link-format="yyyy-mm-dd">
							<input class="form-control" id="bir_input"
								name="buytimes" value="<fmt:formatDate value='${product.buytime}' type='date' pattern="yyyy-MM-dd"/>" type="text" placeholder="请选择时间" readonly/><%-- <fmt:formatDate value='${product.buytime}' type='date' pattern="yyyy-MM-dd"/> --%>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品内容</label>
					<div class="col-sm-8">
					  <textarea class="form-control required" name="content" id="content"
							 placeholder="请输入产品内容" >${product.content}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品详情</label>
					<input type="hidden" id="productdetail" name="productdetail"/>
					<div class="col-sm-8">
					  <textarea class="form-control required" name="productdetails"
							 placeholder="请输入产品详情" >${product.productdetail}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">预订须知</label>
					<input type="hidden" id="notice" name="notice"/>
					<div class="col-sm-8">
					  <textarea class="form-control required" name="notices"
							 placeholder="请输入预订须知" >${product.notice}</textarea>
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
			var productdetail = editor1.html();
			$('#productdetail').val(productdetail);
			var notice = editor2.html();
			$('#notice').val(notice);
			//$("#inputForm").submit();
		}
	
	
		$(document).ready(function(){
			$('.form_datetime').datetimepicker({
		         weekStart: 1,  
		         autoclose: true,  
		         startView: 2,  
		         minView: 2,  
		         forceParse: false,  
		         language: 'zh-CN'  
	    	});  
			$("#inputForm").validate();
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
		editor2 = K.create('textarea[name="notices"]', {
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
		
  		editor1 = K.create('textarea[name="productdetails"]', {
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