package home.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import diary.dao.DiaryDAO;
import diary.dto.DiaryVO;
import home.dao.HomeDAO;
import member.dao.MemberDAO;
import member.dto.MemberVO;
import photo.dto.PhotoVO;
import visit.dao.VisitDAO;
import visit.dto.VisitVO;

public class HomeHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
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
		String url = "../view/home.jsp";//가야할 url을 담아줌
		
		HomeDAO dDao = HomeDAO.getInstance();//DAO객체를 생성
		int dcount = dDao.diarycount();//int타입의 변수에 diarycount에서 가져온 데이터를 저장
		int pcount = dDao.photocount();//int타입의 변수에 photocount에서 가져온 데이터를 저장
		int vcount = dDao.visitcount();//int타입의 변수에 visitcount에서 가져온 데이터를 저장
		
		DiaryVO diary = dDao.selectdiaryone();//DiaryVO타입의 변수에 selectdiaryone에서 가져온 데이터를 저장
		String ddiary = diary.getContent();//가져온 변수에서 내용만 꺼내서 변수에 저장
		PhotoVO photo = dDao.selectphotoone();//PhotoVO타입의 변수에 selectphotoone에서 가져온 데이터를 저장
		String pphoto = photo.getContent();//가져온 변수에서 내용만 꺼내서 변수에 저장
		VisitVO visit = dDao.selectvisitone();//VisitVO타입의 변수에 selectvisitone에서 가져온 데이터를 저장
		String vvisit = visit.getContent();//가져온 변수에서 내용만 꺼내서 변수에 저장
		
		req.setAttribute("diary", ddiary);//변수에 담긴 리스트를 setAttribute저장
		req.setAttribute("photo", pphoto);//변수에 담긴 리스트를 setAttribute저장
		req.setAttribute("visit", vvisit);//변수에 담긴 리스트를 setAttribute저장
		
		req.setAttribute("dcount", dcount);//변수에 담긴 리스트를 setAttribute저장
		req.setAttribute("pcount", pcount);//변수에 담긴 리스트를 setAttribute저장
		req.setAttribute("vcount", vcount);//변수에 담긴 리스트를 setAttribute저장
		return url;//변수에 저장된 url로 이동
	}
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}
}
