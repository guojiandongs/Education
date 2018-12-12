<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title><c:choose>
		<c:when test="${action=='create'}">审核商户</c:when>
		<c:when test="${action=='update'}">审核商户</c:when>
		<c:otherwise></c:otherwise>
	</c:choose></title>
	<script language="JavaScript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/static/jquery-validation/validate.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/messages_zh.js" ></script>
	<script type="text/javascript" src="${ctx}/static/jquery-validation/additional-methods.min.js" ></script>
</head>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li><a href="">订单管理</a></li>
			<li><a href="${ctx}/usinesses">订单查询</a></li>
			<li class="active">订单详情</li>
		</ol>
		<div class="panel-body"><input type="hidden" id="uploadtoken" value="${uploadtoken}" />
				<legend>订单信息</legend>
				<input type="hidden" name="id" value="${usinesses.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">订单号</label>
						<label class="control-label">${order.orderno}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">微信支付订单号</label>
						<label class="control-label">${order.wxorderno}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称</label>
						<label class="control-label">${order.product.name}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">商品价格</label>
						<label class="control-label">${order.product.sanprice}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">实际付款</label>
						<label class="control-label">${order.realpay}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买数量</label>
						<label class="control-label">${order.num}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买人姓名</label>
						<label class="control-label">${order.buyusername}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
						<label class="control-label">${order.buyuserphone}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">订单状态</label>
						<label class="control-label">
							<c:if test="${order.step=='1'}"><label style="color:red;">未支付</label></c:if>
							<c:if test="${order.step=='2'}"><label style="color:green">支付完成</label></c:if>
							<c:if test="${order.step=='3'&&order.isuse=='0'}"><label style="color:green">未结算-未使用</label></c:if>
							<c:if test="${order.step=='3'&&order.isuse=='1'}"><label style="color:green">未结算-已使用</label></c:if>
							<c:if test="${order.step=='4'&&order.isuse=='0'}"><label style="color:green">已结算-未使用</label></c:if>
							<c:if test="${order.step=='4'&&ordre.isuse=='1'}"><label style="color:green">已结算-已使用</label></c:if>
							<c:if test="${order.step=='5'}"><label style="color:green">支付完成</label></c:if>
							<c:if test="${order.step=='6'}"><label style="color:green">支付完成</label></c:if>
						</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">下单时间</label>
						<label class="control-label"><fmt:formatDate value="${order.odate}"
									pattern="yyyy-MM-dd  HH:mm:ss" /></label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
		</div>
	</div>
	
</body>
</html>