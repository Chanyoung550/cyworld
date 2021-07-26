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
						<span class="title">${loginUser.name }의 잡동사니</span> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<!-- 로그인된 유저의 이름을 출력 -->

					</div>
					<div class="home_main">
						<div class="profile">
							<div class="profile_1"><!-- 로그인된 유저의 사진을 출력 -->
								<img class="profile_image" src="../upload/${loginUser.photoUrl}"/>
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
							<div class="photo_contents">
								<form method="post" action="../visit/visitupdate.do" name="frm"><!-- 방명록을 수정하는 form태그 -->
								<table>
									<tr>
										<td><input type="text" name="userid" size="60" value = "${visit.userid }" readonly></td><!-- 받아온 유저아이디를 value값에 저장 (변경불가능) -->
									</tr>
									<tr>
										<td>
											<c:choose>
												<c:when test="${empty visit.photoUrl}"><!-- 사진이 비어있으면 실행 -->
													<img src="../upload/noimage.gif">
												</c:when>
												<c:otherwise><!-- 사진이 있으면 실행 -->
													<img src="../upload/${visit.photoUrl}"style = "width:350px; height:350px; ">
												</c:otherwise>
											</c:choose>
										<td>
									</tr>
									<tr>
										<td>
											<!-- 자겨온 방명록 내용을 value값에 세팅 -->
											<textarea cols="60" rows="10" id = "content" name="content" >${visit.content}</textarea>
										</td>
									</tr>
								</table>
								<input type = "hidden" value = "${visit.bno }" name = "bno"><!-- 프라이머리 키 를 히든값에 저장 -->
								<input type = "submit" style = "text-align:center;"value = "등록하기"><!-- 폼태그에 가지고있는 데이터를 전송하는 버튼 -->
							</form>
							</div>
						</div>
					</div>
				</div>
				<div class="menu_bar">
					<a href="<%=request.getContextPath()%>/home/home.do" class="menu_button1">&nbsp;&nbsp;홈</a> <!-- 홈페이지로 이동 -->
					<a href="<%=request.getContextPath()%>/diary/diary.do" class="menu_button2">&nbsp;&nbsp;다이어리</a> <!-- 다이어리 페이지로 이동 -->
					<a href="<%=request.getContextPath()%>/photo/photo.do" class="menu_button4">&nbsp;&nbsp;사진첩</a><!-- 사진첩 페이지로 이동 -->
					<a href="#" class="menu_button3">&nbsp;&nbsp;방명록</a> <!-- 방명록 페이지로 이동 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>