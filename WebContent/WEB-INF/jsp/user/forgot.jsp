<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${basePath }">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>找回密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1">


<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/animate.css">
<link rel="stylesheet" href="resources/css/signstytle.css">
<link rel="stylesheet" href="resources/css/background.css">
<link rel="stylesheet" href="resources/css/my.css">

<!-- Modernizr JS -->
<script src="resources/js/modernizr-2.6.2.min.js"></script>

</head>
<body>
<zero:nav/>
	<div class="container" style="width: 450px;">
				<!-- Start Sign In Form -->
				<form action="#" class="zero-form animate-box"
					data-animate-effect="fadeIn">
					<h2>找回密码</h2>
<!-- 					<div class="form-group">
						<div class="alert alert-success" role="alert">邮件已发送至您的邮箱，请注意查收</div>
					</div>
 -->					<div class="form-group">
						<label for="email" class="sr-only">请输入您的邮箱</label> <input
							type="email" class="form-control" id="email"
							placeholder="请输入您的邮箱" autocomplete="off">
					</div>
					<div class="form-group">
						<p>
							<a href="user/toLogin">登录</a> | <a href="user/toUserRegist">注册</a>
						</p>
					</div>
					<div class="form-group">
						<button class="btn btn-primary btn-block" type="submit">找回密码</button>
					</div>
				</form>
				<!-- END Sign In Form -->
	</div>

	<!-- jQuery -->
	<script src="resources/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- Placeholder -->
	<script src="resources/js/jquery.placeholder.min.js"></script>
	<!-- Waypoints -->
	<script src="resources/js/jquery.waypoints.min.js"></script>
	<!-- Main JS -->
	<script src="resources/js/main.js"></script>

</body>
</html>

