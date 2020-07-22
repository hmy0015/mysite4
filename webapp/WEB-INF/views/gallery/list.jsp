<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/asideGallery.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
				<c:if test="${authUser != null}">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
				</c:if>
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						
						<c:forEach items="${iList}" var="vo">
							<li>
								<div class="view" data-imageNo="${vo.no}">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${vo.saveName}">
									<div class="imgWriter">작성자 : <strong>${vo.name}</strong></div>
								</div>
							</li>
						</c:forEach>
						
						<!-- 이미지반복영역 -->
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<!-- 파일 첨부를 위한 form 사용 시 [ enctype="multipart/form-data" ]를 필수적으로 입력해야 함 -->
				<form method="post" action="${pageContext.request.contextPath}/gallery/imageUpload" enctype="multipart/form-data">
					<input type="hidden" name="uNo" value="${authUser.no}">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="image">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
					<input type="hidden" name="iNo" value="" id="iNo">
					
				</div>
				<form method="" action="">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
// $("#id").hide(); // 숨김 처리
// $("#id").show(); // 보임

// 업로드 창 보기
$("#btnImgUpload").on("click", function() {
	console.log("이미지 업로드 버튼 클릭");
	$("#addModal").modal(); // 모달창 열기
});

// 이미지 보기
$("#viewArea").on("click", "div", function() {
	console.log("이미지 클릭");
	
	var no = $(this).data("imageno"); // 해당 게시물의 no값 받아오기
	$("#iNo").val(no);
	
	$("#viewModal").modal(); // 모달창 열기
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/getSaveName",		
		type : "post",
		data : {no: no},

		dataType : "json",
		success : function(saveName){ /*성공시 처리해야될 코드 작성*/
			var url = "${pageContext.request.contextPath}/upload/" + saveName;
			$("#viewModelImg").attr("src", url);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

</script>




</html>

