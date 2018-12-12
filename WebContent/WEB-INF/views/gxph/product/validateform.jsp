<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增产品</c:when>
		<c:when test="${action=='update'}">审核产品</c:when>
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
			<li><a href="${ctx}/product">产品管理</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增产品</c:when>
					<c:when test="${action=='update'}">编辑产品</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/product/validatesubmits/${product.id}" method="post">
				<legend>产品</legend>
				<input type="hidden" name="id" value="${product.id}" />
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
						<input type="text" class="form-control required" name="marketprice"
							value="${product.marketprice}" placeholder="请输入产品标签" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品单价</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="sanprice"
							value="${product.sanprice}" placeholder="请输入产品单价" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买人数</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="buynumber"
							value="${product.buynumber}" placeholder="请输入购买人数" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">库存数量</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="store"
							value="${product.store}" placeholder="请输入库存数量" 
							/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单次购买数量</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="singlenumber"
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
					<label for="inputPassword3" class="col-sm-2 control-label">审核状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="mark" onchange="reason(this);">
							<option
								<c:if test="${usinesses.status == '0'}"> selected="selected"</c:if>
								value="0">通过</option>
							<option
								<c:if test="${usinesses.status == '1'}"> selected="selected"</c:if>
								value="1">不通过</option>
						</select>
					</div>
				</div>
				<div class="form-group" style="display: none" id="reason">
					<label class="col-sm-2 control-label">原因</label>
					<div class="col-sm-8">
					  <textarea class="form-control required" name="reasons"
							 placeholder="请输入原因" >${reason}</textarea>
					</div>
				</div>
				<c:if test="${product.issingular=='0'&&product.status=='1'}">
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">费率设置</label>
						<div class="col-sm-8">
						  	<button type="button" class="btn btn-primary" onclick="ratesetting(${product.id});">设置</button>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-sm-2 control-label">平台服务费(单位0.01)</label>
						<div class="col-sm-8">
							<input type="text" class="form-control required digits" name="servicepropercent"
									placeholder="请输入平台服务费" 
									/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">退款手续费(单位0.01)</label>
						<div class="col-sm-8">
							<input type="text" class="form-control required digits" name="returnpropercent"
									placeholder="请输入退款手续费" 
									/>
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="submit" class="btn btn-primary" >审核</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
			<!-- 费率设置模态窗口 -->
			<div class="modal fade" id="rateModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<table
				class="table table-bordered table-hover table-striped sortable">
				<thead>
					<tr>
						<th data-defaultsort='disabled' class="col-md-1.5">操作</th>
						<th>费用名称</th>
						<th>费用收取方式</th>
						<th>计费策略</th>
						<th></th>
						<th>单价</th>
						<th>状态</th>
						<th>注册人员</th>
						<th>注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productList.content}" var="pdl">
						<tr>
							<td><a href="${ctx}/product/update/${pdl.id}">编辑</a>
								<a href="javascript:void(0)" onclick="openDeleteModal('${pdl.id}')">删除</a>
								<a href="${ctx}/product/picupload/${pdl.id}">上传图片</a>
								<c:if test="${pdl.status=='0'}">
								<a href="javascript:void(0)" onclick="openonpreheatModal('${pdl.id}')">预热</a></c:if>
								<c:if test="${pdl.status=='2'}">
								<a href="javascript:void(0)" onclick="openonlineModal('${pdl.id}')">上线</a></c:if>
								<c:if test="${pdl.status=='4'}">
								<a href="javascript:void(0)" onclick="openofflineModal('${pdl.id}')">下架</a></c:if>
							</td>
							<td>${pdl.name}</td>
							<td><c:forEach items="${subjectClasslist}" var="s">
									<c:choose>
										<c:when test="${s.id==pdl.subjectid}">
											${s.name}
										</c:when>
									</c:choose>
								</c:forEach></td>
								<td><c:forEach items="${informationTypelist}" var="i">
									<c:choose>
										<c:when test="${i.id==pdl.informationtypeid}">
											${i.name}
										</c:when>
									</c:choose>
								</c:forEach></td>
								<td><c:forEach items="${areaList}" var="a">
									<c:choose>
										<c:when test="${a.code==pdl.areacode}">
											${a.name}
										</c:when>
									</c:choose>
								</c:forEach></td>
							<td>${pdl.sanprice}</td>
							<td style="color: #c90000"><c:if test="${pdl.status == '0'}">未审核</c:if>
							<c:if test="${pdl.status == '1'}">预热审核</c:if>
							<c:if test="${pdl.status == '2'}">已预热</c:if>
							<c:if test="${pdl.status == '3'}">上线审核</c:if>
							<c:if test="${pdl.status == '4'}">已上线</c:if>
							<c:if test="${pdl.status == '5'}">下架审核</c:if>
							<c:if test="${pdl.status == '6'}">已下架</c:if>
							<c:if test="${pdl.status == '7'}">审核不通过</c:if></td>
							<td>${pdl.recordusername}</td>
							       <td><fmt:formatDate value="${pdl.recordtime}"
									pattern="yyyy-MM-dd  HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">算了</button>
							<button type="button" class="btn btn-primary" id="confirm"
								onclick="offlineSubject()">我确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function ratesetting(productid){
			window.location.href='${ctx}/product/delete/'+productid;
		}
		function reason(value){
			if(value.value=='1'){
				$("#reason").show();
			}else{
				$("#reason").hide();
			}
		}
	
		$(document).ready(function(){
			$("#inputForm").validate();
			$('.form_datetime').datetimepicker({
		         weekStart: 1,  
		         autoclose: true,  
		         startView: 2,  
		         minView: 2,  
		         forceParse: false,  
		         language: 'zh-CN'  
	    	});  
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