package com.spring.boot.vdf.casestudy.garage.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.vdf.casestudy.garage.domain.Result;
import com.spring.boot.vdf.casestudy.garage.domain.Vehicle;
import com.spring.boot.vdf.casestudy.garage.domain.VehicleInput;
import com.spring.boot.vdf.casestudy.garage.exception.ResourceNotFoundException;
import com.spring.boot.vdf.casestudy.garage.services.ParkingService;
import com.spring.boot.vdf.casestudy.garage.services.VehicleFactory;
import com.spring.boot.vdf.casestudy.garage.services.Level;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GarageController {
	private final ParkingService service;
	private final Level level;

	@PostMapping
	@ExceptionHandler(value = { ResourceNotFoundException.class, ResourceNotFoundException.class })
	public @ResponseBody ResponseEntity<String> park(@RequestBody VehicleInput vehicle) {
		final Vehicle vehicleEntity = VehicleFactory.getVehicle(vehicle.getVehicleType(),
				vehicle.getColor().toUpperCase(), vehicle.getRegistrationNumber().toUpperCase()).get();
		final Result processResult = service.parkVehicle(vehicleEntity);
		return ResponseEntity.ok(processResult.getMessage());
	}

	@DeleteMapping(path = "/leave/{parkingSlotNumber}")
	public ResponseEntity<?> leaveByParkingSpot(@PathVariable Integer parkingSlotNumber) {
		service.unParkVehicle(level.findVehicleBySlotNumber(parkingSlotNumber));
		return ResponseEntity.noContent().build();
	}
 
	@GetMapping("/status")
	public ResponseEntity<List> status() {
		final List<String> results = new ArrayList<>();
		service.print(results);
		return ResponseEntity.ok(results);
	}

}
