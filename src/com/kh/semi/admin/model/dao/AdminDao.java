package com.kh.semi.admin.model.dao;

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

import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.member.model.dao.MemberDao;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.Car;
import com.kh.semi.reservation.model.vo.CarInfo;
import com.kh.semi.reservation.model.vo.CarList;
import com.kh.semi.reservation.model.vo.Reservation;

public class AdminDao {
	
	private Properties prop = new Properties();
	
	public AdminDao() {
		String filepath = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Car> selectTotalCarList(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalCarList"); 
		
		ResultSet rset = null;
		List<Car> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Car car = new Car();

				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setImg(rset.getString("img"));
				car.setCarOption(rset.getString("car_option"));
				car.setPrice(rset.getInt("price"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setAssessCnt(rset.getInt("assess_cnt"));
				car.setAvgScore(rset.getInt("avg_score"));
				car.setReservCnt(rset.getInt("reserv_cnt"));
			
				list.add(car);
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

	public int selectTotalCarContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalCarContents");
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

	public List<Car> searchCar(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Car> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carCode":
			sql = prop.getProperty("searchCarByCarCode");
			param.put("searchKeyword", "%" + param.get("searchKeyword")); 
			break;
		case "carName":
			sql = prop.getProperty("searchCarByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "maker":
			sql = prop.getProperty("searchCarByMaker");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "fuel":
			sql = prop.getProperty("searchCarByFuel");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carSize":
			sql = prop.getProperty("searchCarByCarSize");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "numberPlate":
			sql = prop.getProperty("searchCarByNumberPlate");
			param.put("searchKeyword", "%" + param.get("searchKeyword"));
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
				
				Car car = new Car();
				
				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setImg(rset.getString("img"));
				car.setCarOption(rset.getString("car_option"));
				car.setPrice(rset.getInt("price"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setAssessCnt(rset.getInt("assess_cnt"));
				car.setAvgScore(rset.getInt("avg_score"));
				car.setReservCnt(rset.getInt("reserv_cnt"));
			
				list.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int searchCarCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carCode":
			sql = prop.getProperty("searchCarCountByCarCode");
			param.put("searchKeyword", "%" + param.get("searchKeyword")); 
			break;
		case "carName":
			sql = prop.getProperty("searchCarCountByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "maker":
			sql = prop.getProperty("searchCarCountByMaker");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "fuel":
			sql = prop.getProperty("searchCarCountByFuel");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carSize":
			sql = prop.getProperty("searchCarCountByCarSize");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "numberPlate":
			sql = prop.getProperty("searchCarCountByNumberPlate");
			param.put("searchKeyword", "%" + param.get("searchKeyword"));
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

	public int selectLastCarInfoNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastCarInfoNo");
		int carInfoNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				carInfoNo = rset.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BoardException("게시물번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return carInfoNo;
	}

	public int insertCarInfo(Connection conn, CarInfo carInfo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCarInfo");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, carInfo.getCarName());
			pstmt.setString(2, carInfo.getMaker());
			pstmt.setString(3, carInfo.getFuel());
			pstmt.setString(4, carInfo.getCarSize());
			pstmt.setString(5, carInfo.getImg());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new BoardException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectCarInfoTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCarInfoTotalContents");
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

	public int insertCarList(Connection conn, CarList carList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCarList"); 
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, carList.getCarName());
			pstmt.setString(2, carList.getReleaseYear());
			pstmt.setString(3, carList.getCarOption());
			pstmt.setInt(4, carList.getPrice());
			pstmt.setString(5, carList.getNumberPlate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public CarList selectOneCar(Connection conn, String carCode) {
		String sql = prop.getProperty("selectOneCar");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CarList carList = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String carName = rset.getString("car_name");
				String releaseYear = rset.getString("release_year");
				String carOption = rset.getString("car_option");
				int price = rset.getInt("price");
				String numberPlate = rset.getString("number_plate");
				
				carList = new CarList(carCode, carName, releaseYear, carOption, price, numberPlate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return carList;
	}

	public int adminCarListUpdate(Connection conn, CarList carList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("adminCarListUpdate"); 
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, carList.getCarOption());
			pstmt.setInt(2, carList.getPrice());
			pstmt.setString(3, carList.getCarCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int ajaxAdminTodayEnroll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("ajaxAdminTodayEnroll");
		int todayEnroll = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				todayEnroll = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return todayEnroll;
	}

	public int ajaxAdminTodayStartReservation(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("ajaxAdminTodayStartReservation");
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

	public List<Member> ajaxAdminFiveRecentEnroll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		String sql = prop.getProperty("ajaxAdminFiveRecentEnroll");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				
				member.setMemberId(rset.getString("member_id"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberRole(rset.getString("member_role"));
				member.setRegDate(rset.getDate("reg_date"));
				

				System.out.println("member = " + member);
				
				list.add(member);
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

	public List<Reservation> ajaxAdminFiveRecentReservation(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> list = new ArrayList<>();
		String sql = prop.getProperty("ajaxAdminFiveRecentReservation");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Reservation reservation = new Reservation();
				
				reservation.setReserNo(rset.getString("reserv_no"));
				reservation.setMemberId(rset.getString("member_id"));
				reservation.setCarName(rset.getString("car_name"));
				reservation.setStartDate(rset.getString("start_date"));
				reservation.setEndDate(rset.getString("end_date"));
				reservation.setPrice(rset.getInt("price"));
				

				System.out.println("reservation = " + reservation);
				
				list.add(reservation);
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

	public CarInfo selectOneCarInfo(Connection conn, String carName) {
		String sql = prop.getProperty("selectOneCarInfo");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CarInfo carInfo = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int carInfoNo = rset.getInt("car_info_no");
				String maker = rset.getString("maker");
				String fuel = rset.getString("fuel");
				String carSize = rset.getString("car_size");
				String img = rset.getString("img");
				int assessCnt = rset.getInt("assess_cnt");
				int avgScore = rset.getInt("avg_score");
				int reservCnt = rset.getInt("reserv_cnt");
				
				carInfo = new CarInfo(carInfoNo, carName, maker, fuel, carSize, img, assessCnt, avgScore, reservCnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return carInfo;
	}

	public List<CarInfo> selectTotalCarInfoList(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalCarInfoList"); 
		
		ResultSet rset = null;
		List<CarInfo> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				CarInfo carInfo = new CarInfo();

				carInfo.setCarInfoNo(rset.getInt("car_info_no"));
				carInfo.setCarName(rset.getString("car_name"));
				carInfo.setMaker(rset.getString("maker"));
				carInfo.setFuel(rset.getString("fuel"));
				carInfo.setCarSize(rset.getString("car_size"));
				carInfo.setImg(rset.getString("img"));
				carInfo.setAssessCnt(rset.getInt("assess_cnt"));
				carInfo.setAvgScore(rset.getInt("avg_score"));
				carInfo.setReservCnt(rset.getInt("reserv_cnt"));
			
				list.add(carInfo);
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

	public int selectTotalCarInfoContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalCarInfoContents");
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

	public int adminCarInfoDelete(Connection conn, String carName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("adminCarInfoDelete"); 
		System.out.println("query@adminCarInfoDelete@Dao = " + query);
		try {
			
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, carName);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<CarList> selectTotalCarListOfList(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalCarListOfList"); 
		
		ResultSet rset = null;
		List<CarList> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				CarList carList = new CarList();

				carList.setCarCode(rset.getString("car_code"));
				carList.setCarName(rset.getString("car_name"));
				carList.setReleaseYear(rset.getString("release_year"));
				carList.setCarOption(rset.getString("car_option"));
				carList.setPrice(rset.getInt("price"));
				carList.setNumberPlate(rset.getString("number_plate"));
			
				list.add(carList);
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

	public int selectTotalCarListContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalCarListContents");
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

	public CarList selectOneCarList(Connection conn, String carCode) {
		String sql = prop.getProperty("selectOneCarList");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CarList carList = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String carName = rset.getString("car_name");
				String releaseYear = rset.getString("release_year");
				String carOption = rset.getString("car_option");
				int price = rset.getInt("price");
				String numberPlate = rset.getString("number_plate");
				
				carList = new CarList(carCode, carName, releaseYear, carOption, price, numberPlate);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return carList;
	}

	public int adminCarListDelete(Connection conn, String carCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("adminCarListDelete"); 
		System.out.println("query@adminCarListDelete@Dao = " + query);
		try {
			
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, carCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Reservation> selectAllReservation(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllReservation"); 
		
		ResultSet rset = null;
		List<Reservation> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Reservation reservation = new Reservation();

				reservation.setReserNo(rset.getString("reserv_no"));
				reservation.setMemberId(rset.getString("member_id"));
				reservation.setCarCode(rset.getString("car_code"));
				reservation.setCarName(rset.getString("car_name"));
				reservation.setStartDate(rset.getString("start_date"));
				reservation.setEndDate(rset.getString("end_date"));
				reservation.setPrice(rset.getInt("price"));
				reservation.setInsuranceType(rset.getString("insurance_type"));
				reservation.setIssueDate(rset.getString("issue_date"));
				reservation.setLicenseType(rset.getString("license_type"));
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

	public int selectTotalReservationContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalReservationContents");
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

	public List<Reservation> searchReservation(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reservation> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "reserNo":
			sql = prop.getProperty("searchReservationByReserNo");
			param.put("searchKeyword", "%" + param.get("searchKeyword")); 
			break;
		case "memberId":
			sql = prop.getProperty("searchReservationByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carName":
			sql = prop.getProperty("searchReservationByCarName");
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
				
				Reservation reservation = new Reservation();

				reservation.setReserNo(rset.getString("reserv_no"));
				reservation.setMemberId(rset.getString("member_id"));
				reservation.setCarCode(rset.getString("car_code"));
				reservation.setCarName(rset.getString("car_name"));
				reservation.setStartDate(rset.getString("start_date"));
				reservation.setEndDate(rset.getString("end_date"));
				reservation.setPrice(rset.getInt("price"));				
				reservation.setInsuranceType(rset.getString("insurance_type"));
				reservation.setIssueDate(rset.getString("issue_date"));
				reservation.setLicenseType(rset.getString("license_type"));
				reservation.setReviewStatus(rset.getString("review_status"));
				reservation.setReturnStatus(rset.getString("return_status"));
			
				list.add(reservation);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int searchReservationCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "reserNo":
			sql = prop.getProperty("searchReservationCountByReserNo");
			param.put("searchKeyword", "%" + param.get("searchKeyword")); 
			break;
		case "memberId":
			sql = prop.getProperty("searchReservationCountByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carName":
			sql = prop.getProperty("searchReservationCountByCarName");
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

	public List<CarInfo> searchCarInfo(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CarInfo> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carName":
			sql = prop.getProperty("searchCarInfoByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "maker":
			sql = prop.getProperty("searchCarInfoByMaker");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "fuel":
			sql = prop.getProperty("searchCarInfoByFuel");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carSize":
			sql = prop.getProperty("searchCarInfoByCarSize");
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
				
				CarInfo carInfo = new CarInfo();

				carInfo.setCarInfoNo(rset.getInt("car_info_no"));
				carInfo.setCarName(rset.getString("car_name"));
				carInfo.setMaker(rset.getString("maker"));
				carInfo.setFuel(rset.getString("fuel"));
				carInfo.setCarSize(rset.getString("car_size"));
				carInfo.setImg(rset.getString("img"));
				carInfo.setAssessCnt(rset.getInt("assess_cnt"));
				carInfo.setAvgScore(rset.getInt("avg_score"));
				carInfo.setReservCnt(rset.getInt("reserv_cnt"));
			
				list.add(carInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int searchCarInfoCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carName":
			sql = prop.getProperty("searchCarInfoCountByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "maker":
			sql = prop.getProperty("searchCarInfoCountByMaker");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "fuel":
			sql = prop.getProperty("searchCarInfoCountByFuel");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carSize":
			sql = prop.getProperty("searchCarInfoCountByCarSize");
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

	public List<CarList> searchCarList(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<CarList> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carCode":
			sql = prop.getProperty("searchCarListByCarCode");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carName":
			sql = prop.getProperty("searchCarListByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "numberPlate":
			sql = prop.getProperty("searchCarListByNumberPlate");
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
				
				CarList carList = new CarList();

				carList.setCarCode(rset.getString("car_code"));
				carList.setCarName(rset.getString("car_name"));
				carList.setReleaseYear(rset.getString("release_year"));
				carList.setCarOption(rset.getString("car_option"));
				carList.setPrice(rset.getInt("price"));
				carList.setNumberPlate(rset.getString("number_plate"));
			
				list.add(carList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int searchCarListCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "carCode":
			sql = prop.getProperty("searchCarListCountByCarCode");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "carName":
			sql = prop.getProperty("searchCarListCountByCarName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "numberPlate":
			sql = prop.getProperty("searchCarListCountByNumberPlate");
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

	public int ajaxMemberPageReservationCount(Connection conn, String memberId) {
		String sql = prop.getProperty("ajaxMemberPageReservationCount");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int reservation = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			if(rset.next())
				reservation = rset.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return reservation;
	}

	public List<Reservation> selectCarReturn(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCarReturn"); 
		
		ResultSet rset = null;
		List<Reservation> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Reservation reservation = new Reservation();

				reservation.setReserNo(rset.getString("reserv_no"));
				reservation.setMemberId(rset.getString("member_id"));
				reservation.setCarCode(rset.getString("car_code"));
				reservation.setCarName(rset.getString("car_name"));
				reservation.setStartDate(rset.getString("start_date"));
				reservation.setEndDate(rset.getString("end_date"));
				reservation.setPrice(rset.getInt("price"));
				reservation.setInsuranceType(rset.getString("insurance_type"));
				reservation.setIssueDate(rset.getString("issue_date"));
				reservation.setLicenseType(rset.getString("license_type"));
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

	public int selectCarReturnContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCarReturnContents");
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

	public Reservation selectOneReservation(Connection conn, String reserNo) {
		String sql = prop.getProperty("selectOneReservation");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reservation reservation = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberId = rset.getString("member_id");
				String carCode = rset.getString("car_code");
				String carName = rset.getString("car_name");
				String startDate = rset.getString("start_date");
				String endDate = rset.getString("end_date");
				int price = rset.getInt("price");
				String insuranceType = rset.getString("insurance_type");
				String issueDate = rset.getString("issue_date");
				String licenseType = rset.getString("license_type");
				String reviewStatus = rset.getString("review_status");
				String returnStatus = rset.getString("return_status");
				
				reservation = new Reservation(reserNo, memberId, carCode, carName, startDate, endDate, price, insuranceType, issueDate, licenseType, reviewStatus, returnStatus);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return reservation;
	}

	public int deleteReservation(Connection conn, String reserNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReservation"); 
		System.out.println("query@deleteReservation@Dao = " + query);
		try {
			
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, reserNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReturnStatus(Connection conn, Reservation reservation) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateReturnStatus"); 

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, reservation.getReturnStatus());
			pstmt.setString(2, reservation.getReserNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Car> ajaxBestFiveServlet(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Car> list = new ArrayList<>();
		String sql = prop.getProperty("ajaxBestFiveServlet");
		
		try {
			// 1. pstmt 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Car car = new Car();

				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setImg(rset.getString("img"));
				car.setCarOption(rset.getString("car_option"));
				car.setPrice(rset.getInt("price"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setAssessCnt(rset.getInt("assess_cnt"));
				car.setAvgScore(rset.getInt("avg_score"));
				car.setReservCnt(rset.getInt("reserv_cnt"));
			
				list.add(car);
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

}
