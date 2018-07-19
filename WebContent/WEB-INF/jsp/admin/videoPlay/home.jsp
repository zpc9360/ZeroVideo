<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<title>视频</title>
</head>
<body>
	<!-- 购物车加减输入框		<input onkeyup="value=value.replace(/[^1234567890-]+/g,'')"> -->
	<div class="row m-3">
		<div class="row col-8">
			<button class="btn btn-outline-success" onclick="add()">
				<i class="icon-plus icon-large"></i> 添加
			</button>
			<div class="col ml-2">${video.videoName }</div>
		</div>
		<div class="col-4">${pageBar }</div>
	</div>
	<table class="table table-hover table-sm">
		<tr>
			<th>视频资源名称</th>
			<th>视频资源地址</th>
			<th>是否收费（0否/1是）</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${playList }" var="play">
			<tr>
				<td>${play.videoNo }</td>
				<td>${play.videoUrl }</td>
				<td>${play.isCharge }</td>
				<td>
					<button onclick="getOne(${play.id})"
						class="btn btn-sm btn-outline-light text-warning">
						<i class="icon-pencil icon-large"></i> 修改
					</button> <a href="videoPlay/videoPlayDelete?id=${play.id }"
					class="btn btn-sm btn-outline-light text-danger"><i
						class="icon-trash icon-large"></i> 删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 id="modalTitle" class="modal-title">修改</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form:form enctype="multipart/form-data" id="myForm"
					cssClass="zero-form animate-box" modelAttribute="play">
					<div id="modal-body" class="modal-body">
						<!-- 在此处放入表单 -->
						<input type="hidden" name="videoId" value="${video.id }" />
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">视频名称</label> <input
								class="form-control" readonly="readonly" type="text"
								name="videoName" value="${video.videoName }">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">视频资源名称</label>
							<form:input placeholder="请输入视频资源名称" path="videoNo"
								cssClass="form-control" />
						</div>
						<div class="form-group" id="isUploadFileArea">
							<label for="recipient-name" class="col-form-label">是否上传资源文件
							</label> <label class="form-check-label ml-4" style="cursor: pointer;">
								<input style="cursor: pointer;" onclick="radioChange(1)"
								checked="checked" class="form-check-input" type="radio"
								name="isUploadFile" value="1"> 是
							</label> <label class="form-check-label ml-4" style="cursor: pointer;">
								<input style="cursor: pointer;" onclick="radioChange(0)"
								class="form-check-input" type="radio" name="isUploadFile"
								value="0"> 否
							</label>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">是否收费:</label>
							<form:input placeholder="是否收费" path="isCharge"
								cssClass="form-control" />
						</div>

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var videoUrl = '';
	function radioChange(num){
		if(num==1){
			$('#fileUpload').remove();
			$("#isUploadFileArea").after("<div id=\"fileUpload\" class=\"form-group\"> <label for=\"recipient-name\" class=\"col-form-label\">上传文件:</label> <input required=\"required\" type=\"file\" class=\"form-control\" name=\"myFile\"></div>");
			$('#fileUpload').show();
		}
		if(num==0){
			$('#fileUpload').remove();
		}
	}
	function add(){
		$('#myForm').attr('action','videoPlay/videoPlayAdd');
		$('#id').remove();
		$('#videoUrl').remove();
		$('#modalTitle').html('增加');
		//$('input').attr("value",'');
		$("option").removeAttr("selected");
		$("input:radio[name=isUploadFile][value=1]").prop("checked",true);
		$("input:radio[name=isUploadFile][value=0]").prop("checked",false);
		$("#isUploadFileArea").after("<div id=\"fileUpload\" class=\"form-group\"> <label for=\"recipient-name\" class=\"col-form-label\">上传文件:</label> <input required=\"required\" type=\"file\" class=\"form-control\" name=\"myFile\"></div>");
		$('#fileUpload').show();
		$('#myModal').modal();
	}
	function getOne(id) {
		fetch(
				"videoPlay/getOne",
				{
					method : 'POST',
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
					},
					body : 'id=' + id
		})
		.then(res => res.json())
		.then(data => {
			$('#myForm').attr('action','videoPlay/videoPlayUpdate');
			for(var p in data){
				$('#'+p).attr('value', data[p]);
			}
			videoUrl = data.videoUrl;
			$('#id').remove();
			$('#videoUrl').remove();
			$("#myForm").append("<input name='id' id='id' type='hidden' value="+data.id+">");
			$("option").removeAttr("selected");
			$("#myForm").append("<input name='videoUrl' id='videoUrl' type='hidden' value="+data.videoUrl+">");
			$("input:radio[name=isUploadFile][value=0]").prop("checked",true);
			$("input:radio[name=isUploadFile][value=1]").prop("checked",false);
			$('#fileUpload').hide();
			//$("#videoMenu").find("option[value='"+data.videoMenu+"']").prop("selected",true);
			//console.log(data.videoMenu)
			$('#myForm').attr('action','videoPlay/videoPlayUpdate');
			$('#modalTitle').html('修改');
			$('#myModal').modal();
		});
	};
</script>
</html>