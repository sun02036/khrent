package com.kh.semi.member.model.service;

import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.member.model.dao.MemberDao;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberDel;
import com.kh.semi.reservation.model.vo.Reservation;

public class MemberService {

	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	private MemberDao memberDao = new MemberDao();

	public Member selectOneMember(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.selectOneMember(conn, memberId);
		close(conn);
		return member;
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.insertMember(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

    public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updatePassword(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

    public int deleteMember(String memberId) {
 		Connection conn = getConnection();
 		int result = memberDao.deleteMember(conn, memberId);
 		if(result>0)
 			commit(conn);
 		else 
 			rollback(conn);
 		close(conn);
 		return result;
 	}

    public int updateMemberPhone(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMemberPhone(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int LicenseRegister(Member member) {
		Connection conn = getConnection();
		int result = memberDao.LicenseRegister(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Member> selectAllMember(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectAllMember(conn, startRownum, endRownum);
		close(conn);
		return list;
	}
	
	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContent = memberDao.selectTotalContents(conn);
		close(conn);
		return totalContent;
	}

	public List<Member> searchMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Member> list = memberDao.searchMember(conn, param);
		close(conn);
		return list;
	}

	public int searchMemberCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = memberDao.searchMemberCount(conn, param);
		close(conn);
		return totalContent;
	}

	public int adminMemberUpdate(Member member) {
		Connection conn = getConnection();
		int result = memberDao.adminMemberUpdate(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<MemberDel> selectAllDeleteMember(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<MemberDel> list = memberDao.selectAllDeleteMember(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectTotalDeleteContents() {
		Connection conn = getConnection();
		int totalContent = memberDao.selectTotalDeleteContents(conn);
		close(conn);
		return totalContent;
	}

	public int updateMemberRole(String memberId, String memberRole) {
		Connection conn = getConnection();
		int result = memberDao.updateMemberRole(conn, memberId, memberRole);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int adminMemberUpdatePassword(Member member) {
		Connection conn = getConnection();
		int result = memberDao.adminMemberUpdatePassword(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int adminMemberUpdateMileage(Member member) {
		Connection conn = getConnection();
		int result = memberDao.adminMemberUpdateMileage(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Reservation> selectMyReservationList(String memberId) {
		Connection conn = getConnection();
		List<Reservation> list = memberDao.selectMyReservationList(conn, memberId);
		
		close(conn);
		return list;
	}

	public Member findIdMember(String memberName, String phone) {
		Connection conn = getConnection();
		Member member = memberDao.findIdMember(conn, memberName, phone);
		close(conn);
		return member;
	}

}