package com.kh.semi.board.model.service;

import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.board.model.dao.QuestionBoardDao;
import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.QuestionBoard;
import com.kh.semi.board.model.vo.QuestionBoardComment;

public class QuestionBoardService {

	private QuestionBoardDao questionBoardDao = new QuestionBoardDao();
	
	public List<QuestionBoard> selectAllQeustionBoard(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<QuestionBoard> list = questionBoardDao.selectAllQuestionBoard(conn, startRownum, endRownum);
		close(conn);
		
		return list;
	}

	public int selectTotalQuestionContents() {
		Connection conn = getConnection();        
		int totalContents = questionBoardDao.selectTotalQuestionContents(conn);

		close(conn);
		return totalContents;
	}
	

	
	public int insertQnaBoard(QuestionBoard questionBoard) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// board테이블 행추가
			result = questionBoardDao.insertQnaBoard(conn, questionBoard);
			
			// 생성된 board_no 가져오기
			int questionBoardNo = questionBoardDao.selectLastQnaBoardNo(conn);
			System.out.println("questionBoardNo@service = " + questionBoardNo);
			
			// board객체에 set -> servlet에서 참조
			questionBoard.setNo(questionBoardNo);
			
			// attachment테이블 행추가
			Attachment attach = questionBoard.getAttach();
			if(attach != null) {
				attach.setBoardNo(questionBoardNo);
				result = questionBoardDao.insertQnaAttachment(conn, attach);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}
	
	public QuestionBoard selectOneQnaBoard(int no) {
		Connection conn = getConnection();
		
		QuestionBoard questionBoard =  questionBoardDao.selectOneQnaBoard(conn,no);
		
		close(conn);
		
		return questionBoard;
	}

	public Attachment selectOneQnaAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = questionBoardDao.selectOneQnaAttachment(conn, no);;
		
		close(conn);
		return attach;
	}

	public int updateQnaReadCount(int no) {
		Connection conn = getConnection();
		int result = 0;
		result = questionBoardDao.updateQnaReadCount(conn, no);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		return result;
	}

	public List<QuestionBoardComment> selectQnaCommentList(int no) {
		Connection conn = getConnection();
		List<QuestionBoardComment> commentList = questionBoardDao.selectQnaCommentList(conn, no);
		
		close(conn);
		return commentList;
	}

	public int insertQnaBoardComment(QuestionBoardComment bc) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = questionBoardDao.insertQnaBoardComment(conn, bc);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	public List<QuestionBoard> ajaxMainQuestionBoardList() {
		Connection conn = getConnection();
		List<QuestionBoard> list = questionBoardDao.ajaxMainQuestionBoardList(conn);
		close(conn);
		
		return list;
	}

	public int deleteQnaAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = questionBoardDao.deleteQnaAttachment(conn, attachNo);
			if(result == 0)
				throw new IllegalArgumentException("해당 첨부파일이 존재하지 않습니다. : " + attachNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int updateQnaBoard(QuestionBoard questionBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. 게시글 수정 - update board
			result = questionBoardDao.updateQnaBoard(conn, questionBoard);
			
			// 2. 첨부파일이 있는 경우 - insert into attachment
			Attachment attach = questionBoard.getAttach();
			if(attach != null) {
				// 위에 boardEnroll에서 썼던 메소드. 저기에서는 보드넘버를 구하기 위해 보드넘버 구하는 메소드를 먼저 했지만, 여기서는 보드넘버가 이미 있으므로 그 과정은 필요없다.
				result = questionBoardDao.insertQnaAttachment(conn, attach);   
			}
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteQnaBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = questionBoardDao.deleteQnaBoardComment(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteQnaBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = questionBoardDao.deleteQnaBoard(conn, no);
			if(result == 0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : " + no );
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateQnaAnswerStatus(String string, int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = questionBoardDao.updateQnaAnswerStatus(conn, string, no);
			if(result == 0)
				throw new IllegalArgumentException();
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; //controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}
	
}

	

	