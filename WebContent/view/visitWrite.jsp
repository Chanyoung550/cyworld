<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>2021 MINI HOMEPAGE</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/photo.css" />
</head>
<body>
	<div class="bookcover">
		<div class="bookdot">
			<div class="page">
				<div class="home">
					<div class="upside">
						<br><strong><span style="color:coral;"></span></strong> &emsp;&emsp;&emsp; &emsp; 
						<span class="title">${loginUser.name }의 잡동사니</span> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</div><!-- 로그인된 유저아이디의 이름을 출력 -->
					<div class="home_main">
						<div class="profile">
							<div class="profile_1">
								
							</div>
							
							<div class="profile_3">
								<div class="profile-dropdown">
									<div class="dropdown-btn">
										<div class="dropdown-title">Related Link</div>
										<div class="triangle-down"></div>
									</div>
									<div class="dropdown-content">
										<a href="#"
											target="_blank">Devlog</a> <a
											href="https://github.com/" target="_blank">Github</a><!-- 깃허브로 이동하는 태그 -->
										<a href="https://www.instagram.com/" target="_blank">Instagram</a><!-- 인스타로 이동하는 태그 -->
									</div>
								</div>
							</div>
						</div>

						<div class="home_contents">
							<form action = "../visit/visitWrite.do" method = "post"><!-- 방명록을 작성하는 form태그 -->
								<table style = "font:black;">
									<tr>
										<th>아이디</th><!-- 로그인된 유저아이디를 value값에 세팅 -->
										<td><input type = "text" name = "userid" value = "${loginUser.userid}" readonly="readonly">
									</tr>
									
									<tr>
										<th >글작성</th><!-- 글을 작성하는 텍스트창 -->
										<td><textarea cols="60" rows="10" name="content"></textarea></td>
									</tr>
								</table>
								<input type = "hidden" name = "photoUrl" value="${loginUser.photoUrl }"><!-- 로그인된 유저가 가지고 있는 사진을 히든값에 저장 -->
								<input type = "submit" value = "등록하기"	><!-- form데이터에 입력된 정보를 전달하는 버튼 -->
							</form>
						</div>
					</div>
				</div>
				<div class="menu_bar">
					<a href="<%=request.getContextPath()%>/home/home.do" class="menu_button1">&nbsp;&nbsp;홈</a> <!-- 홈페이지로 이동하는 태그 -->
					<a href="<%=request.getContextPath()%>/diary/diary.do" class="menu_button2">&nbsp;&nbsp;다이어리</a> <!-- 다이어리 페이지로 이동하는 태그 -->
					<a href="<%=request.getContextPath()%>/photo/photo.do" class="menu_button3">&nbsp;&nbsp;사진첩</a> <!-- 사진첩 페이지로 이동하는 태그 -->
					<a href="#" class="menu_button4">&nbsp;&nbsp;방명록</a><!-- 방명록 페이지로 이동하는 태그 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>