package reply.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;
import reply.dao.ReplyDAO;
import reply.dto.ReplyVO;

public class ReplyDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return null;
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException{
		

	int rno = Integer.parseInt(req.getParameter("rno"));//파라미터로 받은 댓글번호를 integer형으로 형변환 하여 변수에저장
	ReplyDAO rDao = ReplyDAO.getInstance();//DAO객체를 생성
	rDao.deletereply(rno);//댓글 삭제
	res.sendRedirect("../diary/diary.do");//댓글을 삭제한 후  다이어리 페이지로 redirect
	return null;
	}

}
