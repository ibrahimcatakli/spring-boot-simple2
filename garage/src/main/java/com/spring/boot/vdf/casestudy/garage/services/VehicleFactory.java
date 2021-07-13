package com.spring.boot.vdf.casestudy.garage.services;

import java.util.Optional;

import com.spring.boot.vdf.casestudy.garage.domain.Car;
import com.spring.boot.vdf.casestudy.garage.domain.Jeep;
import com.spring.boot.vdf.casestudy.garage.domain.Truck;
import com.spring.boot.vdf.casestudy.garage.domain.Vehicle;
import com.spring.boot.vdf.casestudy.garage.exception.ResourceNotFoundException;

public class VehicleFactory {
	public static Optional<Vehicle> getVehicle(VehicleType vehicleType, String color, String plate) {

		Vehicle vehicle;
		if (vehicleType == null)
			throw new UnsupportedOperationException("Vehicle Type Cannot be empty or null");

		switch (vehicleType) {
		case CAR:
			vehicle = new Car(plate, color);
			break;
		case JEEP:
			vehicle = new Jeep(plate, color);
			break;
		case TRUCK:
			vehicle = new Truck(plate, color);
			;
			break;
		default:
			throw new ResourceNotFoundException("Car type can not be empty or null");

		}

		return Optional.of(vehicle);

	}
}
