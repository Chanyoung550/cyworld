package reply.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;
import member.dao.MemberDAO;
import member.dto.MemberVO;
import reply.dao.ReplyDAO;
import reply.dto.ReplyVO;

public class ReplyHandler implements CommandHandler {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int bno = Integer.parseInt(req.getParameter("bno"));//파라미터로 받은 댓글번호를 integer형으로 형변환하여 변수에 저장
		String userid = req.getParameter("userid");//파라미터로 받은 유저아이디를 변수에 저장
		String content = req.getParameter("content");//파라미터로 받은 댓글을 변수에 저장
				
		ReplyVO rVo = new ReplyVO();//댓글vo객체 생성
		rVo.setBno(bno);//댓글vo객체에 댓글번호를 저장
		rVo.setUserid(userid);//댓글vo객체에 유저아이디를 저장
		rVo.setContent(content);//댓글vo객체에 댓글을 저장
		
		ReplyDAO rDao = ReplyDAO.getInstance();//DAO객체를 생성
		rDao.insertReply(rVo);	//댓글등록
		res.sendRedirect("../diary/diary.do");//댓글이 등록된 후 다이어리페이지로 redirect
		
		return null;
	
	}
}


