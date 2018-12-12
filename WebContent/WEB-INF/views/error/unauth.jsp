<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setStatus(200);
%>

<!DOCTYPE html>
<html>
<head>
<title>您没有权限进行该操作 - 权限错误</title>
</head>

<body>
	<h2>您没有权限进行该操作 - 权限错误</h2>
	<p>
		<a href="<c:url value="/portal"/>">返回首页</a>
	</p>
</body>
</html>