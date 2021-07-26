package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.command.CommandHandler;

public class LogoutHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		if(request.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return processForm(request, response);
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processForm(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();//세션객체 생성
		session.invalidate();//세션invalidate
		return "/member/loginForm.jsp";//로그아웃 한 후 이동할 페이지
	}
}