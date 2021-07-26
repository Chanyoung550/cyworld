package visit.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;
import photo.dao.PhotoDAO;
import photo.dto.PhotoVO;
import util.DBManager;
import visit.dao.VisitDAO;
import visit.dto.VisitVO;

public class VisitUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return processForm(req, res);
			
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processsubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String url = "../view/visitupdate.jsp";//이동해야할 url을 변수에 저장
		Integer bno = Integer.parseInt(req.getParameter("bno"));//방명록 번호를 integer형으로 형변환 하여 변수에 저장
		VisitDAO vDao = VisitDAO.getInstance();//DAO객체를 생성
		VisitVO vVo = vDao.selectvisitBno(bno);//방명록의 번호에 관련된 정보를 변수에 저장
		req.setAttribute("visit", vVo);//변수에 담긴 리스트를 setAttribute저장
		return url;//변수에 저장된 url로 이동
	}
	
	
	private String processsubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		String content = req.getParameter("content");//파라미터로 받은 방명록의 내용을 변수에 저장
		VisitVO vVo = new VisitVO();//vo객체 생성
		vVo.setBno(Integer.parseInt(req.getParameter("bno")));//파라미터로 받은 방명록번호를 integer형으로 형변환하여  vo에 저장
		vVo.setContent(content);//vo에 저장해놓은변수를 저장
		
		
		VisitDAO vDao = VisitDAO.getInstance();//DAO객체를 생성
		vDao.updatevisit(vVo);//방명록 수정
		res.sendRedirect("../visit/visit.do");//방명록페이지로 redirect
		return null;
	}

}
