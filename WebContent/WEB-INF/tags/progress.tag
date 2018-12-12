<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 上传进度 -->
<div class="modal fade" id="progressmodal" tabindex="-1" role="dialog"
	data-backdrop="static" data-keyboard="false"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title" id="myModalLabel2">文件上传进度</h4>
			</div>
			<div class="modal-body" id="progressshow">
				<div id="progressid" class="progress">
					<div id="p_outid" class="progress-bar" role="progressbar"
						min-width="20px" ; aria-valuenow="100" aria-valuemin="0"
						aria-valuemax="100" style="width: 0%;"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					onclick="cancelupload()">取消</button>
			</div>
		</div>
	</div>
</div>