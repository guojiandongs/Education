<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>产品审核</title>
<script language="JavaScript" src="${ctx}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li class="active">客户管理</li>
			<li class="active">产品管理</li>
			<li><a href="">产品审核</a></li>
		</ol>
		<div class="panel-body ">
			<form class="form-inline" role="form">
				<div class="form-group">
					<div class="input-group">
					   <div class="input-group-addon">产品审核名称</div>
					   <input class="form-control" name="search_LIKE_name" type="text"
						  placeholder="输入产品审核名称" value="${param.search_LIKE_name}">
				   </div>
				 </div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">状态</div>
						<select class="form-control" name="search_EQ_status">
							<option value="">请选择</option>
							<option <c:if test="${param.search_EQ_status == '0'}"> selected="selected"</c:if> value="0">未审核</option>
							<option <c:if test="${param.search_EQ_status == '1'}"> selected="selected"</c:if> value="1">预热审核</option>
							<option <c:if test="${param.search_EQ_status == '2'}"> selected="selected"</c:if> value="2">已预热</option>
							<option <c:if test="${param.search_EQ_status == '3'}"> selected="selected"</c:if> value="3">上线审核</option>
							<option <c:if test="${param.search_EQ_status == '4'}"> selected="selected"</c:if> value="4">已上线</option>
							<option <c:if test="${param.search_EQ_status == '5'}"> selected="selected"</c:if> value="5">下架审核</option>
							<option <c:if test="${param.search_EQ_status == '6'}"> selected="selected"</c:if> value="6">已下架</option>
							<option <c:if test="${param.search_EQ_status == '7'}"> selected="selected"</c:if> value="7">审核未通过</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">所属分区</label>
						<select class="form-control" name="search_EQ_informationtypeid"   id="informationtypeid">
							<option value="">请选择</option>
							<c:forEach var="i" items="${informationTypelist}">
							    <option <c:if test="${param.search_EQ_informationtypeid == i.id}"> selected="selected"</c:if> value="${i.id}">${i.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">所属地区</label>
						<select class="form-control" name="search_EQ_areacode"   id="areacode">
							<option value="">请选择</option>
							<c:forEach var="a" items="${areaList}">
							    <option <c:if test="${param.search_EQ_areacode == a.code}"> selected="selected"</c:if> value="${a.code}">${a.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
				<button type="button" class="btn btn-default" onclick="rest()">重置</button>
			</form>
		</div>
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<strong>成功!</strong>${message}
			</div>
		</c:if>
		<c:if test="${not empty fail}">
		    <div class="alert alert-danger alert-dismissible" role="alert">
		       <button type="button" class="close" data-dismiss="alert">
		          <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
		       </button>
		       <strong>失败!</strong>${fail}
		    </div>
		</c:if>
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover table-striped sortable">
				<thead>
					<tr>
						<th data-defaultsort='disabled' class="col-md-1.5" style="text-align: center;">操作</th>
						<th style="text-align: center;">产品名称</th>
						<th style="text-align: center;">主题分类</th>
						<th style="text-align: center;">所属分区</th>
						<th style="text-align: center;">所属地区</th>
						<th style="text-align: center;">单价</th>
						<th style="text-align: center;">状态</th>
						<th style="text-align: center;">注册人员</th>
						<th style="text-align: center;">注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productList.content}" var="pdl">
						<tr align="center">
							<td><c:if test="${pdl.issingular!='2'}"><a href="${ctx}/product/validateform/${pdl.id}">审核</a>
							<a href="${ctx}/product/findpicupload/${pdl.id}">查看图片</a></c:if>
							<a href="${ctx}/product/validatelist/${pdl.id}">审核历史</a></td>
							
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
			<c:if test="${productList.content.size()==0||productList.content.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
		</div>
		<c:if test="${productList.content.size()>0}">
					<tags:sort />
					<tags:pagination page="${productList}" paginationSize="5" />
				</c:if>
    </div>
	<script type="text/javascript">
		
		function rest(){
			window.location.href = '${ctx}/product/productvalidat';
		}
	</script>
</body>
</html>