package com.kh.semi.board.model.dao;

import static com.kh.semi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.board.model.vo.QuestionBoard;

public class NoticeBoardDao {

	private Properties prop = new Properties();
	
	public NoticeBoardDao() {
		String filepath = NoticeBoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<NoticeBoard> selectAllNoticeBoard(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllNoticeBoard");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				
				noticeBoard.setNoticeNo(rset.getInt("notice_no"));
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setNoticeContent(rset.getString("notice_content"));
				noticeBoard.setRegDate(rset.getDate("reg_date"));
				noticeBoard.setReadCount(rset.getInt("read_count"));
				
				System.out.println("noticeBoard = " + noticeBoard);
				list.add(noticeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int selectTotalNoticeContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalNoticeContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("cnt");   // ()안에는 컬럼명 또는 컬럼의 순서
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}
	
	public NoticeBoard selectOneBoard(Connection conn, int no) {
		NoticeBoard noticeBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneNoticeBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				noticeBoard = new NoticeBoard();
				
				noticeBoard.setNoticeNo(rset.getInt("notice_no"));
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setNoticeContent(rset.getString("notice_content"));
				noticeBoard.setRegDate(rset.getDate("reg_date"));
				noticeBoard.setReadCount(rset.getInt("read_count"));
		
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return noticeBoard;
	}

	public int insertNoticeBoard(Connection conn, NoticeBoard noticeBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNoticeBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeBoard.getNoticeTitle());
			pstmt.setString(2, noticeBoard.getNoticeContent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BoardException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastNoticeBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastNoticeBoardNo");
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BoardException("게시물번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}
	
	public int deleteNoticeBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteNoticeBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}
	
	public int updateNoticeBoard(Connection conn, NoticeBoard noticeBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateNoticeBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, noticeBoard.getNoticeTitle());
			pstmt.setString(2, noticeBoard.getNoticeContent());
			pstmt.setInt(3, noticeBoard.getNoticeNo());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public List<NoticeBoard> ajaxMainNoticeBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<NoticeBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectHomeNoticeBoardList");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				NoticeBoard noticeBoard = new NoticeBoard();
				
				noticeBoard.setNoticeTitle(rset.getString("notice_title"));
				noticeBoard.setRegDate(rset.getDate("reg_date"));

				System.out.println("noticeBoard = " + noticeBoard);
				
				list.add(noticeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int updateNoticeBoardReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNoticeBoardReadCount");
		System.out.println(sql);
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new BoardException("조회수1증가 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}

