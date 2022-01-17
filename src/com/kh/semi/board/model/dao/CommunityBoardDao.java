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
import com.kh.semi.board.model.vo.CommunityBoard;
import com.kh.semi.board.model.vo.CommunityBoardComment;

public class CommunityBoardDao {
	
	private Properties prop = new Properties();
	
	public CommunityBoardDao() {
		String filepath = CommunityBoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<CommunityBoard> selectAllCommunityBoard(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CommunityBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllCommunityBoard");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CommunityBoard board = new CommunityBoard();
				
				board.setNo(rset.getInt("community_no"));
				board.setTitle(rset.getString("community_title"));
				board.setWriter(rset.getString("community_writer"));
				board.setContent(rset.getString("community_content"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setReadCount(rset.getInt("read_count"));
				
				board.setBoardCommentCount(rset.getInt("bc_count"));
				System.out.println("board = " + board);
				
				if(rset.getInt("attach_no") != 0) {
					Attachment attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("attach_no"));
					attach.setOriginalFilename(rset.getString("original_filename"));
					attach.setRenamedFilename(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("attach_reg_date"));
					
					board.setAttach(attach);
				}
				
				list.add(board);
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

	
	public int selectTotalCommunityContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalCommunityContents");
		
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

	public CommunityBoard selectOneBoard(Connection conn, int no) {
		CommunityBoard communityBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneCommunityBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				communityBoard = new CommunityBoard();
				
				communityBoard.setNo(rset.getInt("community_no"));
				communityBoard.setWriter(rset.getString("community_writer"));
				communityBoard.setTitle(rset.getString("community_title"));
				communityBoard.setContent(rset.getString("community_content"));
				communityBoard.setRegDate(rset.getDate("reg_date"));
				communityBoard.setReadCount(rset.getInt("read_count"));
			}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return communityBoard;
	}

	public int insertCommunityComment(Connection conn, CommunityBoardComment communityBoardComment) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCommunityComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,communityBoardComment.getBoardNo());
			pstmt.setString(2, communityBoardComment.getWriter());
			pstmt.setString(3, communityBoardComment.getContent());
			pstmt.setInt(4,communityBoardComment.getCommentLevel());
			pstmt.setObject(5, communityBoardComment.getCommentRef() == 0 ? null : communityBoardComment.getCommentRef());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public List<CommunityBoardComment> selectCommentList(Connection conn, int no) {
		List<CommunityBoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				CommunityBoardComment cb = new CommunityBoardComment();
				cb.setNo(rset.getInt("no"));
				cb.setCommentLevel(rset.getInt("comment_level"));
				cb.setWriter(rset.getString("writer"));
				cb.setContent(rset.getString("content"));
				cb.setBoardNo(rset.getInt("community_no"));
				cb.setCommentRef(rset.getInt("comment_ref"));
				cb.setRegDate(rset.getDate("reg_date"));
				list.add(cb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateCommunityReadCount");
		System.out.println("query@dao = " + sql);
		int result = 0;
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertCommunityBoard(Connection conn, CommunityBoard communityBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCommunityBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communityBoard.getTitle());
			pstmt.setString(2, communityBoard.getWriter());
			pstmt.setString(3, communityBoard.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public int selectLastCommunityBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastCommunityBoardNo");
		System.out.println(sql);
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int insertCommunityAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCommunityAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new BoardException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Attachment selectOneAttachmentByCommunityBoardNo(Connection conn, int no) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneAttachmentByCommunityBoardNo");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("community_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public Attachment selectOneCommunityAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("selectOneCommunityAttachment");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("community_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		
		return attach;
	}

	public int deleteCommunityBoard(Connection conn, int no) {
		// board의 행이 삭제되면 그 행의 no를 참조하는 attachment의 행도 따라서 삭제된다. on delete cascade 이기 때문에
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCommunityBoard");
		System.out.println(sql);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
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

	public int deleteCommunityAttachment(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCommunityAttachment");
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setInt(1, attachNo);
			
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

	public int updateCommunityBoard(Connection conn, CommunityBoard communityBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateCommunityBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, communityBoard.getTitle());
			pstmt.setString(2, communityBoard.getContent());
			pstmt.setInt(3, communityBoard.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteCommunityBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteCommunityBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	

}






