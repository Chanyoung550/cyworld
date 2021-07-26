package visit.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import photo.dao.PhotoDAO;
import visit.dao.VisitDAO;
import visit.dto.VisitVO;

public class VisitdeleteHandler implements CommandHandler{
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
		return null;
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException{
		Integer bno = Integer.parseInt(req.getParameter("deletevisit"));//파라미터로 받은 방명록번호를 integer형으로 형변환하여 변수에저장 
		VisitDAO bDao = VisitDAO.getInstance();//DAO객체를 생성
		bDao.deleteVisit(bno);//방명록 삭제
		res.sendRedirect("../visit/visit.do");//방명록 페이지로 redirect
		return null;
	}
}
