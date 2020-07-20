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
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- header / nav -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- asideGuest -->
		<c:import url="/WEB-INF/views/include/asideGuest.jsp"></c:import>

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<form action="${pageContext.request.contextPath}/guest/write" method="">
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
								<td colspan="4"><button type="submit">등록</button></td>
							</tr>
						</tbody>
						
					</table>
					<!-- //guestWrite -->
				</form>	
				
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

</body>
<script type="text/javascript">
	$(document).ready(function(){
		
		// 전체리스트 불러오기
		fetchList();

	});
	
	// 전체리스트 불러오기
	function fetchList() {
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/list",		
			type : "post",
			/* contentType : "application/json",
			data : , */

			dataType : "json",
			success : function(gList){
				/*성공시 처리해야될 코드 작성*/
				console.log(gList.toString());
				for(var i = 0; i < gList.length; i++) {
					render(gList[i]);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	// 방명록 html 그리기
	function render(gList) {
		var str = '';
		
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + gList.no + '</td>';
		str += '		<td>' + gList.name + '</td>';
		str += '		<td>' + gList.date + '</td>';
		str += '		<td>[삭제]</td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + gList.content + '</td>';
		str += '	</tr>';
		str += '</table>';
		
		$("#guestbookListArea").prepend(str);
	}
</script>
</html>