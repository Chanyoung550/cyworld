<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 폼</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
	<h2>로그인</h2>
	<form action='<c:url value="/member/login.do" />' method="post" name="frm"><!-- 로그인 정보를 확인하는 form 태그 -->
		<table>
			<tr>
				<td>아이디</td><!-- 아이디를 입력하는 텍스트 -->
				<td><input type="text" name="userid" value="${userid}"></td>
			</tr>
			<tr>
				<td>암 호</td><!-- 비밀번호를 입력하는 텍스트 -->
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<c:url value="/member/join.do" var="jurl" />
				<td colspan="2" align="center">
					<!-- form태그에 입력된 데이터를 보내는 버튼 -->
					<input type="submit" value="로그인" onclick="return loginCheck()">&nbsp;&nbsp; 
					<input type="reset" value="취소"> &nbsp;&nbsp; 
					<!-- 회원가입페이지로 이동하는 버튼 -->
					<input type="button" value="회원 가입" onclick="location.href='${jurl}'">
					
			</tr>
			<tr>
				<td colspan="2">${message}</td>
			</tr>
		</table>
	</form>
</body>
</html>