package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class LoginHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
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
		String url = "/member/loginForm.jsp";//이동해야할 url을 변수에 저장
		HttpSession session = request.getSession();//세션 객체 생성
		if (session.getAttribute("loginUser") != null) {// 이미 로그인 된 사용자이면
			url = "../main.jsp"; // 메인 페이지로 이동한다.
		}
		return url;//변수에 저장된 url로 이동
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String url = "/member/loginForm.jsp";//이동해야할 url을 변수에 저장
		String userid = request.getParameter("userid");//파라미터로 받아온 유저아이디를 변수에 저장
		String pwd = request.getParameter("pwd");//파라미터로 받아온 패스워드를 변수에 저장
		MemberDAO mDao = MemberDAO.getInstance();//DAO객체를 생성
		int result = mDao.userCheck(userid, pwd);//아이디와 비밀번호가 일치하는지 확인해서 변수에 저장
		if (result == 1) {//로그인이 성공되면 실행
			MemberVO mVo = mDao.getMember(userid);//로그인한 유저아이디에 대한 정보를 vo에담음
			HttpSession session = request.getSession();//세션 객체생성
			session.setAttribute("loginUser", mVo);//vo에 담긴 정보를 세션에 저장
			request.setAttribute("message", "로그인에 성공했습니다.");
			url = "../main.jsp";//이동할 url을 변수에 저장
		} else if (result == 0) {//로그인한 정보가 일치하지 않으면 실행
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		} else if (result == -1) {//로그인한 정보가 존재하지 않으면 실행
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		return url;//변수에 저장된 url로 이동
	}
}