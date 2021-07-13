package com.spring.boot.vdf.casestudy.garage.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum VehicleType {
	@JsonProperty("Car")
	CAR, @JsonProperty("Jeep")
	JEEP, @JsonProperty("Truck")
	TRUCK
}
