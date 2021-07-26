package member.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class MemberUpdateHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return processForm(request, response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processSubmit(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");//유저아이디를 변수에 저장
		MemberDAO mDao = MemberDAO.getInstance();//DAO객체를 생성
		MemberVO mVo = mDao.getMember(userid);//vo타입의 변수에 유저 아이디의 정보를 저장
		request.setAttribute("mVo", mVo);//변수에 담긴 리스트를 setAttribute저장
		return "/member/memberUpdateForm.jsp";//회원정보변경페이지로 이동
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		request.setCharacterEncoding("UTF-8"); // 한글 깨짐을 방지
		// 폼에서 입력한 회원 정보 얻어오기
		String userid = request.getParameter("userid");//유저아이디를 변수에 저장
		String pwd = request.getParameter("pwd");//패스워드를 변수에 저장
		String email = request.getParameter("email");//이메일을 변수에 저장
		String phone = request.getParameter("phone");//폰번호를 변수에 저장
		String grade = request.getParameter("grade");//유저등급을 변수에 저장
		// 회원 정보를 저장할 객체 생성
		MemberVO mVo = new MemberVO();//vo객체 생성
		mVo.setUserid(userid);//vo객체에 유저아이디를 저장
		mVo.setPwd(pwd);//vo객체에 패스워드를 저장
		mVo.setEmail(email);//vo객체에 이메일을 저장
		mVo.setPhone(phone);//vo객체에 폰번호를 저장
		mVo.setGrade(Integer.parseInt(grade));//vo객체에 Integer로 형변환한 등급을 저장
		MemberDAO mDao = MemberDAO.getInstance();//DAO객체를 생성
		mDao.updateMember(mVo);//입력된 파라미터로 유저 정보를 업데이트
		response.sendRedirect("login.do");//로그인페이지로 redirect
		return null;
	}
}