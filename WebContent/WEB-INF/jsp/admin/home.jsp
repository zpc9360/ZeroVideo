<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/my.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<title>管理首页</title>
</head>
<body>
	<div class="container" style="height: 650px; width: 100%;">
		<div class="row">
			<div class="col-2 bg-dark pt-2" style="height: 650px;">
				<c:forEach items="${menuMap }" var="map">
					<div class="pos-f-t">
						<nav class="navbar navbar-dark bg-dark">
							<button class="navbar-toggler btn btn-link" type="button"
								data-toggle="collapse" data-target="#${map.key.menuName }"
								aria-controls="${map.key.menuName }" aria-expanded="true">${map.key.menuDetail }</button>
						</nav>
						<div class="collapse" id="${map.key.menuName }">
							<c:forEach items="${map.value }" var="menu">
								<div class="bg-dark text-center">
									<a class="btn btn-link text-light" href="${menu.menuUrl }"
										target="myframe">${menu.menuDetail }</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col">
				<iframe style="border: 0px; width: 100%; height: 100%;" id="myframe"
					name="myframe"> </iframe>
			</div>
		</div>
	</div>

</body>

</html>