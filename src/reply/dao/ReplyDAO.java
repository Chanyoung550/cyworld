package reply.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import reply.dto.ReplyVO;
import util.DBManager;

public class ReplyDAO {
	private ReplyDAO() { }
	
	private static ReplyDAO instance = new ReplyDAO();
	
	public static ReplyDAO getInstance() {
		return instance;
	}

	public List<ReplyVO> selectAllReply(int bno) {
		
		String sql =  "select * from reply where bno=?";//다이어리 번호와 같은 댓글을 가져오는 쿼리문
		ResultSet rs = null;
		List<ReplyVO> list = new ArrayList<ReplyVO>();//replyVO 리스트타입의 객체를 생성
		 try(Connection conn = DBManager.getConnection();//데이터베이스와 통신
		           PreparedStatement pstmt = conn.prepareStatement(sql);){//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
	           pstmt.setInt(1, bno);//1번째로 받는 데이터는 댓글번호
	           rs = pstmt.executeQuery();	
			while (rs.next()) {//다음이 있으면 실행
				ReplyVO rVo = new ReplyVO();//replyVO타입의 객체 생성
				rVo.setRno(rs.getInt("rno"));//생성된 객체안에 가져온 댓글번호를 넣어줌
				rVo.setBno(rs.getInt("bno"));//생성된 객체안에 가져온 다이어리번호를 넣어줌
				rVo.setUserid(rs.getString("id"));//생성된 객체안에 가져온 작성한 유저아이디를 넣어줌
				rVo.setContent(rs.getString("content"));//생성된 객체안에 가져온 댓글을 넣어줌
				rVo.setUpdatedate(rs.getString("date"));//생성된 객체안에 가져온 댓글을 작성한 날짜를 넣어줌
				
				list.add(rVo);//rvo객체안에 담긴 데이터를 list객체안에 담아줌
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			DBManager.close(rs);//데이터베이스를 닫음
		}
		return list;
	}

	public void insertReply(ReplyVO rVo) {
		String sql = "insert into reply(bno, id, content) values(?,?,?)";//댓글을 등록하는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql);) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setInt(1, rVo.getBno());//1번째로 받는 데이터는 다이어리번호
			pstmt.setString(2, rVo.getUserid());//2번째로 받는 데이터는 작성한 유저아이디
			pstmt.setString(3, rVo.getContent());//3번째로 받는 데이터는 작성한 댓글
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ReplyVO> selectAllReplyy() {
		
		String sql =  "select * from reply order by rno desc";//전체 댓글을 등록된 순서대로 가져오는 쿼리문
		List<ReplyVO> list = new ArrayList<ReplyVO>();//replyVO 리스트타입의 객체를 생성
		try (Connection conn = DBManager.getConnection();){//데이터베이스와 통신
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			while (rs.next()) {//다음이 있으면 실행
				ReplyVO rVo = new ReplyVO();//replyVO타입의 객체를 생성
				rVo.setRno(rs.getInt("rno"));//생성한 객체 안에 가져온 댓글번호를 넣어줌.
				rVo.setBno(rs.getInt("bno"));//생성한 객체 안에 가져온 다이어리번호를 넣어줌.
				rVo.setUserid(rs.getString("id"));//생성한 객체 안에 가져온 작성한 유저아이디를 넣어줌.
				rVo.setContent(rs.getString("content"));//생성한 객체 안에 가져온 작성한 댓글을 넣어줌.
				rVo.setUpdatedate(rs.getString("date"));//생성한 객체 안에 가져온 작성한 날짜를 넣어줌.
				
				list.add(rVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return list;
	}
	public void deletereply(int rno) {
		String sql = "delete from reply where rno=?";//댓글을 삭제하는 쿼리문
		try (Connection conn = DBManager.getConnection();//데이터베이스와 통신
			PreparedStatement pstmt = conn.prepareStatement(sql); ) {//변수에 담아놓은 쿼리문을 데이터베이스에 넣어줌.
			pstmt.setInt(1, rno);//1번째로 받는 데이터는 다이어리번호
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	
}