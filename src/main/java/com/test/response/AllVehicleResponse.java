package com.test.response;

import java.util.List;

public class AllVehicleResponse extends Response {

	private List<VehicleInformation> vehicle;

	public List<VehicleInformation> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<VehicleInformation> vehicle) {
		this.vehicle = vehicle;
	}

	
}
