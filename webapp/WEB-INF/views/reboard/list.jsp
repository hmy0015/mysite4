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
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
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
						
							<%-- <c:set var="i" value="-1"/> --%>
							<c:forEach items="${rList}" var="vo" varStatus="status">
							<c:set var="nbsp" value = "&nbsp;"/>
								<%-- <input type="hidden" value=${i = i + 1}> --%>
								<tr>
									<%-- <td>${rList.size() - i}</td> --%>
									<td>${vo.no}</td>
									
									<td class="text-left">
										<!-- depth 크기에 맞춰 공백 추가 -->
										<c:forEach begin="2" end="${vo.depth}" step="1" var="depth"> ${nbsp} </c:forEach>
										
										<!-- 답글 제목 앞에 "└" 문구를 추가하여 답글임을 표시 -->
										<c:if test="${vo.depth > 1}"> └ </c:if>
										
										<!-- 게시글 제목 -->
										<a href="${pageContext.request.contextPath}/reboard/read/${vo.no}">${vo.title}</a>
									</td>
									<td>${vo.name}</td>
									<td>${vo.hit}</td>
									<td>${vo.reg_date}</td>
									<td>
										<c:if test="${authUser.no == vo.user_no}">
											<a href="">[삭제]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
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
