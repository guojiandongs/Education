<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setStatus(200);
%>

<!DOCTYPE html>
<html>
<head>
<title>404 - 您要访问的页面不存在</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
<script type="text/javascript"
	src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
			<img src="${ctx}/static/images/404page.jpg" class="img-responsive center-block"
				alt="Responsive image">
			<h2  class="text-center">
				您要访问的页面不存在...所以您需要<a href="<c:url value="${ctx}/portal"/>">返回首页</a>
			</h2>
	</div>
</body>
</html>