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
import com.kh.semi.board.model.vo.CommunityBoardComment;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.board.model.vo.ReviewBoard;
import com.kh.semi.board.model.vo.ReviewBoardComment;
import com.kh.semi.reservation.model.vo.Reservation;

public class ReviewBoardDao {

	private Properties prop = new Properties();
	
	public ReviewBoardDao() {
		String filepath = ReviewBoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Reservation> selectOneReservation(String reserNo, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> reservationList = new ArrayList<>();
		String sql = prop.getProperty("selectOneReservation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reservation reservation = new Reservation();
				
				reservation.setReserNo(rset.getString("reserv_no"));
				reservation.setCarCode(rset.getString("car_code"));
				reservation.setCarName(rset.getString("car_name"));
				reservation.setStartDate(rset.getString("start_date"));
				reservation.setEndDate(rset.getString("end_date"));
				reservation.setPrice(rset.getInt("price"));
				reservation.setReviewStatus(rset.getString("review_status"));
				reservation.setReturnStatus(rset.getString("return_status"));
				
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return reservationList;
	}

	public int insertReviewBoard(Connection conn, ReviewBoard reviewBoard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReviewBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewBoard.getReservNo());
			pstmt.setString(2, reviewBoard.getReviewWriter());
			pstmt.setString(3, reviewBoard.getReviewTitle());
			pstmt.setString(4, reviewBoard.getReviewContent());
			pstmt.setString(5,  reviewBoard.getCarName());
			pstmt.setDouble(6, reviewBoard.getScore());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastReviewBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastReviewBoardNo");
		int reviewBoardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				reviewBoardNo = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return reviewBoardNo;
	}

	public int insertReviewBoardAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReviewBoardAttachment");
		int result = 0;
		System.out.println(attach);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<ReviewBoard> selectAllReviewBoard(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ReviewBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllReviewBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ReviewBoard reviewBoard = new ReviewBoard();
				
				reviewBoard.setReviewNo(rset.getInt("review_no"));
				reviewBoard.setReviewWriter(rset.getString("review_writer"));
				reviewBoard.setReviewTitle(rset.getString("review_title"));
				reviewBoard.setReviewContent(rset.getString("review_content"));
				reviewBoard.setCarName(rset.getString("car_name"));
				reviewBoard.setRegDate(rset.getDate("reg_date"));
				reviewBoard.setReadCount(rset.getInt("read_count"));
				reviewBoard.setScore(rset.getDouble("score"));
				
				reviewBoard.setBoardCommentCount(rset.getInt("bc_count"));
				System.out.println("reviewBoard = " + reviewBoard);
				
				if(rset.getInt("attach_no") != 0) {
					Attachment attach = new Attachment();
					attach.setNo(rset.getInt("attach_no"));
					attach.setBoardNo(rset.getInt("attach_no"));
					attach.setOriginalFilename(rset.getString("original_filename"));
					attach.setRenamedFilename(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("attach_reg_date"));
					
					reviewBoard.setAttach(attach);
				}
				
				list.add(reviewBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectTotalReviewContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("selectTotalReviewContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("cnt");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int updateReviewBoardReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReviewBoardReadCount");
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

	public ReviewBoard selectOneReviewBoard(Connection conn, int no) {
		ReviewBoard reviewBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneReviewBoard");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				reviewBoard = new ReviewBoard();
				reviewBoard.setReviewNo(rset.getInt("review_no"));
				reviewBoard.setReservNo(rset.getString("reserv_no"));
				reviewBoard.setReviewWriter(rset.getString("review_writer"));
				reviewBoard.setReviewTitle(rset.getString("review_title"));
				reviewBoard.setReviewContent(rset.getString("review_content"));
				reviewBoard.setCarName(rset.getString("car_name"));
				reviewBoard.setRegDate(rset.getDate("reg_date"));
				reviewBoard.setReadCount(rset.getInt("read_count"));
				reviewBoard.setScore(rset.getDouble("score"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return reviewBoard;
	}

	public Attachment selectOneAttachmentByReviewBoardNo(Connection conn, int no) {
		Attachment attach = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneAttachmentByReviewBoardNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attach = new Attachment();
				
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("review_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public List<ReviewBoardComment> selectReviewBoardCommentList(Connection conn, int no) {
		List<ReviewBoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewBoardCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewBoardComment reviewBoardComment = new ReviewBoardComment();
				reviewBoardComment.setNo(rset.getInt("no"));
				reviewBoardComment.setCommentLevel(rset.getInt("comment_level"));
				reviewBoardComment.setWriter(rset.getString("writer"));
				reviewBoardComment.setContent(rset.getString("content"));
				reviewBoardComment.setReview_no(rset.getInt("review_no"));
				reviewBoardComment.setCommentRef(rset.getInt("comment_ref"));
				reviewBoardComment.setRegDate(rset.getDate("reg_date"));
				list.add(reviewBoardComment);
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

	public int insertReviewBoardComment(Connection conn, ReviewBoardComment reviewBoardComment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertReviewBoardComment");
		System.out.println("ReviewBoardDao@sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewBoardComment.getReview_no());
			pstmt.setString(2, reviewBoardComment.getWriter());
			pstmt.setString(3, reviewBoardComment.getContent());
			pstmt.setInt(4, reviewBoardComment.getCommentLevel());
			pstmt.setObject(5, reviewBoardComment.getCommentRef() == 0 ? null : reviewBoardComment.getCommentRef());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Attachment selectOneReviewBoardAttachment(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("selectOneReviewBoardAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("review_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		
		return attach;
	}

	public int deleteReviewBoardAttachment(Connection conn, int attachNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteReviewBoardAttachment");
		
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

	public int updateReviewBoard(Connection conn, ReviewBoard reviewBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReviewBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewBoard.getReviewTitle());
			pstmt.setString(2, reviewBoard.getReviewContent());
			pstmt.setDouble(3, reviewBoard.getScore());
			pstmt.setInt(4, reviewBoard.getReviewNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReviewBoard(Connection conn, int no) {
		// board의 행이 삭제되면 그 행의 no를 참조하는 attachment의 행도 따라서 삭제된다. on delete cascade 이기 때문에
		
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteReviewBoard");
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

	public int updateReservationReviewSatatus(Connection conn, String isThereReserveNumber, String no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReservationReviewStatus");
		System.out.println(sql);
		System.out.println(isThereReserveNumber + "," + no);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setString(1, isThereReserveNumber);
			pstmt.setString(2, no);
			
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

	public List<ReviewBoard> selectMyReviewBoard(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ReviewBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectMyReviewBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ReviewBoard reviewBoard = new ReviewBoard();
				
				reviewBoard.setReviewNo(rset.getInt("review_no"));
				reviewBoard.setReviewWriter(rset.getString("review_writer"));
				reviewBoard.setReviewTitle(rset.getString("review_title"));
				reviewBoard.setReviewContent(rset.getString("review_content"));
				reviewBoard.setCarName(rset.getString("car_name"));
				reviewBoard.setRegDate(rset.getDate("reg_date"));
				reviewBoard.setReadCount(rset.getInt("read_count"));
				reviewBoard.setScore(rset.getDouble("score"));
				
				System.out.println("reviewBoard = " + reviewBoard);
				
				list.add(reviewBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int plusOneAssessCnt(Connection conn, String carName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("plusOneAssessCnt");
		System.out.println(sql);
		System.out.println("차이름은?@plusOneAssessCntDao " + carName);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setString(1, carName);
			
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

	public int minusOneAssessCnt(Connection conn, String carName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("minusOneAssessCnt");
		System.out.println(sql);
		System.out.println("차이름은?@minusOneAssessCntDao " + carName);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setString(1, carName);
			
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

	public List<ReviewBoard> ajaxMainReviewBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ReviewBoard> list = new ArrayList<>();
		String sql = prop.getProperty("selectHomeReviewBoardList");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewBoard reviewBoard = new ReviewBoard();
				
				reviewBoard.setReviewTitle(rset.getString("review_title"));
				reviewBoard.setRegDate(rset.getDate("reg_date"));

				System.out.println("reviewBoard = " + reviewBoard);
				
				list.add(reviewBoard);
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
	
	public int insertAvgScore(Connection conn, Double score, String carName) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAvgScore");
		System.out.println(sql);
		System.out.println("차이름은?@insertAvgScoreDao " + carName);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setDouble(1, score);
			pstmt.setString(2, carName);
			
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

	public int plusPoint(Connection conn, String writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("plusPoint");
		System.out.println("plusPoint쿼리@Dao = " + sql);
		System.out.println("글쓴이?@plusPointDao = " + writer);
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문 완성시키기
			pstmt.setString(1, writer);
			
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
	

