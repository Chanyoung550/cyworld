<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글수정</title>
<link rel="stylesheet" type="text/css" href="../css/shopping.css">
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>글수정</h1>
		
		<form name="frm" method="post" action="../diary/update.do"><!-- 다이어리를 수정하는 form 태그 -->
			<input type="hidden" name="bno" value="${diary.bno}"><!-- 다이어리의 프라이머리키를 히든값에 저장 -->
			<input type="hidden" name="updatedate" value="${diary.updatedate}"><!-- 다이어리 작성한 날짜를 히든값에 저장 -->
			<table>			
				<tr>
					<th>제목</th><!-- 다이어리의 제목을 입력하는 텍스트 창 -->
					<td><input type="text" size="70" name="title" value="${diary.title }"> * 필수</td>
				</tr>
				<tr>
					<th>내용</th><!-- 다이어리의 내용을 입력하는 텍스트 창 -->
					<td><textarea cols="70" rows="15" name="content">${diary.content }</textarea></td>
				</tr>
			</table>
			<br>
			<br> 
			<input type="submit" value="수정"><!-- 다이어리를 수정하는 버튼 -->
			<input type="button" value="목록" onclick="location.href='../diary/diary.do'"><!-- 다이어리 페이지로 이돟하는 태그 -->
		</form>
		
	</div>
</body>
</html>