<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html>
<head>
<title>500 - 系统内部错误</title>
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
		<img src="${ctx}/static/images/500page.jpg"
			class="img-responsive center-block" alt="Responsive image">
		<h2 class="text-center">
			系统发生内部错误...所以您需要<a href="javascript :;" onClick="javascript :history.back(-1);">返回之前</a>
		</h2>
	</div>
	<%
		out.println("<h3>" + exception.toString() + "</h3>");
	%>
</body>
</html>
