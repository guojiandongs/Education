<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>ä½ç½®ï¼</span>
    <ul class="placeul">
    <li><a href="#">é¦é¡µ</a></li>
    </ul>
    </div>
    
    <div class="mainindex">
    
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="å¤©æ°" /></span>
    <b>Adminæ©ä¸å¥½ï¼æ¬¢è¿ä½¿ç¨ä¿¡æ¯ç®¡çç³»ç»</b>(admin@uimaker.com)
    <a href="#">å¸å·è®¾ç½®</a>
    </div>
    
    <div class="welinfo">
    <span><img src="images/time.png" alt="æ¶é´" /></span>
    <i>æ¨ä¸æ¬¡ç»å½çæ¶é´ï¼2013-10-09 15:22</i> ï¼ä¸æ¯æ¨ç»å½çï¼<a href="#">è¯·ç¹è¿é</a>ï¼
    </div>
    
    <div class="xline"></div>
    
    <ul class="iconlist">
    
    <li><img src="images/ico01.png" /><p><a href="#">ç®¡çè®¾ç½®</a></p></li>
    <li><img src="images/ico02.png" /><p><a href="#">åå¸æç« </a></p></li>
    <li><img src="images/ico03.png" /><p><a href="#">æ°æ®ç»è®¡</a></p></li>
    <li><img src="images/ico04.png" /><p><a href="#">æä»¶ä¸ä¼ </a></p></li>
    <li><img src="images/ico05.png" /><p><a href="#">ç®å½ç®¡ç</a></p></li>
    <li><img src="images/ico06.png" /><p><a href="#">æ¥è¯¢</a></p></li> 
            
    </ul>
    
    <div class="ibox"><a class="ibtn"><img src="images/iadd.png" />æ·»å æ°çå¿«æ·åè½</a></div>
    
    <div class="xline"></div>
    <div class="box"></div>
    
    <div class="welinfo">
    <span><img src="images/dp.png" alt="æé" /></span>
    <b>Uimakerä¿¡æ¯ç®¡çç³»ç»ä½¿ç¨æå</b>
    </div>
    
    <ul class="infolist">
    <li><span>æ¨å¯ä»¥å¿«éè¿è¡æç« åå¸ç®¡çæä½</span><a class="ibtn">åå¸æç®¡çæç« </a></li>
    <li><span>æ¨å¯ä»¥å¿«éåå¸äº§å</span><a class="ibtn">åå¸æç®¡çäº§å</a></li>
    <li><span>æ¨å¯ä»¥è¿è¡å¯ç ä¿®æ¹ãè´¦æ·è®¾ç½®ç­æä½</span><a class="ibtn">è´¦æ·ç®¡ç</a></li>
    </ul>
    
    <div class="xline"></div>
    
    <div class="uimakerinfo"><b>æ¥çUimakerç½ç«ä½¿ç¨æåï¼æ¨å¯ä»¥äºè§£å°å¤ç§é£æ ¼çB/Såå°ç®¡ççé¢,è½¯ä»¶çé¢è®¾è®¡ï¼å¾æ è®¾è®¡ï¼ææºçé¢ç­ç¸å³ä¿¡æ¯ãæ¥æºï¼<a href="http://www.mycodes.net/" target="_blank">æºç ä¹å®¶</a></b></div>
    
    <ul class="umlist">
    <li><a href="#">å¦ä½åå¸æç« </a></li>
    <li><a href="#">å¦ä½è®¿é®ç½ç«</a></li>
    <li><a href="#">å¦ä½ç®¡çå¹¿å</a></li>
    <li><a href="#">åå°ç¨æ·è®¾ç½®(æé)</a></li>
    <li><a href="#">ç³»ç»è®¾ç½®</a></li>
    </ul>
    
    
    </div>
    
    

</body>

</html>
