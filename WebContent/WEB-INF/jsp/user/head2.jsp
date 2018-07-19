<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="zero" uri="/WEB-INF/tagtld/my.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="resources/headcss/bootstrap.min.css" rel="stylesheet" />
<link href="resources/headcss/cropper.min.css" rel="stylesheet" />
<link href="resources/headcss/sitelogo.css" rel="stylesheet" />
<link href="resources/headcss/font-awesome.min.css" rel="stylesheet" />

<script src="resources/headjs/jquery.min.js"></script>
<script src="resources/headjs/bootstrap.min.js"></script>
<script src="resources/headjs/cropper.js"></script>
<script src="resources/headjs/sitelogo.js"></script>

<style type="text/css">
.avatar-btns button {
	height: 35px;
}
</style>

<title>更改头像</title>
</head>
<body>
	<img src="${currenUser.headImg }">
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#avatar-modal" style="margin: 10px;">修改头像</button>

	<div class="user_pic" style="margin: 10px;">
		<img src="">
	</div>

	<div class="modal fade" id="avatar-modal" aria-hidden="true"
		aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- <form class="avatar-form" action="Upload" enctype="multipart/form-data" method="post"> -->
				<form class="avatar-form">
					<div class="modal-header">
						<button class="close" data-dismiss="modal" type="button">&times;</button>
						<h4 class="modal-title" id="avatar-modal-label">上传图片</h4>
					</div>
					<div class="modal-body">
						<div class="avatar-body">
							<div class="avatar-upload">
								<input class="avatar-src" name="avatar_src" type="hidden">
								<input class="avatar-data" name="avatar_data" type="hidden">
								<label for="avatarInput" style="line-height: 35px;">图片上传</label>
								<button class="btn btn-danger" type="button"
									style="height: 35px;"
									onClick="$('input[id=avatarInput]').click();">请选择图片</button>
								<span id="avatar-name"></span>
								<input class="avatar-input hide" id="avatarInput" name="avatar_file" type="file">
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="avatar-wrapper"></div>
								</div>
								<div class="col-md-3">
									<div class="avatar-preview preview-lg" id="imageHead"></div>
									<div class="avatar-preview preview-md"></div>
									<div class="avatar-preview preview-sm"></div>
								</div>
							</div>
							<div class="row avatar-btns">
								<div class="col-md-4">
									<div class="btn-group">
										<button class="btn btn-danger fa fa-undo" data-method="rotate"
											data-option="-90" type="button" title="向左旋转90°">
											向左旋转</button>
									</div>
									<div class="btn-group">
										<button class="btn  btn-danger fa fa-repeat"
											data-method="rotate" data-option="90" type="button"
											title="向右旋转90°">向右旋转</button>
									</div>
								</div>
								<div class="col-md-5" style="text-align: right;">
									<button class="btn btn-danger fa fa-arrows"
										data-method="setDragMode" data-option="move" type="button"
										title="移动">
										<span class="docs-tooltip" data-toggle="tooltip" title=""
											data-original-title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-search-plus"
										data-method="zoom" data-option="0.1" title="放大图片">
										<span class="docs-tooltip" data-toggle="tooltip" title=""
											data-original-title="$().cropper(&quot;zoom&quot;, 0.1)">
											<!--<span class="fa fa-search-plus"></span>-->
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-search-minus"
										data-method="zoom" data-option="-0.1" title="缩小图片">
										<span class="docs-tooltip" data-toggle="tooltip" title=""
											data-original-title="$().cropper(&quot;zoom&quot;, -0.1)">
											<!--<span class="fa fa-search-minus"></span>-->
										</span>
									</button>
									<button type="button" class="btn btn-danger fa fa-refresh"
										data-method="reset" title="重置图片">
										<span class="docs-tooltip" data-toggle="tooltip" title=""
											data-original-title="$().cropper(&quot;reset&quot;)"
											aria-describedby="tooltip866214"></span>
									</button>
								</div>
								<div class="col-md-3">
									<button class="btn btn-danger btn-block avatar-save fa fa-save"
										type="submit" data-dismiss="modal">保存修改</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
	<script src="resources/headjs/html2canvas.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		//做个下简易的验证  大小 格式 
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
				var teststr = texts; //你这里的路径写错了
				testend = teststr.match(/[^\\]+\.[^\(]+/i); //直接完整文件名的
				filename.innerHTML = testend;
			}
		});

		$(".avatar-save").on("click", function() {
			var img_lg = document.getElementById('imageHead');
			// 截图小的显示框内的内容
			html2canvas(img_lg, {
				allowTaint : true,
				taintTest : false,
				onrendered : function(canvas) {
					canvas.id = "mycanvas";
					//生成base64图片数据
					var dataUrl = canvas.toDataURL("image/png");
					var newImg = document.createElement("img");
					newImg.src = dataUrl;
					imagesAjax(dataUrl)
				}
			});
		})

		function imagesAjax(dataUrl) {
			var pos = dataUrl.indexOf("4") + 2;
			dataUrl = dataUrl.substring(pos, dataUrl.length);//去掉Base64:开头的标识字符
			var data = {};
			data.img = dataUrl;
			$.ajax({
				url : "user/headImgUpload",
				data : data,
				type : "POST",
				dataType : 'json',
				success : function(re) {
					if (re.status == '1') {
						
					}
				}
			});
		}
	</script>
</body>
</html>