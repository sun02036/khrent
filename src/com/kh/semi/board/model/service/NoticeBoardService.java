package com.kh.semi.board.model.service;

import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.board.model.dao.NoticeBoardDao;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.board.model.vo.QuestionBoard;

public class NoticeBoardService {

	private NoticeBoardDao noticeBoardDao = new NoticeBoardDao();

	public List<NoticeBoard> selectAllNoticeBoard(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<NoticeBoard> list = noticeBoardDao.selectAllNoticeBoard(conn, startRownum, endRownum);
		close(conn);
		
		return list;
	}

	public int selectTotalNoticeContents() {
		Connection conn = getConnection();        
		int totalContents = noticeBoardDao.selectTotalNoticeContents(conn);

		close(conn);
		return totalContents;
	}
	
	public NoticeBoard selectOneBoard(int no) {
		Connection conn = getConnection();
		NoticeBoard noticeBoard =  noticeBoardDao.selectOneBoard(conn,no);
		close(conn);
		return noticeBoard;
	}
	public int insertNoticeBoard(NoticeBoard noticeBoard) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// board테이블 행추가
			result = noticeBoardDao.insertNoticeBoard(conn, noticeBoard);
			
			// 생성된 board_no 가져오기
			int noticeBoardNo = noticeBoardDao.selectLastNoticeBoardNo(conn);
			System.out.println("noticeBoardNo@service = " + noticeBoardNo);
			
			// board객체에 set -> servlet에서 참조
			noticeBoard.setNoticeNo(noticeBoardNo);
			
			// attachment테이블 행추가
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}
	
	public int deleteNoticeBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = noticeBoardDao.deleteNoticeBoard(conn, no);
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
	
	public int updateNoticeBoard(NoticeBoard noticeBoard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1.게시글 수정 update board문
			result = noticeBoardDao.updateNoticeBoard(conn, noticeBoard);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	public List<NoticeBoard> ajaxMainNoticeBoardList() {
		Connection conn = getConnection();
		List<NoticeBoard> list = noticeBoardDao.ajaxMainNoticeBoardList(conn);
		close(conn);
		
		return list;
	}
	
	public int updateNoticeBoardReadCount(int no) {
		Connection conn = getConnection();
		int result = noticeBoardDao.updateNoticeBoardReadCount(conn, no);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}
	
	
