package diary.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.DDIV;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;

public class DiaryWriteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return "/view/diaryWrite.jsp";
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DiaryVO dVo = new DiaryVO();//vo객체 생성
		
		dVo.setTitle(req.getParameter("title"));//vo에 제목을 넣어줌.
		dVo.setContent(req.getParameter("content"));//vo에 내용을 넣어줌.
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체를 생성
		dDao.insertDiary(dVo);//insertDiary메소드를 호출
		res.sendRedirect("../diary/diary.do");//다이이리 페이지로 redirect
		return null;
	}
}