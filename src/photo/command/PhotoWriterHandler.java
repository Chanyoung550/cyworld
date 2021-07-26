package photo.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import photo.dao.PhotoDAO;
import photo.dto.PhotoVO;

public class PhotoWriterHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 if(req.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
	         return "../view/photoWrite.jsp";
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
		req.setCharacterEncoding("UTF-8");
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("upload");//경로 설정
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;//사진크기 제한
		MultipartRequest multi = new MultipartRequest(req, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());//사진을 저장하기 위해 사용하는 객체
		
		String title = multi.getParameter("title");//멀티파트에서 사진 제목을 변수에 저장
		String content = multi.getParameter("content");//멀티파트에서 사진 내용을 변수에 저장
		String photoUrl = multi.getFilesystemName("photoUrl");//멀티파트에서 사진 url을 변수에 저장
		PhotoVO pVo = new PhotoVO();//사진 vo객체를 생성
		pVo.setTitle(title);//vo에 사진제목을 저장
		pVo.setContent(content);//vo에 사진내용을 저장
		pVo.setPhotoUrl(photoUrl);//vo에 사진url을 저장
		PhotoDAO pDao = PhotoDAO.getInstance();//DAO객체를 생성
		pDao.insertPhoto(pVo);//사진을 등록
		res.sendRedirect("../photo/photo.do");//사진첩 페이지로 redirect
		return null;
	}
}
