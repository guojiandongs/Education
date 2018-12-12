<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<div>
	<input type="hidden" id="${name }" value="${value }" name="${name }" />
	<input class="help-block" onchange="upload${name }()" type="file"
		id="${name }file" name="${name }file" />
	<ul id="${name }list">
		<c:if test="${value!=null && !value.equals('')}">
			<c:forEach var="s" items="${value.split('\\\|')}">
				<li><span class='glyphicon glyphicon-paperclip'></span>${s.substring(0,s.indexOf('/'))}
				<a onclick="delete${name }('${s.substring(0,s.indexOf('/'))}','${s.substring(s.indexOf('/')+1)}')">&nbsp;&nbsp;删除</a></li>
			</c:forEach>
		</c:if>
	</ul>
</div>



<script type="text/javascript">
var loadingfile = "";
	//上传附件
	function upload${name }() {
		loadingfile = "";
		var i = 0;
		i++;
		var filename = $("#${name }file").val();
		var pos = filename.lastIndexOf('\\');
		if (pos == -1) {
			pos = filename.lastIndexOf('/');
		}
		if (pos >= 0) {
			filename = filename.substr(pos + 1);
		}
		var lis = $("#${name }list").children();
		for (var i = 0; i < lis.length; i++) {
			var li = lis[i];
			if (li.style.display != "none"
					&& li.innerHTML.indexOf(filename) >= 0) {
				alert("已上传文件：" + filename + "！");
				return;
			}
		}

		$("#progressmodal").modal();
		inter = window.setInterval("callback('" + i + "');", 100);//每隔100毫秒执行callback 
		$("#p_outid").width("0%");
		$("#p_outid").html("0%");
		loadingfile = filename;
		
		var posturl = "${ctx}/file/upload";
		$
				.ajaxFileUpload({
					url : posturl,
					secureuri : false,
					fileElementId : [ '${name }file' ],
					dataType : 'text',
					contentType : 'application/json;charset=UTF-8',
					beforeSend : function() {
						//$("#cardupload1").disabled=true;
					},
					complete : function() {
						//$("#cardupload1").disabled=true;
					},
					//上传成功后修改附件上传状态和文件大小信息
					success : function(data, status) {
						var result = data;
						var pos = result.lastIndexOf('{');
						var pos2 = result.lastIndexOf('}');
						if (pos >= 0) {
							result = result.substr(pos, pos2 - pos + 1);
						}
						var returndata = $.parseJSON(result);
						var fileshow = "  " + filename + "  ";
						var anames = "${name }remove" + i;
						var size = "${name }size" + i;
						//添加附件到list
						var ss = $("<li><span class='glyphicon glyphicon-paperclip'></span>"
								+ fileshow
								+ "<span id='"+size+"'></span><a id='"+anames+"' attid='1'>&nbsp;&nbsp;删除</a></li>");
						$("#${name }list").append(ss); //将$htmlLi追加到$ul元素的li列表

						$("#progressmodal").modal('hide');
						$("#${name }remove" + i).bind("click", function() {
							remove${name }(filename, returndata.filecode);
						}); // 你的操作。
						$("#${name }").val(
								$("#${name }").val() + filename + "/"
										+ returndata.filecode + "|");
					},
					error : function(data, status, e) {
						remove${name }(filename, "");
						$("#progressmodal").modal('hide');
					}
				});

	}

	function cancelupload() {
		if (window.stop)
			window.stop();
		else
			document.execCommand("Stop");
		remove${name }(loadingfile, "");
	}
	
	var inter = null;
	function callback(index) {
		$.ajax({
			url : "${ctx}/file/uploadstatus",
			type : "POST",
			async : false,
			data : {},
			error : function(errorMsg) {
			},
			success : function(data) {

				var returndata = $.parseJSON(data);
				var valuenow = returndata.status.substr(0,
						returndata.status.length - 1);
				$("#p_outid").width(returndata.status);
				$("#p_outid").html(returndata.status);
				if (data.indexOf("100%") != -1) {
					window.clearInterval(inter);
					$("#p_outid").width("100%");
					$("#p_outid").html("100%");
				}
			}
		});
	}
	
	//删除附件
	function remove${name }(filename, filecode) {
		//删除
		$.post("${ctx}/file/deleteResume", {
			filecode : encodeURI(filecode)
		}, function(data, status) {
			//界面删除
			$("#${name }").val(
					$("#${name }").val().replace(filename + "/" + filecode + "|",
							""));
			var lis = $("#${name }list").children();
			for (var i = 0; i < lis.length; i++) {
				var li = lis[i];
				if (li.innerHTML.indexOf(filename) >= 0) {
					li.remove();
				}
			}
		});
	}

	function delete${name }(filename,filecode) {
		//界面删除
		$("#${name }").val(
				$("#${name }").val().replace(filename + "/" + filecode + "|",
						""));
		var lis = $("#${name }list").children();
		for (var i = 0; i < lis.length; i++) {
			var li = lis[i];
			if (li.innerHTML.indexOf(filename) >= 0) {
				li.remove();
			}
		}
	}
</script>