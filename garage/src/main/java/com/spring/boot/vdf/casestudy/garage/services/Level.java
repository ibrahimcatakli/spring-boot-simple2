package com.spring.boot.vdf.casestudy.garage.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.spring.boot.vdf.casestudy.garage.domain.Slots;
import com.spring.boot.vdf.casestudy.garage.domain.Vehicle;
import com.spring.boot.vdf.casestudy.garage.exception.ResourceNotFoundException;

public class Level {

	private int floor;
	private Slots[] slots;

	public int getFloor() {
		return floor;
	}

	private int availableSlots = 0; 
	private int SLOTS_PER_ROW;

	public Level(int flr, int num_rows, int slots_per_row) {

		floor = flr;
		int SLOTS_PER_ROW = slots_per_row;
		int numberSpots = 0;
		slots = new Slots[num_rows * slots_per_row];

		for (int row = 0; row < num_rows; ++row) {
			for (int slot = 0; slot < slots_per_row; ++slot) {
				VehicleSize sz = VehicleSize.Small;
				slots[numberSpots] = new Slots(this, row, numberSpots, sz);
				numberSpots++;
			}
		}

		availableSlots = numberSpots;
	}


	public boolean parkVehicle(Vehicle vehicle) {

		if (availableSpots() < vehicle.getSlotsNeeded()) {
			return false; 
		}
		if (slots != null && findDuplicate(vehicle.getRegistrationNumber())) {
			throw new UnsupportedOperationException(
					"Duplicate Register Number Detected for " + vehicle.getRegistrationNumber());
		}
		int slotNumber = findAvailableSpots(vehicle);
		if (slotNumber < 0) {
			return false;
		}else if(slotNumber>0){slotNumber++;}
		return parkStartingAtSlot(slotNumber, vehicle);
	}

	private int findAvailableSpots(Vehicle vehicle) {

		int slotsNeeded = vehicle.getSlotsNeeded();
		int lastRow = -1;
		int slotsFound = 0;

		for (int i = 0; i < slots.length; i++) {
			Slots slot = slots[i];
			if (lastRow != slot.getRow()) {
				slotsFound = 0;
				lastRow = slot.getRow();
			}
			if (slot.canFitVehicle(vehicle)) {
				slotsFound++;
			} else {
				slotsFound = 0;
			}
			if (slotsFound == slotsNeeded) {
				return i - (slotsNeeded - 1); 
			}
		}
		return -1;
	}

	
	private boolean parkStartingAtSlot(int slotNumber, Vehicle vehicle) {
		vehicle.clearSpots();

		boolean success = true;

		for (int i = slotNumber; i < slotNumber + vehicle.getSlotsNeeded(); i++) {
			success &= slots[i].park(vehicle);
		}

		availableSlots -= vehicle.getSlotsNeeded();
		return success;
	}

	
	public void slotFreed() {

		availableSlots++;
	}

	public int availableSpots() {

		return availableSlots;
	}

	public void print(List<String> results) {
		int lastRow = -1;
		Set<Vehicle> slotSet = new HashSet<>();
		for (int i = 0; i < slots.length; i++) {
			Slots slot = slots[i];
			if (slot.getRow() != lastRow) {
				lastRow = slot.getRow();
			}
			if (slot.getVehicle() != null) {
				slotSet.add(slot.getVehicle());
			}
		}
		if (!slotSet.isEmpty()) {
			slotSet.stream().sorted(Vehicle::compareTo).forEach(e -> results.add(e.print()));
		}
	}

	public boolean findDuplicate(String licencePlate) {
		return Arrays.stream(slots).filter(e -> e != null && e.getVehicle() != null)
				.anyMatch(e -> e.getVehicle().getRegistrationNumber().equals(licencePlate));
	}

	public Vehicle findVehicleBySlotNumber(int parkingSpotNumber) {
		final List<String> collect = Arrays.stream(slots).filter(e -> e != null && e.getVehicle() != null)
				.map(e -> e.getVehicle().getRegistrationNumber()).collect(Collectors.toList());
		final ArrayList<String> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(collect));

		if (listWithoutDuplicates != null && !listWithoutDuplicates.isEmpty()) {
			return findVehicle(listWithoutDuplicates.get(parkingSpotNumber - 1));
		}
		throw new ResourceNotFoundException("Parking Slot '" + parkingSpotNumber + "' is empty...");
	}

	public Vehicle findVehicle(String licencePlate) {
		final Optional<Slots> first = Arrays.stream(slots).filter(
				e -> e != null && e.getVehicle() != null && e.getVehicle().getRegistrationNumber().equals(licencePlate))
				.findFirst();
		if (first.isPresent() && !first.isEmpty()) {
			return first.get().getVehicle();
		}
		throw new ResourceNotFoundException("Registration Number '" + licencePlate + "' is not found...");
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Slots[] getSlots() {
		return slots;
	}

	public void setSlots(Slots[] slots) {
		this.slots = slots;
	}
}
