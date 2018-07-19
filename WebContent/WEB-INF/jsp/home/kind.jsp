<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/my.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<title>分类</title>
</head>
<body>
	<zero:nav />
	<div class="container" style="margin-top: 130px;">
		<zero:menuBar url="menuUrl" detail="menuDetail" list="${menuList }" />
		<div class="mt-3"></div>
		<div class="row">
			<c:forEach items="${videoList }" var="video">
				<div class="col-lg-3 col-md-4 col-sm-6 my-2 text-center">
					<a class="btn btn-light rounded border-secondary p-0 text-center "
						href="video/getVideo?id=${video.id }"> <img
						class="rounded ml-1 mt-1" alt="${video.videoName }"
						src="${video.videoPhotoUrl }" width="202px" height="300px">
						<br><span class="ml-1 text-center" style="width:100%">${video.videoName }</span> 
					</a>
				</div>
			</c:forEach>
		</div>
		${pageBar }

	</div>
</body>
</html>