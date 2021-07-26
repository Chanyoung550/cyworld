package member.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.command.CommandHandler;
import member.dao.MemberDAO;
import member.dto.MemberVO;

public class JoinHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(request.getMethod().equalsIgnoreCase("GET")) {//get방식으로 오면 실행
			return "/member/joinForm.jsp";
		}
		else if(request.getMethod().equalsIgnoreCase("POST")) {//post방식으로 오면 실행
			return processSubmit(request, response);
		}
		else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String url = "/member/loginForm.jsp";//이동해야할 url을 변수에 저장
		request.setCharacterEncoding("UTF-8");
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("upload");//경로 설정
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;//사진크기 제한
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());//사진을 저장하기 위해 사용하는 객체
		
		String name = multi.getParameter("name");//멀티파트에서 유저 이름을 가져와서 변수에 저장
		String userid = multi.getParameter("userid");//멀티파트에서 유저 아이디을 가져와서 변수에 저장
		String pwd = multi.getParameter("pwd");//멀티파트에서 유저 비밀번호을 가져와서 변수에 저장
		String email = multi.getParameter("email");//멀티파트에서 유저 이메일을 가져와서 변수에 저장
		String phone = multi.getParameter("phone");//멀티파트에서 유저 핸드폰번호를 가져와서 변수에 저장
		String photoUrl = multi.getFilesystemName("photoUrl");//멀티파트에서 유저 사진url을 가져와서 변수에 저장
		String grade = multi.getParameter("grade");//멀티파트에서 유저 등급을 가져와서 변수에 저장
		MemberVO mVo = new MemberVO();//멤버vo의 객체를 생성
		mVo.setName(name);//생성된 객체에 유저이름을 저장
		mVo.setUserid(userid);//생성된 객체에 유저아이디를 저장
		mVo.setPwd(pwd);//생성된 객체에 비밀번호를 저장
		mVo.setEmail(email);//생성된 객체에 이메일을 저장
		mVo.setPhone(phone);//생성된 객체에 폰번호를 저장
		mVo.setGrade(Integer.parseInt(grade));//생성된 객체에유저 등급을 integer형으로 변환하여 저장
		mVo.setPhotoUrl(photoUrl);//생성된 객체에 사진url을 저장
		MemberDAO mDao = MemberDAO.getInstance();//DAO객체를 생성
		int result = mDao.insertMember(mVo);//int타입의 변수에 insertMember에서 가져온 데이터를 저장
		HttpSession session = request.getSession();//세션객체 생성
		if (result == 1) {//result값이 1이면 실행
			session.setAttribute("userid", mVo.getUserid());//변수에 담긴 리스트를 setAttribute저장
			request.setAttribute("message", "회원 가입에 성공했습니다.");//변수에 담긴 리스트를 setAttribute저장
		} else {//result값이 1이아니면 실행
			request.setAttribute("message", "회원 가입에 실패했습니다.");//변수에 담긴 리스트를 setAttribute저장
			url = "/member/joinForm.jsp";//이동해야할 페이지를 변수에 저장
		}
		return url;//변수에 저장된 url로 이동
	}
}