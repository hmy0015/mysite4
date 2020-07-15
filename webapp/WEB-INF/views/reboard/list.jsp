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
				<div id="list">
					<form action="${pageContext.request.contextPath}/reboard/list" method="">
						<div class="form-group text-right">
							<input type="text" name="keyword" value="">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${rList}" var="vo" varStatus="status">
							<c:set var="nbsp" value = "&nbsp;&nbsp;"/>
								<tr>
									<td>${vo.no}</td>
									
									<td class="text-left">
										<!-- depth 크기에 맞춰 공백 추가 -->
										<c:forEach begin="2" end="${vo.depth}" step="1" var="depth"> ${nbsp} </c:forEach>
										
										<!-- 답글 제목 앞에 "≫" 문구를 추가하여 답글임을 표시 -->
										<c:if test="${vo.depth > 1}">≫</c:if>
										
										<!-- 삭제 된 게시글일 경우 제목에 링크 연결하지 않음, 아닐 경우 제목에 링크 연결 -->
										<c:choose>
											<c:when test="${vo.state == 'del'}"> 삭제 된 게시글입니다. </c:when>
											
											<c:otherwise>
												<a href="${pageContext.request.contextPath}/reboard/read/${vo.no}">${vo.title}</a>
											</c:otherwise>
										</c:choose>
									</td>
									
									<!-- 삭제 된 게시글일 경우 no와 제목 이외의 데이터를 표시하지 않음, 아닐 경우 content를 제외하고 모두 표시 -->
									<c:choose>
										<c:when test="${vo.state == 'del'}"> 
											<td></td> <td></td> <td></td> <td></td>
										</c:when>
										
										<c:otherwise>
											<td>${vo.name}</td>
											<td>${vo.hit}</td>
											<td>${vo.reg_date}</td>
											<td>
												<c:if test="${authUser.no == vo.user_no}">
													<a href="${pageContext.request.contextPath}/reboard/deleteForm/${vo.no}">[삭제]</a>
												</c:if>
											</td>
										</c:otherwise>
									</c:choose>				
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<c:forEach begin="1" end="${page}" step="1" var="page">
								<li><a href="${pageContext.request.contextPath}/reboard/list?page=${page}">${page}</a></li>
							</c:forEach>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<c:if test="${authUser != null}">
						<a id="btn_write" href="${pageContext.request.contextPath}/reboard/writeForm/${authUser.no}">글쓰기</a>
					</c:if>
				</div>
				<!-- //list -->
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
