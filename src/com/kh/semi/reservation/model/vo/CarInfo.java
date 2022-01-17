package com.kh.semi.reservation.model.vo;

import java.io.Serializable;

public class CarInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int carInfoNo;
	private String carName;
	private String maker;
	private String fuel;
	private String carSize;
	private String img;
	private int assessCnt;
	private int avgScore;
	private int reservCnt;
	
	public CarInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarInfo(int carInfoNo, String carName, String maker, String fuel, String carSize, String img, int assessCnt,
			int avgScore, int reservCnt) {
		super();
		this.carInfoNo = carInfoNo;
		this.carName = carName;
		this.maker = maker;
		this.fuel = fuel;
		this.carSize = carSize;
		this.img = img;
		this.assessCnt = assessCnt;
		this.avgScore = avgScore;
		this.reservCnt = reservCnt;
	}

	public int getCarInfoNo() {
		return carInfoNo;
	}

	public void setCarInfoNo(int carInfoNo) {
		this.carInfoNo = carInfoNo;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getCarSize() {
		return carSize;
	}

	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getAssessCnt() {
		return assessCnt;
	}

	public void setAssessCnt(int assessCnt) {
		this.assessCnt = assessCnt;
	}

	public int getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}

	public int getReservCnt() {
		return reservCnt;
	}

	public void setReservCnt(int reservCnt) {
		this.reservCnt = reservCnt;
	}

	@Override
	public String toString() {
		return "CarInfo [carInfoNo=" + carInfoNo + ", carName=" + carName + ", maker=" + maker + ", fuel=" + fuel
				+ ", carSize=" + carSize + ", img=" + img + ", assessCnt=" + assessCnt + ", avgScore=" + avgScore
				+ ", reservCnt=" + reservCnt + "]";
	}

	
}