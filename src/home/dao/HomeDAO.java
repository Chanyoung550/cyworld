package home.dao;

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
import reply.dto.ReplyVO;
import util.DBManager;
import visit.dto.VisitVO;

public class HomeDAO {
	private HomeDAO() {
	}

	private static HomeDAO instance = new HomeDAO();

	public static HomeDAO getInstance() {
		return instance;
	}
	

	public int diarycount() {
		String sql = "select count(*) count from diary;";//전체 다이어리를 카운트하는  쿼리문
		int count = 0;
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			if(rs.next()) {//다음이 없을때까지 진행
				count = rs.getInt("count");//쿼리문에서 얻어온 카운트수를 변수에 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	public int visitcount() {
		String sql = "select count(*) count from visit;";//전체 방명록을 카운트하는 쿼리문
		
		int count = 0;
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			if(rs.next()) {//다음이 없을때까지 진행
				count = rs.getInt("count");//쿼리문에서 얻어온 카운트수를 변수에 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	public int photocount() {
		String sql = "select count(*) count from photo;";//전체 사진첩을 카운트하는 쿼리문
		
		int count = 0;
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			if(rs.next()) {//다음이 없을때까지 진행
				count = rs.getInt("count");//쿼리문에서 얻어온 카운트수를 변수에 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	public DiaryVO selectdiaryone() {
		
		String sql =  "select content from diary order by updatedate desc limit 1;";//다이어리의 가장 최근에 작성된것을 가져오는 쿼리문
		DiaryVO dVo = null;
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			while (rs.next()) {
				dVo = new DiaryVO();//diaryVO객체 생성
				dVo.setContent(rs.getString("content"));//생성한 객체에 다이어리 내용을 담아줌
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dVo;
	}
	public PhotoVO selectphotoone() {
		
		String sql =  "select content from photo order by updatedate desc limit 1;";//사진첩의 가장 최근에 작성된것을 가져오는 쿼리문
		PhotoVO dVo = null;
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			while (rs.next()) {
				dVo = new PhotoVO();//photoVO객체를 생성
				dVo.setContent(rs.getString("content"));//생성한 객체에 사진첩 내용을 담아줌
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dVo;
	}
	public VisitVO selectvisitone() {
	
	String sql =  "select content from visit order by updatedate desc limit 1;";//방명록의 가장 최근에 작성된것을 가져오는 쿼리문
	VisitVO dVo = null;
	try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
		while (rs.next()) {
			dVo = new VisitVO();//visitVO객체를 생성
			dVo.setContent(rs.getString("content"));//생성한 객체에 사진첩 내용을 담아줌
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	
	}
	return dVo;
}
}
