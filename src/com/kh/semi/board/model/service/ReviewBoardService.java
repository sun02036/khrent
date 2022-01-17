package com.kh.semi.board.model.service;
import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.rollback;
import java.sql.Connection;
import java.util.List;

import com.kh.semi.board.model.dao.ReviewBoardDao;
import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.CommunityBoardComment;
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.board.model.vo.ReviewBoardComment;
import com.kh.semi.reservation.model.vo.Reservation;

public class ReviewBoardService {

	private ReviewBoardDao reviewBoardDao = new ReviewBoardDao();
	
	public List<Reservation> selectOneReservation(String reserNo) {
		Connection conn = getConnection();
		List<Reservation> reservationList = reviewBoardDao.selectOneReservation(reserNo, conn);
		close(conn);
		return reservationList;
	}

	public int insertReviewBoard(ReviewBoard reviewBoard) {
		Connection conn = getConnection();
		int result = 0;
		
		
		try {
			// review_board 테이블 행 추가
			result = reviewBoardDao.insertReviewBoard(conn, reviewBoard);
			
			// 생성된 review_no 가져오기
			int reviewBoardNo = reviewBoardDao.selectLastReviewBoardNo(conn);
			System.out.println("reviewBoardNo@servlet = " + reviewBoardNo);
			
			// reviewBoard객체에 set -> servlet에서 참조
			reviewBoard.setReviewNo(reviewBoardNo);
			
			// attachment테이블 행 추가
			Attachment attach = reviewBoard.getAttach();
			if(attach != null) {
				attach.setBoardNo(reviewBoardNo);
				result = reviewBoardDao.insertReviewBoardAttachment(conn, attach);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}

	public List<ReviewBoard> selectAllReviewBoard(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<ReviewBoard> list = reviewBoardDao.selectAllReviewBoard(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectTotalReviewContents() {
		Connection conn = getConnection();
		int totalContents = reviewBoardDao.selectTotalReviewContents(conn);
		close(conn);
		return totalContents;
	}

	public int updateReviewBoardReadCount(int no) {
		Connection conn = getConnection();
		int result = reviewBoardDao.updateReviewBoardReadCount(conn, no);
		 
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public ReviewBoard selectOneReviewBoard(int no) {
		Connection conn = getConnection();
		
		ReviewBoard reviewBoard = reviewBoardDao.selectOneReviewBoard(conn, no);
		Attachment attach = reviewBoardDao.selectOneAttachmentByReviewBoardNo(conn, no);
		reviewBoard.setAttach(attach);
		close(conn);
		return reviewBoard;
		
	}

	public List<ReviewBoardComment> selectReviewCommentList(int no) {
		Connection conn = getConnection();
		List<ReviewBoardComment> list = null;
		
		list = reviewBoardDao.selectReviewBoardCommentList(conn,no);
		
		close(conn);
		
		return list;
	}

	public int insertReviewBoardComment(ReviewBoardComment reviewBoardComment) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = reviewBoardDao.insertReviewBoardComment(conn, reviewBoardComment);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public Attachment selectOneReviewBoardAttachment(int attachNo) {
		Connection conn = getConnection();
		Attachment attach = reviewBoardDao.selectOneReviewBoardAttachment(conn, attachNo);
		close(conn);
		return attach;
	}

	public int deleteReviewBoardAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.deleteReviewBoardAttachment(conn, attachNo);
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

	public int updateReviewBoard(ReviewBoard reviewBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. 게시글 수정 - update board
			result = reviewBoardDao.updateReviewBoard(conn, reviewBoard);
			
			// 2. 첨부파일이 있는 경우 - insert into attachment
			Attachment attach = reviewBoard.getAttach();
			if(attach != null) {
				// 위에 boardEnroll에서 썼던 메소드. 저기에서는 보드넘버를 구하기 위해 보드넘버 구하는 메소드를 먼저 했지만, 여기서는 보드넘버가 이미 있으므로 그 과정은 필요없다.
				result = reviewBoardDao.insertReviewBoardAttachment(conn, attach);   
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

	public int deleteReviewBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.deleteReviewBoard(conn, no);
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

	public int updateReservationReviewSatatus(String isThereReserveNumber, String no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.updateReservationReviewSatatus(conn, isThereReserveNumber, no);
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

	public List<ReviewBoard> selectMyReviewBoard(String memberId) {
		Connection conn = getConnection();
		List<ReviewBoard> list = reviewBoardDao.selectMyReviewBoard(conn, memberId);
		close(conn);
		return list;
	}

	public int plusOneAssessCnt(String carName) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.plusOneAssessCnt(conn, carName);
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

	public int minusOneAssessCnt(String carName) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.minusOneAssessCnt(conn, carName);
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

	public List<ReviewBoard> ajaxMainReviewBoardList() {
		Connection conn = getConnection();
		List<ReviewBoard> list = reviewBoardDao.ajaxMainReviewBoardList(conn);
		close(conn);
		
		return list;
		}
	
	public int insertAvgScore(Double score, String carName) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.insertAvgScore(conn, score, carName);
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
	
	public int plusPoint(String writer) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reviewBoardDao.plusPoint(conn, writer);
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
	
