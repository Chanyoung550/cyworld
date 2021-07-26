package diary.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;

public class DiaryUpdateHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return submitForm(req, res);
			
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processForm(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String submitForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String url ="../view/diaryUpdate.jsp";//가야할 url을 담아줌
		Integer bno = Integer.parseInt(req.getParameter("bno"));//받아온 다이어리번호를 integer로 형변환 후 변수에 저장
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체를 생성
		DiaryVO dVo = dDao.selectOneBoardByBno(bno);//DiaryVO타입의 변수에 selectOneBoardByBno에서 가져온 데이터를 저장
		req.setAttribute("diary", dVo);//변수에 담긴 리스트를 setAttribute저장
		return url;
	}
	

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		DiaryVO dVo = new DiaryVO();//DiaryVO의 객체 생성
		dVo.setBno(Integer.parseInt(req.getParameter("bno")));//vo에 integer로 형변환한 책번호를 넣어줌.
		dVo.setTitle(req.getParameter("title"));//vo에 제목을 넣어줌.
		dVo.setContent(req.getParameter("content"));//vo에 내용을 넣어줌.
		dVo.setUpdatedate(req.getParameter("updatedate"));//vo에  날짜를 넣어줌.
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체를 생성
		dDao.updateDiary(dVo);//updateDiary메소드를 호출
		res.sendRedirect("../diary/diary.do");//다이이리 페이지로 redirect
		return null;
	}

}
