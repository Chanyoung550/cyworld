package visit.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import visit.dao.VisitDAO;
import visit.dto.VisitVO;

public class VisitWriteHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException{
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
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "../view/visitWrite.jsp";//이동해야할 url을 변수에 저장
		return url;//변수에 저장된 url로 이동
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException{
		VisitVO vVo = new VisitVO();//vo객체 생성
		vVo.setContent(req.getParameter("content"));//파라미터로 받은 방명록 내용을 vo에저장
		vVo.setUserid(req.getParameter("userid"));//파라미터로 받은 방명록을 작성한  유저아이디를 vo에저장
		vVo.setPhotoUrl(req.getParameter("photoUrl"));//파라미터로 받은 방명록의 사진을 vo에저장
		VisitDAO vDao = VisitDAO.getInstance();//DAO객체를 생성
		vDao.insertVisit(vVo);//방명록 등록
		res.sendRedirect("../visit/visit.do");//방명록 페이지로 redirect
		return null;
	}
}
