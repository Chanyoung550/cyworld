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
	href="<%=request.getContextPath()%>/css//layout.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/visit.css" />
<script src="https://kit.fontawesome.com/8bcf872b48.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="bookcover">
		<div class="bookdot">
			<div class="page">
				<div class="home">
					<div class="upside">
						<br><strong><span style="color:coral;"></span></strong> &emsp;&emsp;&emsp; &emsp; 
						<span class="title">${loginUser.name }의 잡동사니</span> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</div><!-- 로그인된 유저의 이름을 출력 -->
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
							<!-- 방명록을 등록하는 페이지로 이동하는 버튼 -->
							<input type = "button" value = "등록하기" style = "float:right;"onclick = "location.href = '../visit/visitWrite.do'">
							<div class="visit_contents" style = "margin-top:25px;">
							<c:forEach var = "vlist" items = "${vlist }"><!-- 방명록을 출력 -->
								<div class="visit_title">
									NO.${vlist.bno} <span style="color: mediumblue">${vlist.userid }</span> <!-- 방명록을 등록한 유저아이디를 출력 -->
										<i class="fas fa-home"></i>
										<span class="visit_date">${vlist.updatedate }</span><!-- 방명록을 등록한 날짜를 출력 -->
									</div>
									<div class="visit_container">
										<div class="visit_picture visit_background3">
											<c:choose>
												<c:when test="${empty vlist.photoUrl}"><!-- 방명록에 사진이 없을경우 실행 -->
													<img src="../upload/noimage.gif">
												</c:when>
												<c:otherwise><!-- 방명록에 사진이 있을경우 실행 -->
													<img src="../upload/${vlist.photoUrl}"style = "width:150px; height:150px; "><!-- 사진을 출력 -->
												</c:otherwise>
											</c:choose>
										</div>
										<div class="visit_text">${vlist.content }</div><!-- 작성된 방명록의 내용을 출력 -->
									<c:set var="visitUser" value="${vlist.userid }"/><!-- 방명록을 등록한 유저의 아이디를 value값에 세팅 -->
									<c:set var="logUser" value="${loginUser.userid}"/><!-- 현재 로그인된 유저의 아이디를 value값에 세팅 -->
									<c:if test="${visitUser==logUser}"><!-- 방명록을 등록한 유저와 로그인된 유저의 아이디가 같으면 실행 -->
									<form action = "../visit/visitdelete.do" method = "post"><!-- 방명록을 지우는 form태그 -->
										<input type="hidden" name="deletevisit" value="${vlist.bno}"><!-- 방명록의 프라이머리키를 히든값에 저장 -->
										<input type="submit" value="삭제" style="float:right;"><!-- 방명록을 삭제하는 버튼 -->
									</form>
									<form action = "../visit/visitupdate.do" method="get"><!-- 방명록을 수정하는 페이지로 이동하는 form 태그 -->
										<input type = "hidden" name = "bno" value = "${vlist.bno }"	><!-- 방명록의 프라이머리키를 히든값에 저장 -->
										<input type = "submit" value = "수정" style="float:right;"><!-- 방명록을 수정하는 버튼 -->
									</form>
									</c:if>
									</div>
								</c:forEach>
							</div>
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