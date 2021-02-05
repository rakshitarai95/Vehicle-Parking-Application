package com.test.request;

import java.util.Date;

public class UnParkVehicleRequest {

	private String vehicleRegistrationStatus;

	private String ticketNumber;

	private Date unParkingDate;

	public String getVehicleRegistrationStatus() {
		return vehicleRegistrationStatus;
	}

	public void setVehicleRegistrationStatus(String vehicleRegistrationStatus) {
		this.vehicleRegistrationStatus = vehicleRegistrationStatus;
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

}
