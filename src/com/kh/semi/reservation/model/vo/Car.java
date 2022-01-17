package com.kh.semi.reservation.model.vo;

import java.io.Serializable;

public class Car implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String carCode;
	private String carName;
	private String releaseYear;
	private String maker;
	private String fuel;
	private String carSize;
	private String carOption;
	private String img;
	private String numberPlate;
	private int price;
	private int assessCnt;
	private int avgScore;
	private int reservCnt;
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Car(String carCode, String carName, String releaseYear, String maker, String fuel, String carSize,
			String carOption, String img, String numberPlate, int price, int assessCnt, int avgScore, int reservCnt) {
		super();
		this.carCode = carCode;
		this.carName = carName;
		this.releaseYear = releaseYear;
		this.maker = maker;
		this.fuel = fuel;
		this.carSize = carSize;
		this.carOption = carOption;
		this.img = img;
		this.numberPlate = numberPlate;
		this.price = price;
		this.assessCnt = assessCnt;
		this.avgScore = avgScore;
		this.reservCnt = reservCnt;
	}
	
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
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
	public String getCarOption() {
		return carOption;
	}
	public void setCarOption(String carOption) {
		this.carOption = carOption;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
		return "Car [carCode=" + carCode + ", carName=" + carName + ", releaseYear=" + releaseYear + ", maker=" + maker
				+ ", fuel=" + fuel + ", carSize=" + carSize + ", carOption=" + carOption + ", img=" + img
				+ ", numberPlate=" + numberPlate + ", price=" + price + ", assessCnt=" + assessCnt + ", avgScore="
				+ avgScore + ", reservCnt=" + reservCnt + "]";
	}
	
}
