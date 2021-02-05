package com.test.response;

import java.util.Date;

public class VehicleInformation {

	private String registrationNumber;

	private String vehicleColor;

	private String vehicleType;

	private String parkingStatus;

	private String parkingDate;

	private String ticketNumber;

	private Date unParkingDate;

	private Integer parkingId;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getParkingStatus() {
		return parkingStatus;
	}

	public void setParkingStatus(String parkingStatus) {
		this.parkingStatus = parkingStatus;
	}

	public String getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(String parkingDate) {
		this.parkingDate = parkingDate;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getUnParkingDate() {
		return unParkingDate;
	}

	public void setUnParkingDate(Date unParkingDate) {
		this.unParkingDate = unParkingDate;
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

}
