package com.test.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.request.ParkVehicleRequest;
import com.test.request.UnParkVehicleRequest;
import com.test.response.AllVehicleResponse;
import com.test.response.GetVehicleResponse;
import com.test.response.ParkVehicleResponse;
import com.test.response.Response;
import com.test.response.UnParkVehicleResponse;
import com.test.service.VehicleService;
import com.test.util.Constants;

@RestController
public class VehicleController extends BaseController {

	@Autowired
	private VehicleService vehicleService;
	

	private static final Logger LOG = Logger.getLogger(VehicleController.class);

	@SuppressWarnings("unchecked")
	@PostMapping("/parkvehicle")
	public ResponseEntity<ParkVehicleResponse> parkVehicle(@RequestBody ParkVehicleRequest parkVehicleRequest,
			HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		Response responseData = null;
		try {
			responseData = vehicleService.parkVehicle(parkVehicleRequest, request);
		} catch (Exception e) {
			e.printStackTrace();
			setResponse(responseData, null, Constants.STATUS_FAILURE, Constants.FAILURE_CODE, e.getMessage());
			return (ResponseEntity<ParkVehicleResponse>) restResponse(responseData, request.getMethod());
		}
		LOG.debug("TOTAL_PPROCESS_TIME=park vehicle:" + (System.currentTimeMillis() - startTime));
		return (ResponseEntity<ParkVehicleResponse>) restResponse(responseData, request.getMethod());

	}

	@SuppressWarnings("unchecked")
	@PutMapping("/unparkvehicle")
	public ResponseEntity<UnParkVehicleResponse> unparkVehicle(@RequestBody UnParkVehicleRequest unParkVehicleRequest,
			HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		Response responseData = null;
		try {
			responseData = vehicleService.unparkVehicle(unParkVehicleRequest, request);
		} catch (Exception e) {
			e.printStackTrace();
			setResponse(responseData, null, Constants.STATUS_FAILURE, Constants.FAILURE_CODE, e.getMessage());
			return (ResponseEntity<UnParkVehicleResponse>) restResponse(responseData, request.getMethod());
		}
		LOG.debug("TOTAL_PPROCESS_TIME=un park vehicle:" + (System.currentTimeMillis() - startTime));
		return (ResponseEntity<UnParkVehicleResponse>) restResponse(responseData, request.getMethod());

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/vehicleinformatio/{registrationNumber}")
	public ResponseEntity<GetVehicleResponse> getVehicle(@PathVariable("registrationNumber") String registrationNumber,
			HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		Response responseData = null;
		try {
			responseData = vehicleService.getVehcileByRegistrationNumber(registrationNumber, request);
		} catch (Exception e) {
			e.printStackTrace();
			setResponse(responseData, null, Constants.STATUS_FAILURE, Constants.FAILURE_CODE, e.getMessage());
			return (ResponseEntity<GetVehicleResponse>) restResponse(responseData, request.getMethod());
		}
		LOG.debug("TOTAL_PPROCESS_TIME=Get vehicle by registration number:" + (System.currentTimeMillis() - startTime));
		return (ResponseEntity<GetVehicleResponse>) restResponse(responseData, request.getMethod());

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/allvehicleInformation")
	public ResponseEntity<AllVehicleResponse> getAllVehicle(
			@RequestParam(value = "vehicleColor", required = false) String vehicleColor,
			@RequestParam(value = "vehicleType", required = false) String vehicleType,
			@RequestParam(value = "EnterfromDateTime", required = false) Date fromTime,
			@RequestParam(value = "EntertoDateTime", required = false) Date toTime, HttpServletRequest request) {
		long startTime = System.currentTimeMillis();
		Response responseData = null;
		try {
			responseData = vehicleService.getAllVehicle(vehicleColor, vehicleType, fromTime, toTime, request);
		} catch (Exception e) {
			e.printStackTrace();
			setResponse(responseData, null, Constants.STATUS_FAILURE, Constants.FAILURE_CODE, e.getMessage());
			return (ResponseEntity<AllVehicleResponse>) restResponse(responseData, request.getMethod());
		}
		LOG.debug("TOTAL_PPROCESS_TIME=Get all vehicle:" + (System.currentTimeMillis() - startTime));
		return (ResponseEntity<AllVehicleResponse>) restResponse(responseData, request.getMethod());

	}

}
