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
						
						<!-- 리스트에 있는 값(${iList})을 하나씩 꺼내어 'vo'라는 변수에 담아줌 => ${vo.no}, ${vo.content}와 같은 형식으로 List에 들어있는 값을 출력 -->
						<c:forEach items="${iList}" var="vo">
							<li>										
								
								<div class="view" id="v${vo.no}" data-imageNo="${vo.no}"> <!-- 1. html에서 id 값은 중복될 수 없음 -->
								<!-- 2. id="v${vo.no}" ==> 이와 같이 "아이디이름 + 게시글 번호"를 id값으로 매겨주면 각 게시글의 id 값은 모두 다른 값을 가지게 됨 (for문이 돌 때마다 no값이 변하기 때문에) -->
						
								<!-- id="v${vo.no}" / data-imageNo="${vo.no}" => 해당 게시글 삭제를 위함 -->
								
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
				
				<!-- [ enctype="multipart/form-data" ] -> file을 controller에서 받아주기 위해 필수적으로 입력해야 하는 인코딩 태그 / 여기서 오류 많이 나니 해당 태그를 입력했는 지 꼭 확인할 것 -->
				<form method="post" action="${pageContext.request.contextPath}/gallery/imageUpload" enctype="multipart/form-data">
					<input type="hidden" name="uNo" value="${authUser.no}"> <!-- db에 user_no를 입력해야 하기 때문에 ${authUser.no} 값이 필요함 -->
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" > <!-- content라는 이름으로 값을 보내줌/ ex - ../imageUpload?content='안녕하세요' -->
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
					
					<input type="hidden" name="iNo" value="" id="iNo"> <!-- 게시글 삭제를 위해 게시글의 no값을 보냄 -->
					
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

// 업로드 창 보기
// $("선택자").on("click", function() {}); => 버튼 클릭 시 함수를 실행시키는 틀 (기본 규칙임, 외워둘 것) / 선택자에서 id 값을 사용한 경우 ("#id이름"), class를 사용한 경우 (".클래스이름"), 태그를 사용한 경우 ("태그 이름")
$("#btnImgUpload").on("click", function() {
	// console.log("이미지 업로드 버튼 클릭");
	$("#addModal").modal(); // $("선택자").modal() => 모달창 열어주는 메소드
});

//이미지 보기
$(".view").on("click", function() {
	// console.log("이미지 클릭");

	var no = $(this).data("imageno"); // 해당 게시물의 no값 받아오기 ($(this) 함수 ppt p.17 참고 / jqueryex > 예제 ex14.html 참고)
	$("#iNo").val(no); // 위에서 받아온 게시물의 no 값을 아이디가 iNo인 input box의 value로 삽입해 줌 (.val() 함수 ppt p.15 참고 / jqueryex > 예제 ex12.html 참고)
	
	$.ajax({
		// 데이터를 모아서 controller로 보내주는 부분
		url : "${pageContext.request.contextPath}/gallery/getPostInfo", // form action이라고 생각하면 편함 => <form method="" action="이 부분!">		
		type : "post",  // 마찬가지로 form action이라고 생각하면 편함 => <form method="이 부분!" action="">	
		data : {no: no}, // 파라미터로 넘어갈 곳 {변수명: 값} / ex - ../imageUpload?no=1
		
		// 결과값을 받아오는 부분
		dataType : "json", // 결과값을 json형태로 받아옴
		success : function(vo){ // function(변수명) -> 변수는 controller에서 리턴한 값을 담아줌 (개발자가 알아서 임의적으로 지정해주면 됨)
			/*성공시 처리해야될 코드 작성*/
			
			// image 출력
			var url = "${pageContext.request.contextPath}/upload/" + vo.saveName;
			$("#viewModelImg").attr("src", url); // $("선택자").attr("속성", 값) : 선택자에 있는 속성 안에 해당 값을 넣어줌
												 // $("선택자").attr("속성") : 선택자에 있는 속성(name, src 등)이 가진 값을 가져옴
			
			// content 출력
			$("#viewModelContent").text(vo.content); // (.text() 함수 - ppt p.09 참고 / jqueryex > 예제 ex06.html 참고)
			
			if("${authUser.no}" != vo.user_no) { // 해당 게시물을 올린 글쓴이가 아닌 경우 삭제 버튼 안 보임 / jqeury에서 el문법을 사용하려면 큰따옴표("")로 감싸줘야 함 (ex - "${authUser.no}")
				$("#btnDel").hide(); // $("선택자").hide(); => 모달 숨김처리
									 // $("선택자").show(); => 보임
			}
			
			$("#viewModal").modal(); // $("선택자").modal() = 모달창 열어주는 메소드
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

// 이미지 삭제
$("#btnDel").on("click", function() {
	// console.log("삭제 버튼 클릭");
	
	var no = $("#iNo").val(); // 게시글 삭제를 위해 게시글의 no값이 필요
	
	$.ajax({
		url : "${pageContext.request.contextPath}/gallery/delete",		
		type : "post",
		data : {no: no},

		dataType : "json",
		success : function(cnt){ /*성공시 처리해야될 코드 작성*/
			if(cnt == 1) { // 삭제 성공 시 
				$("#v" + no).remove();  // 해당 게시글 삭제 (line 61번 내용 참고)
				$("#viewModal").modal("hide"); // 모달 창 닫기
			}
			else { // 실패 시
				$("#viewModal").modal("hide"); // 모달 창 닫기
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});

</script>

</html>

