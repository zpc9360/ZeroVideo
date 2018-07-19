<!DOCTYPE html>
<html lang="zh-CN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<head>
<base href="${basePath }">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/animate.css">
<link rel="stylesheet" href="resources/css/signstytle.css">
<link rel="stylesheet" href="resources/css/my.css">

<!-- Modernizr JS -->
<script src="resources/js/modernizr-2.6.2.min.js"></script>

</head>
<body>
<zero:nav/>
	<div class="container" style="width: 450px;">
		<!-- Start Sign In Form -->
		<form action="user/login" class="zero-form animate-box"
			data-animate-effect="fadeIn" method="POST">
			<h2>登录</h2>
			<div class="form-group">
				<label for="userName" class="sr-only">Email</label> <input
					type="text" class="form-control" name="userName" placeholder="用户名"
					autocomplete="off">
			</div>
			<div class="form-group">
				<label for="passWord" class="sr-only">Password</label> <input
					type="password" class="form-control" name="passWord" placeholder="密码"
					autocomplete="off">
			</div>
			<div class="form-group">
				<label class="form-check-label ml-4"> <input
					class="form-check-input" type="checkbox" name="remeber">
					一周之内记住我
				</label>
			</div>
			<div class="form-group">
				<p>
					未注册？ <a href="user/toUserRegist">注册</a> | <a href="user/toForgot">忘记密码？</a>
				</p>
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="submit">登录</button>
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

