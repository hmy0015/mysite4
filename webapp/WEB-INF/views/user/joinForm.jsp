<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- header / nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- asideUser -->
		<c:import url="/WEB-INF/views/include/asideUser.jsp"></c:import>

		<div id="content">
			
			<div id="content-head">
            	<h3>회원가입</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>회원</li>
            			<li class="last">회원가입</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="user">
				<div id="joinForm">
					<form action="${pageContext.request.contextPath}/user/join" method="">

						<!-- 아이디 -->
						<div class="form-group">
							<label class="form-text" for="input-uid">아이디</label> 
							<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							<button type="button" id="btnIdCheck">중복체크</button>
						</div>
						
						<!-- 중복검사 후 메시지 출력 -->
						<span id="checkMsg"></span>
						
						<!-- 비밀번호 -->
						<div class="form-group">
							<label class="form-text" for="input-pass">패스워드</label> 
							<input type="password" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
						</div>

						<!-- 이메일 -->
						<div class="form-group">
							<label class="form-text" for="input-name">이름</label> 
							<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
						</div>

						<!-- //나이 -->
						<div class="form-group">
							<span class="form-text">성별</span> 
							
							<label for="rdo-male">남</label> 
							<input type="radio" id="rdo-male" name="gender" value="male" > 
							
							<label for="rdo-female">여</label> 
							<input type="radio" id="rdo-female" name="gender" value="female" > 

						</div>

						<!-- 약관동의 -->
						<div class="form-group">
							<span class="form-text">약관동의</span> 
							
							<input type="checkbox" id="chk-agree" value="" name="">
							<label for="chk-agree">서비스 약관에 동의합니다.</label> 
						</div>
						
						<span><font color='red'>* 약관동의가 필요합니다.</font></span>
						
						<!-- 버튼영역 -->
		                <div class="button-area">
		                    <button type="submit" id="btn-submit">회원가입</button>
		                </div>
						
					</form>
				</div>
				<!-- //joinForm -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>		

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	$("#btnIdCheck").on("click", function() {
		var uId = $("#input-uid").val();
		console.log(uId);
		
		// 객체 생성 (여러 개의 파라미터를 보낼 때 용이)
		var userInfo = {
				userId: uId
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/user/idcheck",		
			type : "post",
			/* contentType : "application/json", */
			data : userInfo,

			dataType : "json",
			success : function(userVo){ // function(변수명:컨트롤러에서 넘어오는 거랑 상관없이 새로 변수 만드는 거임)
				
				/*성공 시 처리해야될 코드 작성*/
				
				if(userVo == true) {
					$("#checkMsg").html("&emsp;<font color='blue'>* 사용 가능한 아이디입니다.</font>");
				}
				else {
					console.log($("#input-uid").val(""));
					$("#checkMsg").html("&emsp;&emsp;<font color='red'>* 사용할 수 없는 아이디입니다.</font>");
				}
			},
			error : function(XHR, status, error) {
				/* 실패 시 처리해야될 코드 작성*/

				console.error(status + " : " + error);
			}
		});

	});
</script>
</html>