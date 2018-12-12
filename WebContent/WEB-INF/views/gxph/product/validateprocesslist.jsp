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
			<li><a href="${ctx}/productr">产品管理</a></li>
			<li><a href="">审核流程</a></li>
		</ol>
		<div class="table-responsive">
			<table
				class="table table-bordered table-hover table-striped sortable">
				<thead>
					<tr>
						<th style="text-align: center;">审核状态</th>
						<th style="text-align: center;">审核人</th>
						<th style="text-align: center;">审核时间</th>
						<th style="text-align: center;">备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${validateList.content}" var="vl">
						<tr align="center">
							<td style="color: #c90000"><c:if test="${vl.state == '0'}">预热初审</c:if>
							<c:if test="${vl.state == '1'}">预热复审</c:if>
							<c:if test="${vl.state == '2'}">上线初审</c:if>
							<c:if test="${vl.state == '3'}">上线复审</c:if>
							<c:if test="${vl.state == '4'}">下架初审</c:if>
							<c:if test="${vl.state == '5'}">下架复审</c:if>
							<c:if test="${vl.state == '6'}">审核不通过</c:if></td>
							<td>${vl.recordusername}</td>
					       	<td><fmt:formatDate value="${vl.recordtime}"
									pattern="yyyy-MM-dd  HH:mm" /></td>
							<td>${vl.reason}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${validateList.content.size()==0||validateList.content.size()==null}">
				<label class="center-block text-center">没有数据</label>
			</c:if>
		</div>
		<c:if test="${validateList.content.size()>0}">
					<tags:sort />
					<tags:pagination page="${validateList}" paginationSize="5" />
				</c:if>
    </div>
</body>
</html>