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
<title>主页</title>
</head>
<body>
	<zero:nav />
	<div class="container" style="margin-top: 130px;">
		<zero:menuBar url="menuUrl" detail="menuDetail" list="${menuList }" />
		<hr>
		<div>
			<div class="my-1 text-info">
				<h3>热门推荐</h3>
			</div>
			<div class="mt-2 row">
				<div class="col text-left">
					<c:forEach items="${hotVideoList }" var="video">
						<a class="btn btn-light" href="video/getVideo?id=${video.id }">
							<img class="border border-dark rounded" alt=""
							src="${video.videoPhotoUrl }" width="168px" height="250px"><br>
							<span>${video.videoName }</span>
						</a>
					</c:forEach>
				</div>
			</div>
		</div>
		<hr>
		<c:forEach items="${videoMap }" var="videoList">
			<c:if test="${not empty videoList.value }">
				<div>
					<div class="my-1 text-info">
						<h3>${videoList.key.menuDetail }推荐</h3>
					</div>
					<div class="mt-2 row">
						<div class="col text-left">
							<c:forEach items="${videoList.value }" var="video">
								<a class="btn btn-light" href="video/getVideo?id=${video.id }">
									<img class="border border-dark rounded" alt=""
									src="${video.videoPhotoUrl }" width="135px" height="200px"><br>
									<span>${video.videoName }</span>
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
				<hr>
			</c:if>
		</c:forEach>
	</div>
</html>