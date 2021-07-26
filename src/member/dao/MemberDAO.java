package member.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import encript.BCrypt;
import member.dto.MemberVO;
import util.DBManager;

public class MemberDAO {
	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/jspdb");
		conn = ds.getConnection();
		return conn;
	}

	// 사용자 인증시 사용하는 메소드
	public int userCheck(String userid, String pwd) {
		int result = -1;
		String sql = "select pwd from member where userid=?";//가져간 유저아이디와 같은 패스워드를 가져옴
		ResultSet rs = null;
		try (Connection conn = getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ){//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, userid);//1번째로 받는 데이터는 유저아이디.
			rs = pstmt.executeQuery();
			if (rs.next()) {//다음이 없을때까지 진행
				if (rs.getString("pwd") != null
						&& BCrypt.checkpw(pwd, rs.getString("pwd"))) {//데이터베이스에서 가져온 패스워드가 있고, 가져온 패스워드와 입력된패스워드가 같으면 실행
					result = 1;//result값에 1을 넣음.
				} else {//데이터베이스에서 가져온 패스워드가 있고, 가져온 패스워드와 입력된패스워드가 다르면 실행
					result = 0;//result값에 0을 넣음.
				}
			} else {//데이터베이스에서 가져온 패스워드가 없고, 가져온 패스워드와 입력된패스워드가 다르면 실행
				result = -1;//result값에 -1을 넣음.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();//작업이 끝나면 데이터베이스를 닫음
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 아이디로 회원 정보 가져오는 메소드
	public MemberVO getMember(String userid) {
		MemberVO mVo = null;
		String sql = "select * from member where userid=?";//유저아이디와 같은 데이터를 가져옴
		ResultSet rs = null;
		try (Connection conn = getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql);) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, userid);//1번째로 받는 데이터는 유저아이디.
			rs = pstmt.executeQuery();
			if (rs.next()) {//다음이 없을때까지 진행
				mVo = new MemberVO();//memberVO 객체 생성
				mVo.setName(rs.getString("name"));//생성한 객체 안에 가져온 유저이름을 넣어줌
				mVo.setUserid(rs.getString("userid"));//생성한 객체 안에 가져온 아이디를 넣어줌
				mVo.setPwd(rs.getString("pwd"));//생성한 객체 안에 가져온 비밀번호를 넣어줌
				mVo.setEmail(rs.getString("email"));//생성한 객체 안에 가져온 이메일을 넣어줌
				mVo.setPhone(rs.getString("phone"));//생성한 객체 안에 가져온 핸드폰번호를 넣어줌
				mVo.setGrade(rs.getInt("grade"));//생성한 객체 안에 가져온 유저등급을 넣어줌
				mVo.setPhotoUrl(rs.getNString("photourl"));//생성한 객체 안에 가져온 사진url을 넣어줌
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();//작업이 끝나면 데이터베이스를 닫음
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mVo;
	}

	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid=?";//입력된 유저아이디와 중복된 유저아이디가 있는지 확인
		ResultSet rs = null;
		try (Connection conn = getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, userid);//1번째로 받는 데이터는 유저아이디.
			rs = pstmt.executeQuery();
			if (rs.next())//다음이 있으면 실행
				result = 1;//result값에 1을 넣음.
			else
				result = -1;//result값에 -1을 넣음.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();//작업이 끝나면 데이터베이스를 닫음
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertMember(MemberVO mVo) {
		int result = -1;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?)";//회원가입한 정보를 데이터베이스에 넣는 쿼리문
		try (Connection conn = getConnection();//데이터베이스와 통신
				PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, mVo.getName());//1번째로 받는 데이터는 유저이름
			pstmt.setString(2, mVo.getUserid());//2번째로 받는 데이터는 유저아이디
			pstmt.setString(3, BCrypt.hashpw(mVo.getPwd(), BCrypt.gensalt(10)));//3번째로 받는 데이터는 패스워드
			pstmt.setString(4, mVo.getEmail());//4번째로 받는 데이터는 이메일
			pstmt.setString(5, mVo.getPhone());//5번째로 받는 데이터는 폰번호
			pstmt.setInt(6, mVo.getGrade());//6번째로 받는 데이터는 유저등급
			pstmt.setNString(7, mVo.getPhotoUrl());//7번째로 받는 데이터는 사진url
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?,"
				+ "phone=?, grade=? where userid=?";//유저의 정보를 바꾸는 쿼리문
		try (Connection conn = getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, BCrypt.hashpw(mVo.getPwd(), BCrypt.gensalt(10)));//1번째로 받는 데이터는 패스워드
			pstmt.setString(2, mVo.getEmail());//2번째로 받는 데이터는 이메일
			pstmt.setString(3, mVo.getPhone());//3번째로 받는 데이터는 폰번호
			pstmt.setInt(4, mVo.getGrade());//4번째로 받는 데이터는 유저등급
			pstmt.setString(5, mVo.getUserid());//5번째로 받는 데이터는 유저아이디
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
	}

	public List<MemberVO> selectVisitMembers() {
		String sql = "select userid, photourl from member";//member테이블에서 유저아이디와 사진url을 가지고오는 쿼리문
		List<MemberVO> list = new ArrayList<MemberVO>();//memberVO 리스트 타입의 객체생성
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);	) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			if (rs.next()) {//다음이 있으면 실행
				MemberVO mVo = new MemberVO();//memberVO객체 생성
				mVo.setUserid(rs.getString("userid"));//생성된 객체안에 유저아이디를 가져옴
				mVo.setPhotoUrl(rs.getString("photourl"));//생성된 객체안에 사진url을 가져옴
				list.add(mVo);//리스트에 객체안에 데이터를 담아줌
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}