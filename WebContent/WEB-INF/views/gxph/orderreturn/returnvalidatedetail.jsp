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
			<li><a href="${ctx}/usinesses">退款订单</a></li>
			<li class="active">退单详情</li>
		</ol>
		<form id="inputForm" class="form-horizontal" role="form"
				action="${ctx}/orderreturn/validatesubmits/${orderrrturn.id}" method="post">
		<div class="panel-body"><input type="hidden" id="uploadtoken" value="${uploadtoken}" />
				<legend>退单信息</legend>
				<input type="hidden" name="id" value="${orderrrturn.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">订单号</label>
						<label class="control-label">${orderrrturn.notest}</label>
					<div class="col-sm-1">
					</div>
				</div>
			<%-- 	<div class="form-group">
					<label class="col-sm-2 control-label">微信支付订单号</label>
						<label class="control-label">${orderrrturn.order.wxorderno}</label>
					<div class="col-sm-1">
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-2 control-label">退单号</label>
						<label class="control-label">${orderrrturn.order.wxorderno}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品名称</label>
						<label class="control-label">${orderrrturn.order.product.name}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">实际付款</label>
						<label class="control-label">${orderrrturn.order.realpay}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款金额</label>
						<label class="control-label">${orderrrturn.returnmoney}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买数量</label>
						<label class="control-label">${orderrrturn.order.num}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">购买人姓名</label>
						<label class="control-label">${orderrrturn.order.buyusername}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
						<label class="control-label">${orderrrturn.order.buyuserphone}</label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款日期</label>
						<label class="control-label"><fmt:formatDate value="${orderrrturn.returntime}"
									pattern="yyyy-MM-dd  HH:mm:ss" /></label>
					<div class="col-sm-1">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">审核状态</label>
					<div class="col-sm-8">
						<select class="form-control required" name="mark" onchange="reason(this);">
							<option
								<c:if test="${usinesses.status == '0'}"> selected="selected"</c:if>
								value="0">通过</option>
							<option
								<c:if test="${usinesses.status == '1'}"> selected="selected"</c:if>
								value="1">不通过</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-6">
						<button type="submit" class="btn btn-primary" >审核</button>
						<input class="btn btn-default" type="button" value="返回"
							onclick="history.back()" />
					</div>
				</div>
		</div>
		</form>
	</div>
	
</body>
</html>