<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>
<!DOCTYPE html>
<html lang="ko">

  <head>
    <meta charset="UTF-8" />
    <title>2021 MINI HOMEPAGE</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/font.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/layout.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/diary.css" />
    <script src="https://kit.fontawesome.com/8bcf872b48.js" crossorigin="anonymous"></script>

  </head>
  <body>
    <div class="bookcover">
      <div class="bookdot">
        <div class="page">
          <div class="home">
            <div class="upside">
              <br><strong><span style="color:coral;"></span></strong> &emsp;&emsp;&emsp; &emsp; 
              <span class="title">${loginUser.name }의 잡동사니</span> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<!-- 로그인한 유저의 이름을 출력 -->
            </div>
            <div class="home_main">
              <div class="profile">
                <div class="profile_1">
                  <img class="profile_image" src="../upload/${loginUser.photoUrl}"/><!-- 로그인한 유저의 사진을 등록 -->
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
                  <div class="calendar" style="margin-right: 80px;">
                  	<form action="../diary/diary.do" method="post" style="margin-top: -20px;"><!--  form태그를 이용하여 post형식으로 컨트롤러로 보냄  -->
                  		<p><input type="date" name="updatedate"></p><!-- 데이트피커를 띄우는 인푼태그 -->
                  		<p><input type="submit" value="search">	<!-- 데이터피커에 입력된 날짜를 전홍하는 버튼 -->
                  	</form>  
                  </div>
                  <div style="margin-top: 40px; margin-right: 30px;">
                  	<button onclick="location.href='../diary/write.do'" style="float: right;">글쓰기</button><!-- 다이어리에 글쓰기 페이지로 이동하는 버튼 -->
                  	</div>
                  <div class="diary">
                    <div class="diary_contents">
                  
                   <c:forEach var="diary" items="${diary}"><!-- 등록되어 있는 다이어리를 출력 -->
                        <div class="diary_date">${diary.updatedate }</div><!-- 다이어리가 작성된 날짜를 출력 -->
                        <div class="diary_text">${diary.content } <!-- 다이어리에 입력한 내용을 출력 -->
							<div style="margin-bottom: 30px; margin-right: 15px;">
							  	<form name="btn_js_del" action="../diary/delete.do" method="post" style="float: right;"><!-- 다이어리를 삭제하는 form태그 -->
							  		<input type="hidden" name="bno" value="${diary.bno }"><!-- 다이이리에 대한 프라이머리키를 히든값에 저장 -->
							  		<input type="submit" value="삭제" onclick="return delete_event();"><!-- 얻은프라이머리키를 가지고 삭제하는 버튼 -->
							  	</form>
							  	<!-- 다이어리를 수정하는 form태그 -->
							  	<form name="btn_js_upd" action="../diary/update.do" method="get" style="float: right; margin-right: 10px ">
							  		<input type="hidden" name="bno" value="${diary.bno }"><!-- 다이이리에 대한 프라이머리키를 히든값에 저장 -->
							  		<input type="submit" value="수정" onclick="return update_event();"><!-- 얻은프라이머리키를 가지고 수정하는 페이지로 이동하는 버튼 --> 
							  	</form>
                      		</div>
                      		<br/>
                      		<hr/>
                  			<c:forEach var="reply" items="${reply}"><!-- 댓글을 가져와서 출력 -->
                  				<c:set var = "dno" value = "${diary.bno }"/><!-- 다이어리의 프라이머리키를 value값에 세팅 -->
                  				<c:set var = "rno" value = "${reply.bno }"/><!-- 댓글의 프라이머리키를 value값에 세팅 -->
                  				<c:choose>
                  					<c:when test = "${dno==rno }"><!-- 댓글의 프라이머리키와 다이어리의 프라이머리키가 같으면 실행 -->
			                  			<form action = "../reply/replydelete.do" method = "post"><!-- 댓글을 삭제하기위한 form태그 -->
			                  				<input type = "hidden" name = "rno" value = "${reply.rno }"><!-- 댓글의 프라이머리키를 value값에 저장 -->
											<input type = "submit" value = "X" style = "float:right;"><!-- 댓글을 삭제하는 버튼 -->
										</form>
										${reply.userid } ${reply.updatedate } : ${reply.content } <br>	<!-- 댓글을 작성한 유저아이디와 댓글을 작성한 날짜와 작성한 댓글을 출력 -->
									</c:when>
								</c:choose>
							</c:forEach>
			                <div class="w3-border w3-padding" style="border: 1px solid;'">
							<c:if test="${ loginUser.userid == null }"><!-- 로그인이 되어있지않으면 실행 -->
								<textarea rows="5" cols="50" class="w3-input w3-border newLogin" readonly>로그인 후 댓글 달기</textarea>
							</c:if>
							<c:if test="${ loginUser.userid != null }"><!-- 로그인이 되어있으면 실행 -->
								<i class="fa fa-user w3-padding-16"></i> ${ loginUser.userid }<!-- 유저아이디를 출력 -->
								<form action="../reply/reply.do" method="post"><!-- 댓글을 등록하는 form 태그 -->
									<input type="hidden" name="bno" id="no" value="${ diary.bno }"> <!-- 다이어리의 프라이머리키를 히든값에 저장 -->
									<input type="hidden" name="userid" id="id" value="${loginUser.userid }"><!-- 로그인되어있는 아이디를 히든값에 저장 -->
									<!-- 댓글을 입력할 수 있는 텍스트창 -->
									<textarea rows="5" cols="50" class="w3-input w3-border" placeholder="댓글 작성" name="content" id="reply_content"></textarea>
									<input type="submit" class="w3-button w3-border" id="reply_btn" value="댓글 등록"><!-- 댓글을 등록하는 버튼 -->
								</form>
							</c:if>
						</div>
	  				</div>                     
    				</c:forEach>
                    </div>
                    <button id="button" onclick="getTextFile()"><i class="fas fa-paw"></i> 티벳토끼의 비밀일기</button>
                    <div class="diary_contents" id="hidden">
                      <div class="diary_date">티벳토끼의 비밀일기</div>
                        <div class="diary_text">
                          <div id="secret_txt"></div>                        
                        </div>
                    </div>
                  </div>
              </div>
            </div>
          </div>
          <div class="menu_bar">
            <a href="<%=request.getContextPath() %>/home/home.do" class="menu_button1">&nbsp;&nbsp;홈</a><!-- 홈페이지로 이동하는 태그 -->
            <a href="#" class="menu_button2">&nbsp;&nbsp;다이어리</a><!-- 다이어리 페이지로 이동하는 태그 -->
            <a href="<%=request.getContextPath() %>/photo/photo.do" class="menu_button3">&nbsp;&nbsp;사진첩</a><!-- 사진첩 페이지로 이동하는 태그 -->
            <a href="<%=request.getContextPath() %>/visit/visit.do" class="menu_button4">&nbsp;&nbsp;방명록</a><!-- 방명록 페이지로 이동하는 태그 -->
          </div>
        </div>
      </div>
    </div>
  </body>
</html>