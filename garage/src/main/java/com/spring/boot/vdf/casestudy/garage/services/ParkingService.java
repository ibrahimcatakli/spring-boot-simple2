package com.spring.boot.vdf.casestudy.garage.services;

import java.util.List;

import com.spring.boot.vdf.casestudy.garage.domain.Result;
import com.spring.boot.vdf.casestudy.garage.domain.Vehicle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkingService {

	private final Level level;

	public Result parkVehicle(Vehicle vehicle) {
		final Result processResult = new Result();
		if (level.parkVehicle(vehicle)) {
			final int size = vehicle.getSlots().size();
			String message = "Allocated " + size + (size == 1 ? " slot" : " slots");
			processResult.setpStatus(false);
			processResult.setMessage(message);
		} else {
			throw new RuntimeException("Garage is full.");
		}
		return processResult;
	}

	public void unParkVehicle(Vehicle vehicle) {

		vehicle.clearSpots();
	}

	public void print(List<String> result) {

		level.print(result);
	}
}
