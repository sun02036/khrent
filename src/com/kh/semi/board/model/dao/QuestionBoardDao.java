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
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.board.model.vo.QuestionBoardComment;

public class QuestionBoardDao {
	
	private Properties prop = new Properties();
	
	public QuestionBoardDao() {
		String filepath = QuestionBoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<QuestionBoard> selectAllQuestionBoard(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QuestionBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllQuestionBoard");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QuestionBoard qnaBoard = new QuestionBoard();
				
				qnaBoard.setNo(rset.getInt("qna_no"));
				qnaBoard.setQna_title(rset.getString("qna_title"));
				qnaBoard.setWriter(rset.getString("qna_writer"));
				qnaBoard.setQna_content(rset.getString("qna_content"));
				qnaBoard.setAnswer_status(rset.getString("answer_status"));
				qnaBoard.setRegDate(rset.getDate("reg_date"));
				qnaBoard.setReadCount(rset.getInt("read_count"));
				
				qnaBoard.setBoardCommentCount(rset.getInt("bc_count"));
				System.out.println("qnaBoard = " + qnaBoard);
				
				if(rset.getInt("attach_no") != 0) {
					Attachment attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("attach_no"));
					attach.setOriginalFilename(rset.getString("original_filename"));
					attach.setRenamedFilename(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("attach_reg_date"));
					
					qnaBoard.setAttach(attach);
				}
				
				list.add(qnaBoard);
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

	public int selectTotalQuestionContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalQuestionContents");
		
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
	public QuestionBoard selectOneQnaBoard(Connection conn, int no) {
		QuestionBoard questionBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneQuestionBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				questionBoard = new QuestionBoard();
				
				questionBoard.setNo(rset.getInt("qna_no"));
				questionBoard.setWriter(rset.getString("qna_writer"));
				questionBoard.setQna_title(rset.getString("qna_title"));
				questionBoard.setQna_content(rset.getString("qna_content"));
				questionBoard.setRegDate(rset.getDate("reg_date"));
				questionBoard.setAnswer_status(rset.getString("answer_status"));
				questionBoard.setReadCount(rset.getInt("read_count"));
				if(rset.getInt("attach_no") != 0) {
					Attachment attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("attach_no"));
					attach.setOriginalFilename(rset.getString("original_filename"));
					attach.setRenamedFilename(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("attach_reg_date"));
					
					questionBoard.setAttach(attach);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return questionBoard;
	}
	public int insertQnaBoard(Connection conn, QuestionBoard questionBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQnaBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionBoard.getWriter());
			pstmt.setString(2, questionBoard.getQna_title());
			pstmt.setString(3, questionBoard.getQna_content());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BoardException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastQnaBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastQnaBoardNo");
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

	public int insertQnaAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQnaAttachment");
		int result = 0;
		System.out.println(attach);
		
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

	public Attachment selectOneQnaAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("selectOneQnaAttachment");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("qna_no"));
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

	public int updateQnaReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateQnaReadCount");
		
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

	public List<QuestionBoardComment> selectQnaCommentList(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QuestionBoardComment> commentList = new ArrayList<>();
		String sql = prop.getProperty("selectQnaCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QuestionBoardComment boardComment = new QuestionBoardComment();
				
				boardComment.setNo(rset.getInt("no"));
				boardComment.setBoardNo(rset.getInt("qna_no"));
				boardComment.setWriter(rset.getString("writer"));
				boardComment.setContent(rset.getString("content"));
				boardComment.setRegDate(rset.getDate("reg_date"));
				boardComment.setCommentLevel(rset.getInt("comment_level"));
				boardComment.setCommentRef(rset.getInt("comment_ref"));
				
				commentList.add(boardComment);
			}
			
		} catch (SQLException e) {
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	public int insertQnaBoardComment(Connection conn, QuestionBoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertQnaBoardComment");
		System.out.println("QuestionBoardDao@sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getBoardNo());
			pstmt.setString(2, bc.getWriter());
			pstmt.setString(3, bc.getContent());
			pstmt.setInt(4, bc.getCommentLevel());
			pstmt.setObject(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public List<QuestionBoard> ajaxMainQuestionBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QuestionBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectHomeQnaBoardList");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QuestionBoard qnaBoard = new QuestionBoard();
				
				qnaBoard.setQna_title(rset.getString("qna_title"));
				qnaBoard.setRegDate(rset.getDate("reg_date"));
				
				System.out.println("qnaBoard = " + qnaBoard);
				
				list.add(qnaBoard);
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

	public int deleteQnaAttachment(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteQnaAttachment");
		
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

	public int updateQnaBoard(Connection conn, QuestionBoard questionBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateQnaBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionBoard.getQna_title());
			pstmt.setString(2, questionBoard.getQna_content());
			pstmt.setInt(3, questionBoard.getNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteQnaBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteQnaBoardComment");
		
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

	public int deleteQnaBoard(Connection conn, int no) {
		// board의 행이 삭제되면 그 행의 no를 참조하는 attachment의 행도 따라서 삭제된다. on delete cascade 이기 때문에
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteQnaBoard");
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

	public int updateQnaAnswerStatus(Connection conn, String string, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateQnaAnswerStatus");
		System.out.println(sql);
		System.out.println(string + "," + no);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setString(1, string);
			pstmt.setInt(2, no);
			
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

}
	
