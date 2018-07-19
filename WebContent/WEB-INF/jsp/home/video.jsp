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
<title>视频详情页</title>
</head>
<body>
	<zero:nav />
	<div class="" style="margin-top:130px;position: relative; z-index: 1"></div>
		<div class="container">
			<hr>
			<div class="row">
				<div class="col-3">
					<img src="${video.videoPhotoUrl }" width="202px" height="300px"
						class="rounded border border-dark">
				</div>
				<div class="col-9 mt-2">
					<h4>${video.videoName }</h4><br>
					<div class="row text-center">
						<div class="col-2">播放量<br>${video.videoViewSum }</div>
						<span class="border border-dark" style="height: 52px;"></span>
						<div class="col text-left">导演：${video.videoMaker}<br>主演：${video.videoActs }</div>
					</div>
					<br>
					<h5>简介：</h5>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${video.videoAbstract }</span>
				</div>
			</div>
			<hr>
			<h5 class="ml-2 mt-3 text-dark">播放列表</h5>
			<c:forEach items="${videoPlayList}" var="vp">
				<a class="ml-4 btn btn-outline-secondary my-1"
					href="videoPlay/play?id=${vp.id }">${vp.videoNo }</a>
			</c:forEach>
			<hr>
		</div>
</body>
</html>