<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">新增字典</c:when>
		<c:when test="${action=='update'}">编辑字典</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">系统设置</a></li>
			<li><a href="${ctx}/sys/dict">字典</a></li>
			<li class="active"><c:choose>
					<c:when test="${action=='create'}">新增字典</c:when>
					<c:when test="${action=='update'}">编辑字典</c:when>
					<c:otherwise></c:otherwise>
				</c:choose></li>
		</ol>
		<div class="panel-body">
			<form id="inputForm" class="form-horizontal" role="form">
				<legend>字典信息</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">字典编号</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="keyword" id="keyword"
							 placeholder="请输入字典编号" autofocus required
							<c:if test="${action=='update'}">readonly</c:if>/> <span
							class="help-block">字典编号不允许使用汉字和符号</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">字典名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control required" name="des" id="des" required
							 placeholder="请输入字典名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">字典列表</label>
					<div class="col-sm-8">
						<table id="table" class="table table-bordered text-center">
							<thead>
								<th></th>
							   	<th>显示名</th>
								<th>值</th>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
						<button type="button" class="btn btn-default" id="addBtn">
 							 <span class="glyphicon glyphicon-plus" style="color: #5cb85c"></span> 增加
						</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="button" class="btn btn-primary" onclick="submitt()">保存</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			var action = "${action}";
			if(action =='update')
			{
				var obj = ${json};
				$("#keyword").val(obj.keyword);
				$("#des").val(obj.des);
				rendertlist(obj);
			}
		
			$("#addBtn").click(function() {
				var template = "<tr><td><i class='glyphicon glyphicon-remove' style='color: #d9534f' title='删除'></i></td><td><input id='value' class='form-control' type='text'/></td><td> <input class='form-control' id='code' type='text'/></td></tr>";
				$("#tbody").append(template);
				
				$('tbody td:first-child').on('click',function() {
					 $(this).parent().remove();  
				});
			});
			
			$('tbody td:first-child').on('click',function() {
				 $(this).parent().remove();  
			});
		});
		function rendertlist(o)
		{
			var template = "{{#list}}<tr><td><i class='glyphicon glyphicon-remove' style='color: #d9534f' title='删除'></i></td><td><input id='value' class='form-control' type='text' value={{value}} /></td><td> <input class='form-control' id='code' type='text' value={{code}} /></td></tr>{{/list}}";
			var output = Mustache.render(template, o);
			$("#tbody").html(output);
		}
		
		function submitt()
		{
			if($("#keyword").val()=="")
			{
				alert("字典编号必填");
			}else if($("#des").val()==""){
				alert("字典名称必填");
			}else{
				save();
			}
			
		}
		
		function save()
		{
			var arr = new Array();
			$("#tbody tr").each(function() {
				var v = $(this).find("#code").val();
				var r =  /^[1-9]\d*$/;
				if(r.test(v))
				{
					var o = {"code":$(this).find("#code").val(),"value":$(this).find("#value").val()};
					arr.push(o);
				}
				else{
					alert("值只能为正整数");
				}
			});
			
			if(arr.length<=0)
			{
				alert("字典数据项不能为空");
			}
			else{
				var obj = {keyword: $("#keyword").val(),des: $("#des").val(),list:arr};
				
				var jsonstr = JSON.stringify(obj);
				
				$.ajax({
					type : 'POST',
					url : "${ctx}/sys/dict/save",
					data : {
						data : jsonstr
					},
					success : function(data) {
						if (data == 'success')
							window.location.href = "${ctx}/sys/dict";
						else
							alert(data);
					}
				});
			}
			
		}
		
		
		
		
	</script>
</body>
</html>