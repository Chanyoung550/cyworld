<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" href="../css/shopping.css">
<script type="text/javascript" src="../js/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>글쓰기</h1>
		<form name="frm" method="post" action="../diary/write.do"><!-- 다이어리를 작성하는 from태그 -->
			<table>			
				<tr>
					<th>제목</th><!-- 등록할 다이어리의 제목을 입력할 수 있는 텍스트 창 -->
					<td><input type="text" size="70" name="title"></td>
				</tr>
				<tr>
					<th>내용</th><!-- 등록할 다이어리의 내용을 입력할 수 있는 텍스트 창 -->
					<td><textarea cols="70" rows="15" name="content"></textarea></td>
				</tr>
			</table>
			<br>
			<br> 
				<input type="submit" value="등록" onclick="return boardCheck()"> <!-- form태그에 작성된 데이터를 전송하는 버튼 -->
				<input type="button" value="목록" onclick="location.href = '../diary/diary.do'"><!-- 다이어리 페이지로 이동하는 버튼 -->
		</form>
	</div>
</body>
</html>