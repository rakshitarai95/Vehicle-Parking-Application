package com.test.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.test.exception.ParkingException;
import com.test.model.Vehicle;
import com.test.repository.VehicleRepository;
import com.test.request.ParkVehicleRequest;
import com.test.request.UnParkVehicleRequest;
import com.test.response.AllVehicleResponse;
import com.test.response.GetVehicleResponse;
import com.test.response.ParkVehicleResponse;
import com.test.response.Response;
import com.test.response.UnParkVehicleResponse;
import com.test.response.VehicleInformation;
import com.test.service.VehicleService;
import com.test.util.Constants;
import com.test.util.DateUtility;

@Service
public class VehcileServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Value("$parkingcapacity")
	private Integer parkingCapacity;

	@Override
	public Response parkVehicle(ParkVehicleRequest parkVehicleRequest, HttpServletRequest request)
			throws ParkingException {
		// TODO Auto-generated method stub
		List<Vehicle> existingData = (List<Vehicle>) vehicleRepository.findAll();
		if (existingData.size() >= 6) {
			throw new ParkingException(Constants.FAILURE_CODE, "Praking space is not available");
		}
		ParkVehicleResponse parkVehicleResponse = new ParkVehicleResponse();
		Vehicle vehicle = new Vehicle();
		vehicle.setRegistrationNumber(parkVehicleRequest.getVehicleRegistrationNumber());
		vehicle.setVehicleColor(parkVehicleRequest.getVehicleColorType());
		vehicle.setVehicleType(parkVehicleRequest.getVehicleType());
		vehicle.setParkingDate(parkVehicleRequest.getParkingDate());
		vehicle.setParkingStatus((byte) 0);
		String ticketNumber = DateUtility.getTicketNumber();
		vehicle.setTicketNumber(ticketNumber);
		vehicleRepository.save(vehicle);
		parkVehicleResponse.setTicketNumber(ticketNumber);
		parkVehicleResponse.setRegistrationNumber(parkVehicleRequest.getVehicleRegistrationNumber());
		parkVehicleResponse.setParkingDate(parkVehicleRequest.getParkingDate());
		parkVehicleResponse.setStatusCode(Constants.SUCCESS_CODE);
		parkVehicleResponse.setMessage("You have parked your vehicle successfully");
		return parkVehicleResponse;
	}

	@Override
	public Response unparkVehicle(UnParkVehicleRequest unParkVehicleRequest, HttpServletRequest request)
			throws ParkingException {
		UnParkVehicleResponse unParkVehicleResponse = new UnParkVehicleResponse();
		Vehicle parkedVehicle = vehicleRepository.findByTicketNumber(unParkVehicleRequest.getTicketNumber());
		if (parkedVehicle == null) {
			throw new ParkingException(Constants.FAILURE_CODE,
					"Please check provided ticket number and try with correct ticekt number.");
		} else {
			parkedVehicle.setParkingStatus((byte) 1);
			parkedVehicle.setUnParkingDate(new Date());
			vehicleRepository.save(parkedVehicle);
			unParkVehicleResponse.setUnParkDate(new Date());
			unParkVehicleResponse.setStatus(Constants.STATUS_SUCCESS);
			unParkVehicleResponse.setStatusCode(Constants.SUCCESS_CODE);
			unParkVehicleResponse.setMessage("Vechicle has been unparked successfully.");

		}
		return unParkVehicleResponse;
	}

	@Override
	public Response getVehcileByRegistrationNumber(String registrationNumber, HttpServletRequest request)
			throws ParkingException {
		// TODO Auto-generated method stub
		GetVehicleResponse vehicleResponse = new GetVehicleResponse();
		System.out.println("Registration number :" + registrationNumber);
		Vehicle parkedVehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
		if (parkedVehicle == null) {
			throw new ParkingException(Constants.FAILURE_CODE,
					"Please check provided ticket number and try with correct ticekt number.");
		} else {
			vehicleResponse.setParkingDate(parkedVehicle.getParkingDate());
			vehicleResponse.setParkingId(parkedVehicle.getRegistration_id());
			vehicleResponse.setRegistrationNumber(parkedVehicle.getRegistrationNumber());
			vehicleResponse.setTicketNumber(parkedVehicle.getTicketNumber());
			vehicleResponse.setUnParkingDate(parkedVehicle.getUnParkingDate());
			vehicleResponse.setVehicleColor(parkedVehicle.getVehicleColor());
			vehicleResponse.setVehicleType(parkedVehicle.getVehicleType());
			vehicleResponse.setStatus(Constants.STATUS_SUCCESS);
			vehicleResponse.setStatusCode(Constants.SUCCESS_CODE);
			vehicleResponse.setMessage("Vehicle details has been populated successfully");

		}
		return vehicleResponse;
	}

	@Override
	public Response getAllVehicle(String color, String type, Date fromTime, Date toTime, HttpServletRequest request)
			throws ParkingException {
		AllVehicleResponse allVehicleResponse = new AllVehicleResponse();
		List<VehicleInformation> vehicleList = new ArrayList<VehicleInformation>();
		List<Vehicle> parkedVehicle = null;
		if (color != null) {
			parkedVehicle = vehicleRepository.findByVehicleColor(color);
		} else if (type != null) {
			parkedVehicle = vehicleRepository.findByVehicleType(type);
		} else if (fromTime != null && toTime != null) {
			return setVehicleResponseBetweenToDates(fromTime, toTime, allVehicleResponse, vehicleList);
		}
		if (parkedVehicle != null && parkedVehicle.isEmpty()) {
			throw new ParkingException(Constants.FAILURE_CODE, "No vehicle found");
		} else {
			setAllVehicleResponse(allVehicleResponse, vehicleList, parkedVehicle);

		}
		setStatusCodeandResposne(allVehicleResponse);
		return allVehicleResponse;

	}

	private Response setVehicleResponseBetweenToDates(Date fromTime, Date toTime, AllVehicleResponse allVehicleResponse,
			List<VehicleInformation> vehicleList) {
		List<Vehicle> allVehicleDetails = (List<Vehicle>) vehicleRepository.findAll();
		for (Vehicle vehicle : allVehicleDetails) {
			if (vehicle.getParkingDate().compareTo(fromTime) >= 0 && vehicle.getParkingDate().compareTo(toTime) <= 0) {
				AddVehicleDetailstoList(vehicleList, vehicle);
			}
		}
		allVehicleResponse.setVehicle(vehicleList);
		setStatusCodeandResposne(allVehicleResponse);
		return allVehicleResponse;
	}

	private void AddVehicleDetailstoList(List<VehicleInformation> vehicleList, Vehicle vehicle) {
		VehicleInformation vehicleInformation = new VehicleInformation();
		vehicleInformation.setParkingDate(vehicle.getParkingDate().toString());
		vehicleInformation.setParkingId(vehicle.getRegistration_id());
		vehicleInformation.setRegistrationNumber(vehicle.getRegistrationNumber());
		vehicleInformation.setTicketNumber(vehicle.getTicketNumber());
		vehicleInformation.setUnParkingDate(vehicle.getUnParkingDate());
		vehicleInformation.setVehicleColor(vehicle.getVehicleColor());
		vehicleInformation.setVehicleType(vehicle.getVehicleType());
		vehicleList.add(vehicleInformation);
	}

	private void setStatusCodeandResposne(AllVehicleResponse allVehicleResponse) {
		allVehicleResponse.setStatus(Constants.STATUS_SUCCESS);
		allVehicleResponse.setStatusCode(Constants.SUCCESS_CODE);
		allVehicleResponse.setMessage("All the vehicle list has been populated based on given condition");
	}

	private void setAllVehicleResponse(AllVehicleResponse allVehicleResponse, List<VehicleInformation> vehicleList,
			List<Vehicle> parkedVehicle) {
		for (Vehicle vehicle : parkedVehicle) {
			AddVehicleDetailstoList(vehicleList, vehicle);
		}
		allVehicleResponse.setVehicle(vehicleList);

	}

}