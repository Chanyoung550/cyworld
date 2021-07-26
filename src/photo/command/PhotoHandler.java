package photo.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.command.CommandHandler;

import photo.dao.PhotoDAO;
import photo.dto.PhotoVO;
import reply.dao.ReplyDAO;
import reply.dto.ReplyVO;

public class PhotoHandler implements CommandHandler {
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
      String url = "../view/photo.jsp";    //이동할 url을 변수에 저장  
      PhotoDAO pDao = PhotoDAO.getInstance();//DAO객체를 생성
      List<PhotoVO> photo = pDao.selectPhoto();//등록된사진을 리스트타입의 변수에 저장
      req.setAttribute("photo", photo);//변수에 담긴 리스트를 setAttribute저장
      return url;//변수에 저장된 url로 이동
   }

   private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
      String url = "../view/diary.jsp";   //이동할 url을 변수에 저장  
      int bno = Integer.parseInt(req.getParameter("bno"));//파라미터로 받은 사진번호를 Integer형으로 형변환후 변수에 저장
      String userid = req.getParameter("userid");//파라미터로 받은 유저아이디를 변수에 저장
      String content = req.getParameter("content");//파라미터로 받은 사진내용을 변수에 저장
            
      ReplyVO rVo = new ReplyVO();//댓글 vo객체 생성
      rVo.setBno(bno);//vo에 사진번호를 저장
      rVo.setUserid(userid);//vo에 유저아이디를 저장
      rVo.setContent(content);//vo에 사진내용을 저장
      
      ReplyDAO rDao = ReplyDAO.getInstance();//DAO객체를 생성
      rDao.insertReply(rVo);   //사진을 등록
      res.sendRedirect("../diary/diary.do");//다이어리 페이지로 redirect
      
      return null;
   
   }
}

