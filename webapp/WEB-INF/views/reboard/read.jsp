<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- header / nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- asideBoard -->
		<c:import url="/WEB-INF/views/include/asideBoard.jsp"></c:import>

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">댓글게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="read">
					<form action="#" method="get">
						<!-- 작성자 -->
						<div class="form-group">
							<span class="form-text">작성자</span>
							<span class="form-value">${vo.name}</span>
						</div>
						
						<!-- 조회수 -->
						<div class="form-group">
							<span class="form-text">조회수</span>
							<span class="form-value">${vo.hit}</span>
						</div>
						
						<!-- 작성일 -->
						<div class="form-group">
							<span class="form-text">작성일</span>
							<span class="form-value">${vo.reg_date}</span>
						</div>
						
						<!-- 제목 -->
						<div class="form-group">
							<span class="form-text">제 목</span>
							<span class="form-value">${vo.title}</span>
						</div>
					
						<!-- 내용 -->
						<div id="txt-content">
							<span class="form-value" >${vo.content}</span>
						</div>
						
						<a id="btn_modify" href="${pageContext.request.contextPath}/reboard/list">목록</a>
						
						<c:if test="${authUser != null}"> <!-- 로그인 한 상태가 아니라면 보이지 않음 -->
							<a id="btn_modify" href="${pageContext.request.contextPath}/reboard/replyForm/${no}">답글</a>
						</c:if>
						
						<c:if test="${authUser.no == vo.user_no}"> <!-- 해당 글의 작성자가 아니라면 보이지 않음 -->
							<a id="btn_modify" href="${pageContext.request.contextPath}/reboard/modifyForm/${vo.no}">수정</a>
						</c:if>
						
					</form>
	                <!-- //form -->
				</div>
				<!-- //read -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

</html>
