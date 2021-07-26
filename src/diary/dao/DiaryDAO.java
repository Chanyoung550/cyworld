package diary.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import diary.dto.DiaryVO;
import util.DBManager;

public class DiaryDAO {
	private DiaryDAO() { }
	
	private static DiaryDAO instance = new DiaryDAO();
	
	public static DiaryDAO getInstance() {
		return instance;
	}
	
	public List<DiaryVO> selectDiary(DiaryVO dvo) {
		
		String sql = "select * from diary where date(updatedate)= ? order by bno desc";//diary에서 날짜순으로 desc해서 변수에 저장
		List<DiaryVO> list = new ArrayList<DiaryVO>();//diaryVO 리스트 타입의 객체를 생성
		try(Connection conn = DBManager.getConnection();//데이터베이스와 통신
				PreparedStatement pstmt = conn.prepareStatement(sql);) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, dvo.getUpdatedate());//1번째로 받는 데이터는 날짜.
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//다음이 없을때까지 진행
				DiaryVO dVo = new DiaryVO();//diartVO 객체 생성
				dVo.setBno(rs.getInt("bno"));//객체 안에 받아온 다이어리번호를 넣어줌.
				dVo.setTitle(rs.getNString("title"));//객체 안에  받아온 제목을 넣어줌.
				dVo.setContent(rs.getNString("content"));//객체 안에  받아온 내용을 넣어줌.
				dVo.setUpdatedate(rs.getString("updatedate"));//객체 안에  받아온 받아온 작성날짜를 넣어줌
				list.add(dVo);//vo에 담아놓은 데이터를 리스트객체에 담아줌.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public void insertDiary(DiaryVO	dvo) {
		String sql = "insert into diary(title, content) values(?,?)";//작성한 다이어리 데이터를 데이터베이스에 넣어주는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql);) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, dvo.getTitle());//1번째로 받는 데이터는 다이어리 제목
			pstmt.setString(2, dvo.getContent());//2번째로 받는 데이터는 다이어리 내용
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DiaryVO selectOneBoardByBno(int bno) {
		String sql = "select * from diary where bno = ?";//보낸 프라이머리 키와 같은 다이어리를 가져오는 쿼리문
		DiaryVO dVo = null;
		ResultSet rs = null;
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setInt(1, bno);;//1번째로 받는 데이터는 다이어리 번호
			rs = pstmt.executeQuery();
			if (rs.next()) {//결과가 없을때까지 계속 진행
				dVo = new DiaryVO();//diaryVO 객체 생성
				dVo.setBno(rs.getInt("bno"));//vo객체안에 가져온 다이어리 번호를 저장
				dVo.setTitle(rs.getString("title"));//vo객체안에 가져온 다이어리 제목을 저장
				dVo.setContent(rs.getString("content"));//vo객체안에 가져온  다이어리 내용을 저장
				dVo.setUpdatedate(rs.getString("updatedate"));//vo객체안에 가져온 다이어리 작성날짜를 저장
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);//데이터베이스와 통신 종료
		}
		return dVo;
	}

	public void updateDiary(DiaryVO dVo) {
		String sql = "update diary set title=?, content=?, updatedate=? where bno=?";//다이어리를 수정하는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setString(1, dVo.getTitle());//1번째로 받는 데이터는 다이어리 제목
			pstmt.setString(2, dVo.getContent());//2번째로 받는 데이터는 다이어리 내용
			pstmt.setString(3, dVo.getUpdatedate());//3번째로 받는 데이터는 다이어리 작성날짜 
			pstmt.setInt(4, dVo.getBno());//4번째로 받는 데이터는 다이어리 번호
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteDiary(int bno) {
		String sql = "delete from diary where bno=?";//가져온 다이어리번호와 같은 다이어리 row를 지우는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setInt(1, bno);//1번째로  받는 데이터는 다이어리번호
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public List<DiaryVO> getSeletLeast() {
		String sql = "select * from diary order by bno desc limit 10;";//다이어리 작성순서대로 10개만 가져오는 쿼리문
		List<DiaryVO> list = new ArrayList<DiaryVO>();//리스트타입의 객체 생성
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			while(rs.next()) {//다음이 없을때까지 진행
				DiaryVO dVo = new DiaryVO();//diaryVO객체 생성
				dVo.setBno(rs.getInt("bno"));//생성한 객체 안에 가져온 다이어리 번호를 담아줌
				dVo.setTitle(rs.getString("title"));//생성한 객체 안에 가져온 다이어리 제목을 담아줌
				dVo.setContent(rs.getString("content"));//생성한 객체 안에 가져온 다이어리 내용을 담아줌
				dVo.setUpdatedate(rs.getString("updatedate"));//생성한 객체 안에 가져온 다이어리 날짜를 담아줌
				list.add(dVo);//리스트타입의 객체안에 vo의 데이터를 넣어줌
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
}