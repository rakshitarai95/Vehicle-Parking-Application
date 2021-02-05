package com.test.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.test.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, BigInteger> {

	Vehicle findByRegistrationNumber(String registrationNumber);

	Vehicle findByTicketNumber(String ticketNumber);

	List<Vehicle> findByVehicleColor(String ticketNumber);

	List<Vehicle> findByVehicleType(String vehicleType);

}
