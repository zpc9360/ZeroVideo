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
<link rel="stylesheet" href="resources/css/barrage.css">
<link rel="stylesheet" href="resources/css/my.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script src="ueditor/ueditor.config.js"></script>
<script src="ueditor/ueditor.all.js"></script>

<title>正在播放</title>
</head>
<body>
	<zero:nav />
	<div class="bg-light mt-2" style="position: relative; z-index: 1">
									<!-- 
					
					弹幕
					
					 -->
					 <hr>
<a href="javascript:;" class="showBarrage btn btn-success" style="margin-left:400px">打开弹幕</a>
	<div class="barrage">
	<div class="screen">
	<a href="javascript:;" class="s_close">X</a>
		<div class="mask">
		<!--内容在这里显示-->
		nonon
		</div>
	</div>

	<!--Send Begin-->

	<div class="send">
					  <input type="text" class="s_text"/>
					  <input type="button" class="s_btn" value="说两句"/>
	</div>

	<!--Send End-->
	<span class="s_close">X</span>

	</div>
	
	<script src="resources/js/jquery.min.js"></script>

	<script>

		$(function(){

				$(".showBarrage,.s_close").click(function(){

				$(".barrage,.s_close").toggle("slow");

			});

		init_animated();

		})

//提交评论

		$(".send .s_btn").click(function(){

	var text = $(".s_text").val();

		if(text == ""){

			return;

		};

var _lable = $("<div style='right:20px;top:0px;opacity:1;color:"+getRandomColor()+";'>"+text+"</div>");

			$(".mask").append(_lable.show());

			init_barrage();

			})

//初始化弹幕技术

			function init_barrage(){

				var _top = 0;

				$(".mask div").show().each(function(){

				var _left = $(window).width()-$(this).width();//浏览器最大宽度，作为定位left的值

				var _height = $(window).height();//浏览器最大高度

				_top +=75;

				if(_top >= (_height -130)){

				_top = 0;

				}

				$(this).css({left:_left,top:_top,color:getRandomColor()});

				//定时弹出文字

				var time = 10000;

				if($(this).index() % 2 == 0){

				time = 15000;

				}

$(this).animate({left:"-"+_left+"px"},time,function(){

					$(this).remove();

		});

		}

		);

		}

//获取随机颜色

function getRandomColor(){

		return '#' + (function(h){

		return new Array(7 - h.length).join("0") + h

			}

		)((Math.random() * 0x1000000 << 0).toString(16))

	}

</script>
						<!-- 
						
						弹幕
						
						 -->
		<div class="container">
			<div class="row ">
				<div class="col-9 mt-3">
					<span class="text-light ml-3 mt-3"
						style="position: absolute; z-index: 1">当前正在播放：${play.videoName }
						${play.videoNo }</span>
					<video class="px-3"
						style="padding-top: -5px; background-color: #000;"
						autoplay="autoplay" controls="controls" preload="auto"
						height="560" width="840">
						<source src="${basePath }${play.videoUrl }${videoToken }"
							type="video/mp4">
					</video>
				</div>
				<div class="col-3 my-3">
					<div class="rounded ml-3 mb-3" style="height: 100%;">
					</div>
				</div>
			</div>


			<hr>
			<div>
				评论
				<c:if test="${empty currenUser }">（登陆后才可以发表评论）</c:if>
			</div>
			<c:if test="${not empty currenUser }">
				<div class="row my-3" style="height: 80px;">
					<c:if test="${not empty currenUser }">
						<div class="col-1 d-flex align-items-center ml-2">
							<img width="65px" height="65px" class="rounded-circle"
								src="${currenUser.headImg }">
						</div>
					</c:if>
					<div class="col-9">
						<textarea id="comment" name="comment" class="rounded"
							style="width: 100%; height: 100%; resize: none;"
							placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"></textarea>
					</div>
					<div class="col-1 d-flex">
						<button class="btn btn-primary btn-block text-center" onclick='addComment()'>发表</button>
					</div>
				</div>
			</c:if>
			<!-- 评论列表  -->
			<input type="hidden" style="display: none;" id="videoId" value="${play.id }">
			<input type="hidden" style="display: none;" id="userId" value="${currenUser.id }">
			<input type="hidden" style="display: none;" id="" value="">
			<hr>
			<div id="pageBar"></div>
			<div id="commentList"></div>
<!-- 			<div><div class="row"><div class="col-1"><img width="65px" height="65px" class="rounded-circle" src="----------"></div><div class="col"><span class="badge badge-info">--------</span><span class="badge badge-secondary">---------</span><br><span class="mt-1">--------</span></div></div></div>
 -->
		</div>
		<div></div>
	</div>
</body>
<script>
	function addComment(){
		len = $('#comment').val().length;
		if(len < 10){
			alert("评论长度不小于10字！");
			return;
		}
		fetch(
				"comment/addComment",
				{
					method : 'POST',
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
					},
					body : 'videoId='+$('#videoId').val()+'&userId='+$('#userId').val()+'&content='+$('#comment').val()
		})
		.then(res => res.json())
		.then(data => {
			$('#commentList').empty();
			for(var i in data){
				$('#commentList').append("<div class='row my-2'><div class='col-1'><img width='65px' height='65px' class='rounded-circle' src='"+data[i].headImg+"'></div><div class='col'><span class='badge badge-info'>"+data[i].nickName+"</span><span class='badge badge-secondary ml-2'>"+data[i].createDate+"</span><br><span class='mt-2'>"+data[i].content+"</span></div></div><hr>");
			}
			$('#comment').val("");
		});
	}
	//var ue = UE.getEditor('container');
	getComments(0,$('#videoId').val());
	function getComments(curPage, videoId){
		fetch(
				"comment/getComments",
				{
					method : 'POST',
					headers : {
						'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
					},
					body : 'curPage='+curPage+'&videoId='+videoId
		})
		.then(res => res.json())
		.then(data => {
			$('#commentList').empty();
			$('#pageBar').empty();
			$('#pageBar').append(data.pageBar)
			for(var i in data){
				$('#commentList').append("<div><div class='row'><div class='col-1'><img width='65px' height='65px' class='rounded-circle' src='"+data[i].headImg+"'></div><div class='col'><span class='badge badge-info'>"+data[i].nickName+"</span><span class='badge badge-secondary ml-2'>"+data[i].createDate+"</span><br><span class='mt-1'>"+data[i].content+"</span></div></div></div><hr>");
			}
		});
	}
</script>
</html>