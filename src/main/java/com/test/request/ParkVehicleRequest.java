package com.test.request;

import java.util.Date;

public class ParkVehicleRequest {

	private String vehicleRegistrationNumber;

	private String vehicleColorType;

	private String vehicleType;

	private Date parkingDate;

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public String getVehicleColorType() {
		return vehicleColorType;
	}

	public void setVehicleColorType(String vehicleColorType) {
		this.vehicleColorType = vehicleColorType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Date getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(Date parkingDate) {
		this.parkingDate = parkingDate;
	}

}
