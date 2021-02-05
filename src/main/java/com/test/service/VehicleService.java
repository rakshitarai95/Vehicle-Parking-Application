package com.test.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.test.exception.ParkingException;
import com.test.request.ParkVehicleRequest;
import com.test.request.UnParkVehicleRequest;
import com.test.response.Response;

public interface VehicleService {

	Response parkVehicle(ParkVehicleRequest parkVehicleRequest, HttpServletRequest request) throws ParkingException;

	Response unparkVehicle(UnParkVehicleRequest unParkVehicleRequest, HttpServletRequest request)
			throws ParkingException;

	Response getAllVehicle(String color, String Type, Date fromTime, Date toTime, HttpServletRequest request)
			throws ParkingException;

	Response getVehcileByRegistrationNumber(String registrationNumber, HttpServletRequest request)
			throws ParkingException;

}
