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
								<!-- 사진을 등록하는 페이지로 이동하는 버튼 -->
								<input type = "button" style = "float:right;"value = "등록하기" onclick = "location.href='../photo/photoWrite.do'">
								<c:forEach var = "photo" items = "${photo }"><!-- 등록된 사진을 출력 -->
									<div class="photo_title"> &emsp;&nbsp;${photo.title }<!-- 사진의 제목을 출력 -->
										<span class="photo_title_date">${photo.updatedate }</span><!-- 사진을 등록한 날짜를 출력 -->
									</div>
									<form action = "../photo/photoDelete.do" method = "post"><!-- 사진을 삭제하는 form태그 -->
										<input type = "hidden" name = "bno" value = "${photo.bno }"	><!-- 사진의 프라이머리 키를 히든값에 저장 -->
										<input type = "submit" value = "삭제"style = "float:right;"><!-- 사진을 삭제하는 버튼 -->
									</form>
									<form action = "../photo/photoUpdate.do" method="get"><!-- 사진을 수정하는 form 태그 -->
										<input type = "hidden" name = "title" value = "${photo.title }"><!-- 사진의 제목을 히든값에 저장 -->
										<input type = "hidden" name = "content" value = "${photo.content }"><!-- 사진의 내용을 히든값에 저장 -->
										<input type = "hidden" name = "bno" value = "${photo.bno }"	><!-- 사진의 프라이머리키를 히든값에 저장 -->
										<input type = "hidden" name = "photoUrl" value="${photo.photoUrl }"><!-- 사진의url을 히든값에 저장 -->
										<input type = "submit" value = "수정" style = "float:right;"><!-- 사진을 수정하는 페이지로 이동하는 버튼 -->
									</form>
									<c:choose>
										<c:when test="${empty photo.photoUrl}"><!-- 등록되어있는 사진이 없으면 실행 -->
											<img src="../upload/noimage.gif">
										</c:when>
										<c:otherwise><!-- 등록되어있는 사진이 있을 경우 실행 -->
											<img src="../upload/${photo.photoUrl}"style = "width:350px; height:350px; "><!-- 등록한 사진을 출력 -->
										</c:otherwise>
									</c:choose>
									<div class="photo_text">${photo.content }</div><!-- 등록한 사진의 내용을 출력 -->
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="menu_bar">
					<a href="<%=request.getContextPath()%>/home/home.do" class="menu_button1">&nbsp;&nbsp;홈</a> <!-- 홈페이지로 이동 -->
					<a href="<%=request.getContextPath()%>/diary/diary.do" class="menu_button2">&nbsp;&nbsp;다이어리</a> <!-- 다이어리 페이지로 이동 -->
					<a href="#" class="menu_button3">&nbsp;&nbsp;사진첩</a> <!-- 사진첩 페이지로 이동 -->
					<a href="<%=request.getContextPath()%>/visit/visit.do" class="menu_button4">&nbsp;&nbsp;방명록</a><!-- 방명록 페이지로 이동 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>