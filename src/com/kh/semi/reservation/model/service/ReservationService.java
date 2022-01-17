package com.kh.semi.reservation.model.service;

import static com.kh.semi.common.JdbcTemplate.close;
import static com.kh.semi.common.JdbcTemplate.commit;
import static com.kh.semi.common.JdbcTemplate.getConnection;
import static com.kh.semi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.reservation.model.dao.ReservationDao;
import com.kh.semi.reservation.model.vo.Car;
import com.kh.semi.reservation.model.vo.Reservation;

public class ReservationService {

	private ReservationDao reservationDao = new ReservationDao();

	public List<Car> searchCar(String startDate, String endDate) {
		Connection conn = getConnection();
		List<Car> list = reservationDao.searchCar(conn, startDate, endDate);
		close(conn);
		
		return list;
	}

	public Car selectOneCar(String carCode) {
		Connection conn = getConnection();
		Car car = reservationDao.selectOneCar(conn, carCode);
		return car;
	}

	public int insertReservation(Reservation reservation) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = reservationDao.insertReservation(conn, reservation);
			
			commit(conn);
			
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
		
		close(conn);
		return result;
	}

	public List<Car> searchCarSort(String startDate, String endDate, String sortType) {
		Connection conn = getConnection();
		List<Car> list = reservationDao.searchCarSort(conn, startDate, endDate, sortType);
		close(conn);
		
		return list;
	}
	
	public int deleteRservation(String reserNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = reservationDao.deleteRservation(conn, reserNo);
			if(result == 0)
				throw new IllegalArgumentException("해당 예약내역이 존재하지 않습니다." + reserNo);
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
