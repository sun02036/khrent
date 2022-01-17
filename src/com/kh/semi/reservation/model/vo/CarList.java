package com.kh.semi.reservation.model.vo;

import java.io.Serializable;

public class CarList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String carCode;
	private String carName;
	private String releaseYear;
	private String carOption;
	private int price;
	private String numberPlate;
	public CarList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarList(String carCode, String carName, String releaseYear, String carOption, int price,
			String numberPlate) {
		super();
		this.carCode = carCode;
		this.carName = carName;
		this.releaseYear = releaseYear;
		this.carOption = carOption;
		this.price = price;
		this.numberPlate = numberPlate;
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
	public String getCarOption() {
		return carOption;
	}
	public void setCarOption(String carOption) {
		this.carOption = carOption;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CarList [carCode=" + carCode + ", carName=" + carName + ", releaseYear=" + releaseYear + ", carOption="
				+ carOption + ", price=" + price + ", numberPlate=" + numberPlate + "]";
	}
	
	
	
	
	
	
}