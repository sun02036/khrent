package com.kh.semi.admin.model.service;

import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.semi.admin.model.dao.AdminDao;
import com.kh.semi.board.model.vo.NoticeBoard;
import com.kh.semi.member.model.vo.Member;
import com.kh.semi.reservation.model.vo.Car;
import com.kh.semi.reservation.model.vo.CarInfo;
import com.kh.semi.reservation.model.vo.CarList;
import com.kh.semi.reservation.model.vo.Reservation;

public class AdminService {

	private AdminDao adminDao = new AdminDao();

	public List<Car> selectTotalCarList(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Car> list = adminDao.selectTotalCarList(conn, startRownum, endRownum);
		close(conn);
		return list;
	}
	
	public int selectTotalCarContents() {
		Connection conn = getConnection();
		int totalContent = adminDao.selectTotalCarContents(conn);
		close(conn);
		return totalContent;
	}

	public List<Car> searchCar(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Car> list = adminDao.searchCar(conn, param);
		close(conn);
		return list;
	}

	public int searchCarCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = adminDao.searchCarCount(conn, param);
		close(conn);
		return totalContent;
	}

	public int insertCarInfo(CarInfo carInfo) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// board테이블 행추가
			result = adminDao.insertCarInfo(conn, carInfo);
			
			// 생성된 board_no 가져오기
			int carInfoNo = adminDao.selectLastCarInfoNo(conn);
			System.out.println("carInfoNo@service = " + carInfoNo);
			
			// board객체에 set -> servlet에서 참조
			carInfo.setCarInfoNo(carInfoNo);
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}

	public int selectCarInfoTotalContents() {
		Connection conn = getConnection();
		int totalContent = adminDao.selectCarInfoTotalContents(conn);
		close(conn);
		return totalContent;
	}

	public int insertCarList(CarList carList) {
		Connection conn = getConnection();
		int result = adminDao.insertCarList(conn, carList);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public CarList selectOneCar(String carCode) {
		Connection conn = getConnection();
		CarList carList = adminDao.selectOneCar(conn, carCode);
		close(conn);
		return carList;
	}

	public int adminCarListUpdate(CarList carList) {
		Connection conn = getConnection();
		int result = adminDao.adminCarListUpdate(conn, carList);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int ajaxAdminTodayEnroll() {
		Connection conn = getConnection();
		int todayEnroll = adminDao.ajaxAdminTodayEnroll(conn);
		close(conn);
		return todayEnroll;
	}

	public int ajaxAdminTodayStartReservation() {
		Connection conn = getConnection();
		int totalContents = adminDao.ajaxAdminTodayStartReservation(conn);
		close(conn);
		return totalContents;
	}

	public List<Member> ajaxAdminFiveRecentEnroll() {
		Connection conn = getConnection();
		List<Member> list = adminDao.ajaxAdminFiveRecentEnroll(conn);
		close(conn);
		
		return list;
	}

	public List<Reservation> ajaxAdminFiveRecentReservation() {
		Connection conn = getConnection();
		List<Reservation> list = adminDao.ajaxAdminFiveRecentReservation(conn);
		close(conn);
		
		return list;
	}

	public CarInfo selectOneCarInfo(String carName) {
		Connection conn = getConnection();
		CarInfo carList = adminDao.selectOneCarInfo(conn, carName);
		close(conn);
		return carList;
	}

	public List<CarInfo> selectTotalCarInfo(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<CarInfo> list = adminDao.selectTotalCarInfoList(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectTotalCarInfoContents() {
		Connection conn = getConnection();
		int totalContent = adminDao.selectTotalCarInfoContents(conn);
		close(conn);
		return totalContent;
	}

	public int adminCarInfoDelete(String carName) {
 		Connection conn = getConnection();
 		int result = adminDao.adminCarInfoDelete(conn, carName);
 		if(result>0)
 			commit(conn);
 		else 
 			rollback(conn);
 		close(conn);
 		return result;
	}

	public List<CarList> selectTotalCarListOfList(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<CarList> list = adminDao.selectTotalCarListOfList(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectTotalCarListContents() {
		Connection conn = getConnection();
		int totalContent = adminDao.selectTotalCarListContents(conn);
		close(conn);
		return totalContent;
	}

	public CarList selectOneCarList(String carCode) {
		Connection conn = getConnection();
		CarList carList = adminDao.selectOneCarList(conn, carCode);
		close(conn);
		return carList;
	}

	public int adminCarListDelete(String carCode) {
 		Connection conn = getConnection();
 		int result = adminDao.adminCarListDelete(conn, carCode);
 		if(result>0)
 			commit(conn);
 		else 
 			rollback(conn);
 		close(conn);
 		return result;
	}

	public List<Reservation> selectAllReservation(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Reservation> list = adminDao.selectAllReservation(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectTotalReservationContents() {
		Connection conn = getConnection();
		int totalReservation = adminDao.selectTotalReservationContents(conn);
		close(conn);
		return totalReservation;
	}

	public List<Reservation> searchReservation(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Reservation> list = adminDao.searchReservation(conn, param);
		close(conn);
		return list;
	}

	public int searchReservationCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = adminDao.searchReservationCount(conn, param);
		close(conn);
		return totalContent;
	}

	public List<CarInfo> searchCarInfo(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CarInfo> list = adminDao.searchCarInfo(conn, param);
		close(conn);
		return list;
	}

	public int searchCarInfoCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = adminDao.searchCarInfoCount(conn, param);
		close(conn);
		return totalContent;
	}

	public List<CarList> searchCarList(Map<String, Object> param) {
		Connection conn = getConnection();
		List<CarList> list = adminDao.searchCarList(conn, param);
		close(conn);
		return list;
	}

	public int searchCarListCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = adminDao.searchCarListCount(conn, param);
		close(conn);
		return totalContent;
	}

	public int ajaxMemberPageReservationCount(String memberId) {
		Connection conn = getConnection();
		int myReservation = adminDao.ajaxMemberPageReservationCount(conn, memberId);
		close(conn);
		return myReservation;
	}

	public List<Reservation> selectCarReturn(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Reservation> list = adminDao.selectCarReturn(conn, startRownum, endRownum);
		close(conn);
		return list;
	}

	public int selectCarReturnContents() {
		Connection conn = getConnection();
		int carReturn = adminDao.selectCarReturnContents(conn);
		close(conn);
		return carReturn;
	}

	public Reservation selectOneReservation(String reserNo) {
		Connection conn = getConnection();
		Reservation reservation = adminDao.selectOneReservation(conn, reserNo);
		close(conn);
		return reservation;
	}

	public int deleteReservation(String reserNo) {
 		Connection conn = getConnection();
 		int result = adminDao.deleteReservation(conn, reserNo);
 		if(result>0)
 			commit(conn);
 		else 
 			rollback(conn);
 		close(conn);
 		return result;
	}

	public int updateReturnStatus(Reservation reservation) {
		Connection conn = getConnection();
		int result = adminDao.updateReturnStatus(conn, reservation);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Car> ajaxBestFiveServlet() {
		Connection conn = getConnection();
		List<Car> list = adminDao.ajaxBestFiveServlet(conn);
		close(conn);
		
		return list;
	}
}