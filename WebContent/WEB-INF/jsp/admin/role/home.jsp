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
<title>角色</title>
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
			<th>角色释义</th>
			<th>角色等级</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${roleList }" var="role">
			<tr>
				<td>${role.roleName }</td>
				<td>${role.roleLevel }</td>
				<td>
					<button onclick="getOne(${role.id})"
						class="btn btn-sm btn-outline-light text-warning">
						<i class="icon-pencil icon-large"></i>修改
					</button> <a href="role/roleDelete?id=${role.id }"
					class="btn btn-sm btn-outline-light text-danger"><i
						class="icon-trash icon-large"></i> 删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
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
				modelAttribute="role">
				<div id="modal-body" class="modal-body">
					<!-- 在此处放入表单 -->
					<form:hidden path="id" />
					<div class="form-group">
						<label for="recipient-name" class="col-form-label">角色名称:</label>
						<form:input placeholder="请输入角色名称" path="roleName"
							cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="recipient-name" class="col-form-label">角色等级:</label>
						<form:input placeholder="请输入角色等级" path="roleLevel"
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
		$('#myForm').attr('action','role/roleAdd');
		$('#id').remove();
		$('input').attr("value",'');
		$("option").removeAttr("selected");
		$('#modalTitle').html('增加');
		$('#myModal').modal();
	}
	function getOne(id) {
		fetch(
				"role/getOne",
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
			$('#myForm').attr('action','role/roleUpdate');
			$('#modalTitle').html('修改');
			$('#myModal').modal();
		});
	};
</script>
</html>