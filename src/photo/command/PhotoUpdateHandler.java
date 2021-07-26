package photo.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import photo.dao.PhotoDAO;
import photo.dto.PhotoVO;

public class PhotoUpdateHandler implements CommandHandler {

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
		String url = "../view/photoUpdate.jsp";//이동할 url을 변수에 저장
		Integer bno = Integer.parseInt(req.getParameter("bno"));//파라미터로 받은 사진번호를 integer형으로 형변환 하여 변수에 저장
		PhotoDAO pDao = PhotoDAO.getInstance();//DAO객체를 생성
		PhotoVO pVo = pDao.selectProductBno(bno);//사진번호에 대한 정보를 vo에 담음
		req.setAttribute("photo", pVo);//변수에 담긴 리스트를 setAttribute저장
		return url;//변수에 저장된 url로 이동
	}
	private String processsubmit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("upload");//경로 설정
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;//사진크기 제한
		MultipartRequest multi = new MultipartRequest(req, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());//사진을 저장하기 위해 사용하는 객체
		int bno = Integer.parseInt(multi.getParameter("bno"));//멀티파트에서 사진번호를 integer형으로 형변환하여 변수에 저장
		String title = multi.getParameter("title");//멀티파트에서 사진제목을 변수에저장
		String content = multi.getParameter("description");//멀티파트에서 사진내용을 변수에 저장
		String photoUrl = multi.getFilesystemName("photoUrl");//멀티파트에서 사진 url을 변수에 저장
		
		if(photoUrl == null) {//사진이 없으면 실행
			photoUrl = multi.getParameter("photoUrl");
		}
		PhotoVO pVo = new PhotoVO();//사진 vo객체 생성
		pVo.setBno(bno);//vo에 사진번호를 저장
		pVo.setTitle(title);//vo에 사진제목을 저장
		pVo.setContent(content);//vo에 사진내용을 저장
		pVo.setPhotoUrl(photoUrl);//vo에 사진 url을 저장
		PhotoDAO pDao = PhotoDAO.getInstance();//DAO객체를 생성
		pDao.updatePhoto(pVo);//사진을 수정.
		res.sendRedirect("../photo/photo.do");//사진첩 페이지로 redirect
		return null;
	}

}
