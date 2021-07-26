package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.dao.MemberDAO;

public class IdCheckHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			String userid = request.getParameter("userid");//파라미터로 전달된 유저아이디를 변수에 저장
			MemberDAO mDao = MemberDAO.getInstance();//DAO객체를 생성
			int result = mDao.confirmID(userid);//int타입의 변수에 confirmID에서 가져온 데이터를 저장
			request.setAttribute("userid", userid);//변수에 담긴 리스트를 setAttribute저장
			request.setAttribute("result", result);//변수에 담긴 리스트를 setAttribute저장
			return "/member/idcheck.jsp";//아이디 체크하는 페이지로 이동
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
}