<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>山西旅游</title>
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>

<script type="text/javascript">
	$(function(){
		//导航切换
		$(".menuson li").click(function(){
			$(".menuson li.active").removeClass("active");
			$(this).addClass("active");
		});
		
		$('.title').click(function(){
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if($ul.is(':visible')){
				$(this).next('ul').slideUp();
			}else{
				$(this).next('ul').slideDown();
			}
		});
	});	
</script>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>功能列表</div>
    
    <dl class="leftmenu">
       <c:forEach items="${resources}" var="rootMenu">
		    <dd>
		    <div class="title">
		    <span><img src="images/leftico01.png" /></span>${rootMenu.coreRes.name}
		    </div>
		    	<ul class="menuson">
		    	<c:forEach items="${rootMenu.coreRes.childcoreReslists}" var="rootMenus">
		    		<li><cite></cite><a href="${ctx}${rootMenus.url}" target="rightFrame">${rootMenus.name}</a><i></i></li>
		    	</c:forEach>
		        </ul>    
		    </dd>
        </c:forEach>
    </dl>
</body>
</html>
