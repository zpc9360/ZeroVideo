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
<title>权限</title>
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
	<table class="table table-hover table-sm">
		<tr>
			<th>权限字典</th>
			<th>权限释义</th>
			<th>所属角色</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${authorityList }" var="authority">
			<tr>
				<td>${authority.authorityName }</td>
				<td>${authority.authorityDetail }</td>
				<td>${authority.roleLevel }</td>
				<td>
					<button type="button" onclick="getOne(${authority.id })"
						class="btn btn-primary btn btn-sm btn-outline-light text-warning">
						<i class="icon-pencil icon-large"></i> 修改
					</button> <a href="authority/authorityDelete?id=${authority.id }"
					class="btn btn-sm btn-outline-light text-danger"> <i
						class="icon-trash icon-large"></i> 删除
				</a>
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
					modelAttribute="authority">
					<div id="formBody" class="modal-body">
						<!-- 在此处放入表单 -->
						<form:hidden path="id" />
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">权限字典:</label>
							<form:input placeholder="请输入权限字典" path="authorityName"
								cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">权限释义:</label>
							<form:input placeholder="请输入权限解释" path="authorityDetail"
								cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">所属角色:</label>
							<form:select path="roleLevel" items="${roleMap }"
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
	function add(){
		$('#myForm').attr('action','authority/authorityAdd');
		$('#id').remove();
		$('input').attr("value",'');
		$("option").removeAttr("selected");
		$('#modalTitle').html('增加');
		$('#myModal').modal();
	}
	function getOne(id) {
		fetch(
				"authority/getOne",
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
			$('select').removeAttr("value");
			$("option").removeAttr("selected");
			$("#roleLevel").find("option[value="+data.roleLevel+"]").prop("selected",true);
			$('#myForm').attr('action','authority/authorityUpdate');
			$('#modalTitle').html('修改');
			$('#myModal').modal();
		});
	};
</script>
</html>