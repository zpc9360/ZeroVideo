<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/cropper.min.css" rel="stylesheet" />
<link href="resources/css/photoUpload.css" rel="stylesheet" />

<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/cropper.min.js"></script>


<title>更改头像</title>
</head>
<body>

	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#exampleModal">Launch demo modal</button>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="avatar-upload">
							<button class="btn btn-danger"
								onClick="$('input[id=avatarInput]').click();">请选择图片</button>
							<span id="avatar-name"></span> <input hidden="true"
								id="avatarInput" name="avatar_file" type="file">
						</div>

						<div class="container">
							<div class="row">
								<div class="col-md-9">
									<div class="img-container avatar-wrapper">
										<img id="image" src="resources/imgs/zero/videoNotFound.jpg">
									</div>
								</div>
								<div class="col-3 img-container">
									<div class="img-preview preview-lg rounded" style="height:150px;width:150px"></div>
									<div class="img-preview preview-md rounded"></div>
									<div class="img-preview preview-sm rounded"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>



</body>
<script type="text/javascript">
	$('#image').cropper({
		modal : false,
		aspectRatio : 1 / 1,
		viewMode : 2,
		preview : '.img-preview',
		crop : function(e) {
		}
	});
	$('#avatarInput').on('change', function(e) {
		var filemaxsize = 1024 * 5;//5M
		var target = $(e.target);
		var Size = target[0].files[0].size / 1024;
		if (Size > filemaxsize) {
			alert('图片过大，请重新选择!');
			$(".avatar-wrapper").childre().remove;
			return false;
		}
		if (!this.files[0].type.match(/image.*/)) {
			alert('请选择正确的图片!')
		} else {
			var filename = document.querySelector("#avatar-name");
			var texts = document.querySelector("#avatarInput").value;
			var teststr = texts;
			testend = teststr.match(/[^\\]+\.[^\(]+/i); //直接完整文件名的
			filename.innerHTML = testend;
			c();
		}
	});

	function c() {
		var r = new FileReader();
		f = $('#avatarInput').get(0).files[0];
		r.readAsDataURL(f);
		r.onload = function(e) {
			$('#image').cropper('replace', this.result, true);
		};
	}
</script>
</html>