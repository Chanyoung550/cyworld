package photo.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import photo.dao.PhotoDAO;

public class PhotoDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return null;
			
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processForm(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer bno = Integer.parseInt(req.getParameter("bno"));//사진번호를 파라미터로 받아서 Integer로 형변환 후 변수에 저장
		PhotoDAO pDao = PhotoDAO.getInstance();//DAO객체를 생성
		pDao.deletePhoto(bno);//받은 프라이머리키와 같은 데이터를 지움
		res.sendRedirect("../photo/photo.do");//사진첩으로 redirect
		return null;
	}
}
