<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 전용 패이지</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
	<h2>회원 전용 페이지</h2>
	<!-- 로그인이 완료된 후 이동하는 페이지 -->
	<form action="logout.do">
		<table>
			<tr>
				<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td><!-- 세션에 들어있는 유저이름과 유저아이디를 출력 -->
			</tr>
			
			<tr>
				<c:url value="/member/update.do?userid=${loginUser.userid}" var="uurl" /><!-- value값에 세션에 있는 유저이아디를 get방식으로  회원정보를 변경할 수 있는 페이지로 이동할수 있는 url로 저장-->
				<td colspan="2" align="center">
					<input type="submit" value="로그아웃"> &nbsp;&nbsp; <!-- 로그아웃 버튼 -->
					<input type="button" value="회원정보변경" onclick="location.href='${uurl}'"> &nbsp;&nbsp;
					<!-- 회원 정보를 변경할 수 있는 페이지로 이동. -->
					<input type="button" value = "싸이월드" onclick = "location.href = '../home/home.do'">
					<!-- 싸이월드 홈 페이지로 이동. -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>