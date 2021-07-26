package diary.command;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;
import member.dao.MemberDAO;
import member.dto.MemberVO;
import reply.dao.ReplyDAO;
import reply.dto.ReplyVO;

public class DiaryHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String url = "../view/diary.jsp";	//가야할 url을 담아줌
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체를 생성
		List<DiaryVO> diary = dDao.getSeletLeast();//getSeletLeast메소드를 호출하여 리스트타입의 변수에 저장
		req.setAttribute("diary", diary);//변수에 담긴 리스트를 setAttribute저장
		
		ReplyDAO rDao = ReplyDAO.getInstance();//DAO객체를 생성
		List<ReplyVO> reply = rDao.selectAllReplyy();//selectAllReplyy메소드를 호출하여 리스트타입의 변수에 저장
		req.setAttribute("reply", reply);//변수에 담긴 리스트를 setAttribute저장
	
		return url;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		String updatedate =	req.getParameter("updatedate");//날짜를 파라미터로 받아와 변수에 저장
		DiaryVO dVo = new DiaryVO();//VO객체를 생성
		dVo.setUpdatedate(updatedate);//VO에 파라미터를 넣어줌
		String url = "../view/diary.jsp";//가야할 url을 담아줌
		DiaryDAO dDao = DiaryDAO.getInstance();//DAO객체를 생성
		List<DiaryVO> diary = dDao.selectDiary(dVo);//selectDiary메소드를 호출하여 리스트타입의 변수에 저장
		req.setAttribute("diary", diary);//변수에 담긴 리스트를 setAttribute저장
		return url;
	}
}


