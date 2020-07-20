<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- header / nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- asideGuest -->
		<c:import url="/WEB-INF/views/include/asideGuest.jsp"></c:import>

		<div id="content">
			
			<div id="content-head">
            	<h3>ajax방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">ajax일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<table id="guestAdd">
					<colgroup>
						<col style="width: 70px;">
						<col>
						<col style="width: 70px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th><label class="form-text" for="input-uname">이름</label></td>
							<td><input id="input-uname" type="text" name="name"></td>
							<th><label class="form-text" for="input-pass">패스워드</label></td>
							<td><input id="input-pass"type="password" name="pw"></td>
						</tr>
						<tr>
							<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
						</tr>
						<tr class="button-area">
							<td colspan="4"><button id="btnSubmit" type="submit">등록</button></td>
						</tr>
					</tbody>
					
				</table>
				<!-- //guestWrite -->
				
				<div id="guestbookListArea">
				
				</div>
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->
	
	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="delModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록을 삭제하시겠습니까?</h4>
				</div>
				<div class="modal-body"><br>
					<label>비밀번호를 입력하세요.</label> <br>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="hidden" name="modalNo" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>
<script type="text/javascript">
	$(document).ready(function(){		
		// 전체리스트 불러오기
		fetchList();
	});
	
	// 삭제 버튼 클릭
	$("#guestbookListArea").on("click", "a", function() {
		console.log("리스트 지역 클릭");
		event.preventDefault(); // 본래 html 안에 있는 태그의 기능을 사용하지 않음
		
		var $this = $(this)
		var no = $this.data("delno");
		console.log(no);

		$("#modalNo").val(no); // 모달no 창에 해당 게시글 no 삽입
		$("#modalPassword").val(""); // 패스워드 창 비우기
		$("#delModal").modal(); // 모달창 열기
	});
	
	// 전체리스트 불러오기
	function fetchList() {
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/list",		
			type : "post",

			dataType : "json",
			success : function(guestVo){ /*성공시 처리해야될 코드 작성*/
				for(var i = 0; i < guestVo.length; i++) {
					render(guestVo[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	// 방명록 html 그리기
	function render(guestVo) {
		var str = '';
		
		str += '<table id="t' + guestVo.no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestVo.no + '</td>';
		str += '		<td>' + guestVo.name + '</td>';
		str += '		<td>' + guestVo.reg_date + '</td>';
		str += '		<td><a href="" data-delNo="' + guestVo.no + '">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';
		
		$("#guestbookListArea").prepend(str);
	}
	
	// 게시글 작성
	$("#btnSubmit").on("click", function() {
		console.log("submit 클릭");
		event.preventDefault(); // 본래 html 안에 있는 태그의 기능을 사용하지 않음
		
		var uName = $("#input-uname").val();
		var pass = $("#input-pass").val();
		var content = $("[name = 'content']").val();
		
		var guestVo = { 
			name: uName,
			pw: pass, 
			content: content
		};
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/write",		
			type : "post",
			data : guestVo,

			dataType : "json",
			success : function(guestVo){ /*성공시 처리해야될 코드 작성*/
				render(guestVo);
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name = 'content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	// 모달창 삭제버튼 클릭할 때
	$("#btnDel").on("click", function() {
		console.log("삭제버튼 클릭");
		
		var pw = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		$.ajax({
			url : "${pageContext.request.contextPath}/api/guestbook/delete",		
			type : "post",
			data : {pw: pw, no: no},

			dataType : "json",
			success : function(count){ /*성공시 처리해야될 코드 작성*/
				if(count == 1) { // 삭제 성공 시
					
					$("#delModal").modal("hide"); // 모달창 닫기
					$("#t" + no).remove(); // 해당 게시글 삭제			
					
				} else { // 삭제 실패 시
					
					$("#delModal").modal("hide"); // 모달창 닫기
					
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>
</html>