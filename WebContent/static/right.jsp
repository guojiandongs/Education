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
<title></title>
<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.js"></scripxt>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>ä½ç½®ï¼</span>
    <ul class="placeul">
    <li><a href="#">é¦é¡µ</a></li>
    <li><a href="#">æ°æ®è¡¨</a></li>
    <li><a href="#">åºæ¬åå®¹</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>æ·»å </li>
        <li class="click"><span><img src="images/t02.png" /></span>ä¿®æ¹</li>
        <li><span><img src="images/t03.png" /></span>å é¤</li>
        <li><span><img src="images/t04.png" /></span>ç»è®¡</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>è®¾ç½®</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>ç¼å·<i class="sort"><img src="images/px.gif" /></i></th>
        <th>æ é¢</th>
        <th>ç¨æ·</th>
        <th>ç±è´¯</th>
        <th>åå¸æ¶é´</th>
        <th>æ¯å¦å®¡æ ¸</th>
        <th>æä½</th>
        </tr>
        </thead>
        <tbody>
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130908</td>
        <td>çéå¹³å¹åï¼é©¬è±ä¹å£°æå­å­è§è¡ äººæ´»çæ²¡ææ</td>
        <td>admin</td>
        <td>æ±èåäº¬</td>
        <td>2013-09-09 15:05</td>
        <td>å·²å®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink"> å é¤</a></td>
        </tr> 
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130907</td>
        <td>æ¸©å·19åå°å­¦çä¸­æ¯æµé¼»è¡ç»­ï¼å¨è¾¹é¨åä¼ä¸å³å</td>
        <td>uimaker</td>
        <td>å±±ä¸æµå</td>
        <td>2013-09-08 14:02</td>
        <td>æªå®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130906</td>
        <td>ç¤¾ç§é¢:çµå­åå¡ä¿è¿äºåæç»æµç»æåç¤¾ä¼è½¬å</td>
        <td>user</td>
        <td>æ±èæ é¡</td>
        <td>2013-09-07 13:16</td>
        <td>æªå®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130905</td>
        <td>æ±è¥¿&quot;å±é¿è¿è§å»ºè±ªå®&quot;ï¼å±é¿æ£è®¨</td>
        <td>admin</td>
        <td>åäº¬å¸</td>
        <td>2013-09-06 10:36</td>
        <td>å·²å®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130904</td>
        <td>ä¸­å½2020å¹´æè¿å¥é«æ¶å¥å½å®¶è¡å</td>
        <td>uimaker</td>
        <td>æ±èåäº¬</td>
        <td>2013-09-05 13:25</td>
        <td>å·²å®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130903</td>
        <td>æ·±å³å°éè½¦é¨å ä¹å®¢æé¸æå¼ 3äººè¢«æ¤å¥é§é</td>
        <td>user</td>
        <td>å¹¿ä¸æ·±å³</td>
        <td>2013-09-04 12:00</td>
        <td>å·²å®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130902</td>
        <td>33æ¬¡å°è¡¨å¡é· ææ°ä¸æ¢ä¸å°å³ä½(å¾)</td>
        <td>admin</td>
        <td>æ¹åé¿æ²</td>
        <td>2013-09-03 00:05</td>
        <td>æªå®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130901</td>
        <td>å»æ£å³ç³»ï¼å»çå¨æ¿æ¹é©ä¸å½»åºèé»é</td>
        <td>admin</td>
        <td>æ±èåäº¬</td>
        <td>2013-09-02 15:05</td>
        <td>æªå®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>
        
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>20130900</td>
        <td>å±±ä¸ç« ä¸å¬è½¦è¿é¥­åºæ¯ç¹å°èªå¨åçæ§ç³»ç»æ¥è­¦</td>
        <td>uimaker</td>
        <td>å±±ä¸æ»¨å·</td>
        <td>2013-09-01 10:26</td>
        <td>å·²å®¡æ ¸</td>
        <td><a href="#" class="tablelink">æ¥ç</a>     <a href="#" class="tablelink">å é¤</a></td>
        </tr>        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">å±<i class="blue">1256</i>æ¡è®°å½ï¼å½åæ¾ç¤ºç¬¬&nbsp;<i class="blue">2&nbsp;</i>é¡µ</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>æç¤ºä¿¡æ¯</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>æ¯å¦ç¡®è®¤å¯¹ä¿¡æ¯çä¿®æ¹ ï¼</p>
        <cite>å¦ææ¯è¯·ç¹å»ç¡®å®æé® ï¼å¦åè¯·ç¹åæ¶ã</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="ç¡®å®" />&nbsp;
        <input name="" type="button"  class="cancel" value="åæ¶" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
