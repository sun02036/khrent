package com.kh.semi.reservation.model.dao;

import static com.kh.semi.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.semi.board.model.dao.CommunityBoardDao;
import com.kh.semi.reservation.model.vo.Car;
import com.kh.semi.reservation.model.vo.CarInfo;
import com.kh.semi.reservation.model.vo.CarList;
import com.kh.semi.reservation.model.vo.Reservation;

public class ReservationDao {
	
	private Properties prop = new Properties();
	
	public ReservationDao() {
		String filepath = CommunityBoardDao.class.getResource("/sql/reservation/reservation-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Car> searchCar(Connection conn, String startDate, String endDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Car> list = new ArrayList<>();
		String sql = prop.getProperty("searchCar");
		System.out.println(sql);
//		System.out.println("sdate = " + startDate);
//		System.out.println("edate = " + endDate);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Car car = new Car();
				
				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setCarOption(rset.getString("car_option"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setPrice(rset.getInt("price"));
				car.setImg(rset.getString("img"));
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

	public Car selectOneCar(Connection conn, String carCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Car car = null;
		String sql = prop.getProperty("selectOneCar");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carCode);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				car = new Car();
				
				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setCarOption(rset.getString("car_option"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setPrice(rset.getInt("price"));
				car.setImg(rset.getString("img"));
				car.setAssessCnt(rset.getInt("assess_cnt"));
				car.setAvgScore(rset.getInt("avg_score"));
				car.setReservCnt(rset.getInt("reserv_cnt"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return car;
	}

	public int insertReservation(Connection conn, Reservation reservation) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertReservation");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reservation.getMemberId());
			pstmt.setString(2, reservation.getCarCode());
			pstmt.setString(3, reservation.getCarName());
			pstmt.setString(4, reservation.getStartDate());
			pstmt.setString(5, reservation.getEndDate());
			pstmt.setInt(6, reservation.getPrice());
			pstmt.setString(7, reservation.getInsuranceType());
			pstmt.setString(8, reservation.getIssueDate());
			pstmt.setString(9, reservation.getLicenseType());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Car> searchCarSort(Connection conn, String startDate, String endDate, String sortType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Car> list = new ArrayList<>();
		String sql = prop.getProperty("searchCarSort");
		sql = sql.replace("#", sortType);
		
		System.out.println(sql);
//		System.out.println("sdate = " + startDate);
//		System.out.println("edate = " + endDate);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Car car = new Car();
				
				car.setCarCode(rset.getString("car_code"));
				car.setCarName(rset.getString("car_name"));
				car.setReleaseYear(rset.getString("release_year"));
				car.setMaker(rset.getString("maker"));
				car.setFuel(rset.getString("fuel"));
				car.setCarSize(rset.getString("car_size"));
				car.setCarOption(rset.getString("car_option"));
				car.setNumberPlate(rset.getString("number_plate"));
				car.setPrice(rset.getInt("price"));
				car.setImg(rset.getString("img"));
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
	
	public int deleteRservation(Connection conn, String reserNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteRservation");
		System.out.println("sql@deleteReservation Dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserNo);
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
