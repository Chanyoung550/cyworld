package visit.dao;

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

import diary.dto.DiaryVO;
import encript.BCrypt;
import member.dao.MemberDAO;
import member.dto.MemberVO;
import photo.dto.PhotoVO;
import util.DBManager;
import visit.dto.VisitVO;

public class VisitDAO {
	private VisitDAO() {
	}

	private static VisitDAO instance = new VisitDAO();

	public static VisitDAO getInstance() {
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
	public List<VisitVO> selectAllVisit(){
		String sql = "select* from visit order by bno desc";
		List<VisitVO> list = new ArrayList<VisitVO>();
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);){//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
				while (rs.next()) {//다음이 있으면 실행
					VisitVO vVo = new VisitVO();//visitVO 객체를 생성
					vVo.setBno(rs.getInt("bno"));//생성된 객체안에 가져온 방명록번호를 넣어줌
					vVo.setUserid(rs.getString("userid"));//생성된 객체안에 가져온 방명록을 작성한 유저아이디를 넣어줌
					vVo.setContent(rs.getString("content"));//생성된 객체안에 가져온 방명록에 작성한 내용을 넣어줌
					vVo.setUpdatedate(rs.getString("updatedate"));//생성된 객체안에 가져온 방명록을 작성한 날짜를 넣어줌
					vVo.setPhotoUrl(rs.getNString("photourl"));//생성된 객체안에 가져온 유저의 사진url을 넣어줌
					list.add(vVo);//vo객체안에 저장된 데이터를 list객체안에 넣어줌.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	}
	public void insertVisit(VisitVO vVo) {
		String sql = "insert into visit(content,userid, photourl) values(?,?,?)";//방명록을 등록하는 쿼리문
		try (Connection conn = getConnection();//데이터베이스와 통신
				PreparedStatement pstmt = conn.prepareStatement(sql); ){//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, vVo.getContent());//1번째로 받는 데이터는 방명록에 작성한 내용.
			pstmt.setString(2, vVo.getUserid());//1번째로 받는 데이터는 방명록을 작성한 유저아이디
			pstmt.setString(3, vVo.getPhotoUrl());//1번째로 받는 데이터는 방명록을 작성한 유저사진url
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteVisit(Integer bno) {
		String sql = "delete from visit where bno = ?";//방명록을 삭제하는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
				PreparedStatement pstmt = conn.prepareStatement(sql); ){//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setInt(1, bno);//1번째로 받는 데이터는 방명록번호.
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public VisitVO selectvisitBno(Integer bno) {
		String sql = "select* from visit where bno=?";//방명록번호와 같은 방명록 데이터를 가져오는 쿼리문
		VisitVO vVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
				PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
				pstmt.setInt(1, bno);//1번째로 받는 데이터는 방명록번호.
				rs = pstmt.executeQuery();
				if (rs.next()) {//다음이 있으면 실행
					vVo = new VisitVO();//visitVO객체를 생성
					vVo.setBno(rs.getInt("bno"));//생성된 객체안에 가져온 방명록 번호를 넣어줌
					vVo.setUserid(rs.getString("userid"));//생성된 객체안에 가져온 방명록을 작성한 유저아이디를 넣어줌
					vVo.setContent(rs.getNString("content"));//생성된 객체안에 가져온 방명록에 작성된 내용을 넣어줌
					vVo.setUpdatedate(rs.getString("updatedate"));//생성된 객체안에 가져온 방명록을 작성한 날짜를 넣어줌
					vVo.setPhotoUrl(rs.getNString("photourl"));//생성된 객체안에 가져온 방명록을 작성한 유저의 사진을 넣어줌
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(rs);//데이터베이스를 닫음
		}
		return vVo;
	}
	public void updatevisit(VisitVO vVo) {
		String sql = "update visit set content=? where bno=?";//방명록을 수정하는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, vVo.getContent());//1번째로 받는 데이터는 방명록내용.
			pstmt.setInt(2, vVo.getBno());//2번째로 받는 데이터는 방명록번호.
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
