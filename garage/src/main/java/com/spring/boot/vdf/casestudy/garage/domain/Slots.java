package com.spring.boot.vdf.casestudy.garage.domain;

import java.util.Objects;

import com.spring.boot.vdf.casestudy.garage.services.Level;
import com.spring.boot.vdf.casestudy.garage.services.VehicleSize;

public class Slots {

	private Vehicle vehicle;

	private VehicleSize spotSize;
	private Integer row;
	private Integer spotNumber;
	private Level level;

	public Slots(Level lvl, int r, int n, VehicleSize sz) {
		level = lvl;
		row = r;
		spotNumber = n;
		spotSize = sz;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	public Integer getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(Integer spotNumber) {
		this.spotNumber = spotNumber;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean canFitVehicle(Vehicle vehicle) {
		return isAvailable() && vehicle.canFitInSpot(this);
	}

	public boolean park(Vehicle v) {
		if (!canFitVehicle(v)) {
			return false;
		}
		vehicle = v;
		vehicle.parkInSlot(this);
		return true;
	}

	public void removeVehicle() {
		level.slotFreed();
		vehicle = null;

	}

	public void print() {
		if (vehicle != null) {
			vehicle.print();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Slots that = (Slots) o;
		return row == that.row && spotNumber == that.spotNumber && vehicle.equals(that.vehicle)
				&& spotSize == that.spotSize && level.getFloor() == that.level.getFloor();
	}

	@Override
	public int hashCode() {
		return Objects.hash(spotSize, spotNumber, level.getFloor());
	}

}