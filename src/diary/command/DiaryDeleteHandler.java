package diary.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;

public class DiaryDeleteHandler implements CommandHandler {
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
		Integer bno = Integer.valueOf(req.getParameter("bno"));//다이어리 번호를 request로 받아서 인티저로 형변환 후 변수에 저장.
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체 생성
		dDao.deleteDiary(bno);//DAO에 deleteDiary메서드를 호출
		res.sendRedirect("../diary/diary.do");//다이어리 페이지로 redirect.
		return null;
	}

}
