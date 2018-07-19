<!DOCTYPE html>
<html lang="zh-CN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<head>
<base href="${basePath }">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>注册</title>
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
	<div class="container" style="width: 450px">
		<!-- Start Sign In Form -->
		<form:form data-animate-effect="fadeIn"
			cssClass="zero-form animate-box" action="user/regist"
			modelAttribute="user">
			<h2>注册</h2>
<!-- 			<div class="form-group">
				<div class="alert alert-success" role="alert">您的验证信息已发送至您的邮箱，请注意查收</div>
			</div>
 -->			<div class="form-group">
				<label for="email" class="sr-only">用户名</label>
				<form:input autocomplete="off" placeholder="请输入用户名" path="userName"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<label for="name" class="sr-only">昵称</label>
				<form:input autocomplete="off" placeholder="昵称" path="nickName"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<label for="password" class="sr-only">密码</label>
				<form:password autocomplete="off" placeholder="密码" path="passWord"
					cssClass="form-control" />
			</div>
			<div class="form-group">
				<label for="remember" class="sr-only"><input type="checkbox" id="remember">
					一周之内记住我</label>
			</div>
			<div class="form-group pull-right">
				<p>
					已有账户？ <a href="user/toLogin">登录</a>
				</p>
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="submit">注册</button>
			</div>
		</form:form>
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

