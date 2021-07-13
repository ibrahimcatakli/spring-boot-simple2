package com.spring.boot.vdf.casestudy.garage.domain;

import com.spring.boot.vdf.casestudy.garage.services.VehicleType;

public class VehicleInput {

	private String registrationNumber;
	private VehicleType vehicleType;
	private String color;

	
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}
