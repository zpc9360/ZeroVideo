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
	<div class="row m-3">
		<div class="col-8">
			<button class="btn btn-outline-success" onclick="add()">
				<i class="icon-plus icon-large"></i> 添加
			</button>
		</div>
		<div class="col-4">${pageBar }</div>
	</div>
	<table class="table table-hover table-sm" style="font-size: 15px">
		<tr>
			<th>视频名称</th>
			<th>视频资源总数</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${videoList }" var="video">
			<tr>
				<td>${video.videoName }</td>
				<td>${video.videoPlaySum }</td>
				<td>
					<button onclick="getOne(${video.id})"
						class="btn btn-sm btn-outline-light text-warning">
						<i class="icon-pencil icon-large"></i> 修改
					</button> <a href="videoPlay/videoPlayManage?id=${video.id }"
					class="btn btn-sm btn-outline-light text-info"><i
						class="icon-film icon-large"></i> 资源</a> <a
					href="video/videoDelete?id=${video.id }"
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
				<form:form id="myForm" cssClass="zero-form animate-box"
					enctype="multipart/form-data" modelAttribute="video">
					<div id="modal-body" class="modal-body">
						<!-- 在此处放入表单 -->
						<form:hidden path="id" value="0" />
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">所属分类:</label>
							<form:select items="${menuMap }" path="videoMenu"
								cssClass="form-control" />
						</div>
						<div class="form-group" id="isUploadFileArea">
							<label for="recipient-name" class="col-form-label">是否上传封面
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
							<label for="recipient-name" class="col-form-label">视频名称</label>
							<form:input placeholder="请输入菜单名称" path="videoName"
								cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">视频简介:</label>
							<form:input placeholder="视频简介" path="videoAbstract"
								cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">演员:</label>
							<form:input placeholder="演员" path="videoActs"
								cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">导演:</label>
							<form:input placeholder="导演" path="videoMaker"
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
		$('#myForm').attr('action','video/videoAdd');
		$('#id').remove();
		$('#modalTitle').html('增加');
		$('input').removeAttr("value");
		$("option").removeAttr("selected");
		$("input:radio[name=isUploadFile][value=1]").prop("checked",true);
		$("input:radio[name=isUploadFile][value=0]").prop("checked",false);
		$("#isUploadFileArea").after("<div id=\"fileUpload\" class=\"form-group\"> <label for=\"recipient-name\" class=\"col-form-label\">上传文件:</label> <input required=\"required\" type=\"file\" class=\"form-control\" name=\"myFile\"></div>");
		$('#fileUpload').show();
		$('#myModal').modal();
	}
	function getOne(id) {
		fetch(
				"video/getOne",
				{
					method : 'POST',
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
					},
					body : 'id=' + id
		})
		.then(res => res.json())
		.then(data => {
			for(var p in data){
				$('#'+p).attr('value', data[p]);
			}
			$('#id').remove();
			$("#myForm").append("<input name='id' id='id' type='hidden' value="+data.id+">");
			$("#myForm").append("<input name='video' id='id' type='hidden' value="+data.id+">");
			$("#myForm").append("<input name='id' id='id' type='hidden' value="+data.id+">");
			$('select').removeAttr("value");
			$("option").removeAttr("selected");
			$("#videoMenu").find("option[value='"+data.videoMenu+"']").prop("selected",true);
			$("input:radio[name=isUploadFile][value=0]").prop("checked",true);
			$("input:radio[name=isUploadFile][value=1]").prop("checked",false);
			$('#fileUpload').hide();
			$('#myForm').attr('action','video/videoUpdate');
			$('#modalTitle').html('修改');
			$('#myModal').modal();
		});
	};
</script>
</html>