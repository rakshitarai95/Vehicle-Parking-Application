package com.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registration_id", unique = true, nullable = false)
	private Integer registration_id;

	@Column(name = "registration_number", unique = true, nullable = false)
	private String registrationNumber;

	@Column(name = "vechile_color")
	private String vehicleColor;

	@Column(name = "vechile_type")
	private String vehicleType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parking_date", nullable = false)
	private Date parkingDate;

	@Column(name = "parking_status", nullable = false)
	private byte parkingStatus;

	@Column(name = "ticket_number")
	private String ticketNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "un_parking_date", nullable = false)
	private Date unParkingDate;

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

	public Date getParkingDate() {
		return parkingDate;
	}

	public void setParkingDate(Date parkingDate) {
		this.parkingDate = parkingDate;
	}

	public byte getParkingStatus() {
		return parkingStatus;
	}

	public void setParkingStatus(byte parkingStatus) {
		this.parkingStatus = parkingStatus;
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

	public Integer getRegistration_id() {
		return registration_id;
	}

	public void setRegistration_id(Integer registration_id) {
		this.registration_id = registration_id;
	}

}
