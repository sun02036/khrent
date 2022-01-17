package com.kh.semi.board.model.service;

import static com.kh.semi.common.JdbcTemplate.*;
import java.sql.Connection;
import java.util.List;
import com.kh.semi.board.model.dao.CommunityBoardDao;
import com.kh.semi.board.model.vo.Attachment;
import com.kh.semi.board.model.vo.CommunityBoard;
import com.kh.semi.board.model.vo.CommunityBoardComment;

public class CommunityBoardService {
	
	private CommunityBoardDao communityBoardDao = new CommunityBoardDao();

	public List<CommunityBoard> selectAllCommunityBoard(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<CommunityBoard> list = communityBoardDao.selectAllCommunityBoard(conn, startRownum, endRownum);
		close(conn);
		
		return list;
	}

	public int selectTotalCommunityContents() {
		Connection conn = getConnection();        
		int totalContents = communityBoardDao.selectTotalCommunityContents(conn);

		close(conn);
		return totalContents;
	}

	public CommunityBoard selectOneBoard(int no) {
		Connection conn = getConnection();
		
		CommunityBoard communityBoard =  communityBoardDao.selectOneBoard(conn,no);
		Attachment attach = communityBoardDao.selectOneAttachmentByCommunityBoardNo(conn, no);
		communityBoard.setAttach(attach);
		close(conn);
		
		return communityBoard;
	}

	public int insertCommunityComment(CommunityBoardComment communityBoardComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = communityBoardDao.insertCommunityComment(conn,communityBoardComment);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
		
	}

	public List<CommunityBoardComment> selectCommentList(int no) {
		Connection conn = getConnection();
		List<CommunityBoardComment> list = null;
		
		list = communityBoardDao.selectCommentList(conn,no);
		
		close(conn);
		
		return list;
	}

	public int updateReadCount(int no) {
		Connection conn = getConnection();
		int result = communityBoardDao.updateReadCount(conn,no);
	
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int insertCommunityBoard(CommunityBoard communityBoard) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// board테이블 행추가
			result = communityBoardDao.insertCommunityBoard(conn, communityBoard);
			
			// 생성된 board_no 가져오기
			int communityBoardNo = communityBoardDao.selectLastCommunityBoardNo(conn);
			System.out.println("questionBoardNo@service = " + communityBoardNo);
			
			// board객체에 set -> servlet에서 참조
			communityBoard.setNo(communityBoardNo);
			
			// attachment테이블 행추가
			Attachment attach = communityBoard.getAttach();
			if(attach != null) {
				attach.setBoardNo(communityBoardNo);
				result = communityBoardDao.insertCommunityAttachment(conn, attach);
			}
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}

	public Attachment selectOneCommunityAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = communityBoardDao.selectOneCommunityAttachment(conn, no);
		close(conn);
		return attach;
	}

	public int deleteCommunityBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = communityBoardDao.deleteCommunityBoard(conn, no);
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

	public int deleteCommunityAttachment(int attachNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = communityBoardDao.deleteCommunityAttachment(conn, attachNo);
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

	public int updateCommunityBoard(CommunityBoard communityBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. 게시글 수정 - update board
			result = communityBoardDao.updateCommunityBoard(conn, communityBoard);
			
			// 2. 첨부파일이 있는 경우 - insert into attachment
			Attachment attach = communityBoard.getAttach();
			if(attach != null) {
				// 위에 boardEnroll에서 썼던 메소드. 저기에서는 보드넘버를 구하기 위해 보드넘버 구하는 메소드를 먼저 했지만, 여기서는 보드넘버가 이미 있으므로 그 과정은 필요없다.
				result = communityBoardDao.insertCommunityAttachment(conn, attach);   
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

	public int deleteCommunityBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = communityBoardDao.deleteCommunityBoardComment(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

}




