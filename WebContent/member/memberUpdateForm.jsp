<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 수정 폼</title>
<script type="text/javascript" src="../js/member.js"></script>
</head>
<body>
	<h2>회원 수정</h2>
	<form action="update.do" method="post" name="frm">
		<table>
			<tr>
				<td>이름</td><!-- 로그인된 유저의 이름을 가져옴 -->
				<td><input type="text" name="name" size="20"
					value="${mVo.name}" readonly></td>
			</tr>
			<tr>
				<td>아이디</td><!-- 로그인된 유저의 아이디을 가져옴 -->
				<td><input type="text" name="userid" size="20"
					value="${mVo.userid}" readonly></td>
			</tr>
			<tr>
				<td>암 &nbsp; 호</td><!-- 수정할 비밀번호를 입력하는 텍스트 -->
				<td><input type="password" name="pwd" size="20">*</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td><!-- 위에서 입력한 비밀번호와 동일한지 확인하는 텍스트 -->
				<td><input type="password" name="pwd_check" size="20">*</td>
			</tr>
			<tr>
				<td>이메일</td><!-- 로그인된 유저의 이메일을 가져옴 -->
				<td><input type="text" name="email" size="20"
					value="${mVo.email}"></td>
			</tr>
			<tr>
				<td>전화번호</td><!-- 로그인된 유저의 전화번호를 가져옴 -->
				<td><input type="text" name="phone" size="20"
					value="${mVo.phone}"></td>
			</tr>
			<tr>
				<td>등급</td><!-- 로그인된 유저의 등급을 가져옴 -->
				<td><c:choose><!-- 등급이 0이면 실행 -->
						<c:when test="${mVo.grade==0}">
							<input type="radio" name="grade" value="0" checked="checked"> 일반회원
							<input type="radio" name="grade" value="1"> 관리자
						</c:when>
						<c:otherwise><!-- 등급이 1이면 실행 -->
							<input type="radio" name="grade" value="0"> 일반회원
							<input type="radio" name="grade" value="1" checked="checked"> 관리자
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" onclick="return joinCheck()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<!-- 수정된 데이터를 전달하는 버튼 -->
				</td>
			</tr>
		</table>
	</form>
</body>
</html>