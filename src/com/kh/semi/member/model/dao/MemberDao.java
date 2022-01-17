package com.kh.semi.member.model.dao;

import static com.kh.semi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.member.model.vo.Member;
import com.kh.semi.member.model.vo.MemberDel;
import com.kh.semi.reservation.model.vo.Reservation;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filepath = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember"); 
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberRole());
			pstmt.setString(4, member.getMemberName());
			pstmt.setString(5, member.getPhone());
			pstmt.setInt(6, member.getMileage());
				
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public Member selectOneMember(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOneMember");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String password = rset.getString("member_pwd");
				String memberRole = rset.getString("member_role");
				String memberName = rset.getString("member_name");
				String phone = rset.getString("phone");
				int mileage = rset.getInt("mileage");
				Date regDate = rset.getDate("reg_date");
				String issueDate = rset.getString("issue_date");
				String licenseType = rset.getString("license_type");
				String licenseNo = rset.getString("license_no");
				
				member = new Member(memberId, password, memberRole, memberName, phone, mileage, regDate, issueDate, licenseType, licenseNo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return member;
	}

    public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword"); 

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

    public int deleteMember(Connection conn, String membmerId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 
		System.out.println("query@deleteMember@Dao = " + query);
		try {
			
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, membmerId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberPhone(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberPhone"); 
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getPhone());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int LicenseRegister(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("licenseRegister"); 
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getIssue_date());
			pstmt.setString(2, member.getLicense_type());
			pstmt.setString(3, member.getLicense_no());
			pstmt.setString(4, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Member> selectAllMember(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllMember"); 
		
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("member_pwd"));
				member.setMemberRole(rset.getString("member_role"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setMileage(rset.getInt("mileage"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setIssue_date(rset.getString("issue_date"));
				member.setLicense_type(rset.getString("license_type"));
				member.setLicense_no(rset.getString("license_no"));
			
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public List<Member> searchMember(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "memberId":
			sql = prop.getProperty("searchMemberByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "memberName":
			sql = prop.getProperty("searchMemberByMemberName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		}
		
		System.out.println("searchType@dao = " + searchType);
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, (String) param.get("searchKeyword"));
			pstmt.setInt(2, (int) param.get("start"));
			pstmt.setInt(3, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				
				member.setMemberId(rset.getString("member_id"));
				member.setPassword(rset.getString("member_pwd"));
				member.setMemberRole(rset.getString("member_role"));
				member.setMemberName(rset.getString("member_name"));
				member.setPhone(rset.getString("phone"));
				member.setMileage(rset.getInt("mileage"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setIssue_date(rset.getString("issue_date"));
				member.setLicense_type(rset.getString("license_type"));
				member.setLicense_no(rset.getString("license_no"));
				
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int searchMemberCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "memberId":
			sql = prop.getProperty("searchMemberCountByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "memberName":
			sql = prop.getProperty("searchMemberCountByMemberName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		}
		
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	public int adminMemberUpdate(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminMemberUpdate"); 
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getLicense_type());
			pstmt.setString(4, member.getLicense_no());
			pstmt.setString(5, member.getIssue_date());
			pstmt.setString(6, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<MemberDel> selectAllDeleteMember(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllDeleteMember"); 
		
		ResultSet rset = null;
		List<MemberDel> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				MemberDel memberDel = new MemberDel();
				memberDel.setMemberId(rset.getString("member_id"));
				memberDel.setPassword(rset.getString("member_pwd"));
				memberDel.setMemberRole(rset.getString("member_role"));
				memberDel.setMemberName(rset.getString("member_name"));
				memberDel.setPhone(rset.getString("phone"));
				memberDel.setMileage(rset.getInt("mileage"));
				memberDel.setRegDate(rset.getDate("reg_date"));
				memberDel.setIssue_date(rset.getString("issue_date"));
				memberDel.setLicense_type(rset.getString("license_type"));
				memberDel.setLicense_no(rset.getString("license_no"));
				memberDel.setDelDate(rset.getDate("del_date"));
			
				list.add(memberDel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectTotalDeleteContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalDeleteContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	   public int updateMemberRole(Connection conn, String memberId, String memberRole) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("adminUpdateMemberRole"); 

			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, memberRole);
				pstmt.setString(2, memberId);

				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

	public int adminMemberUpdatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminUpdateMemberPassword"); 
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int adminMemberUpdateMileage(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminUpdateMemberMileage"); 
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, member.getMileage());
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Reservation> selectMyReservationList(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMyReservationList"); 
		
		ResultSet rset = null;
		List<Reservation> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
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
			
				list.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public Member findIdMember(Connection conn, String memberName, String phone) {
		String sql = prop.getProperty("findIdMember");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberId = rset.getString("member_id");
				
				member = new Member(memberId, null, null, memberName, phone, 0, null, null, null, null);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


}
   