package com.spring.boot.vdf.casestudy.garage.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.spring.boot.vdf.casestudy.garage.services.VehicleSize;

public abstract class Vehicle implements Comparable<Vehicle> {
  
  protected int slotsNeeded;
  protected VehicleSize size;
  protected String registrationNumber;  
  protected String color;
  protected String ticketNumber;

  protected ArrayList<Slots> slots = new ArrayList<>(); 
  public int getSlotsNeeded() {
    return slotsNeeded;
  }

  public VehicleSize getSize() {
    return size;
  }

 
  public void parkInSlot(Slots spot) {
    slots.add(spot);
  }

  
  public void clearSpots() {

    for (int i = 0; i < slots.size(); i++) {
      slots.get(i).removeVehicle();
    }
    slots.clear();
  }
  public List<Integer> getSlots(){
    return this.slots.stream().map(parkingSpot -> parkingSpot.getSpotNumber()).collect(
        Collectors.toList());
  }

  public List<Integer> getSpotsIncrised(){
    return this.slots.stream().map(parkingSpot -> parkingSpot.getSpotNumber()+1).collect(
        Collectors.toList());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(" ");
    sb.append(registrationNumber).append(' ');
    sb.append(color).append(' ');
    sb.append(getSpotsIncrised());
    return sb.toString();
  }
  //this need to be implement in subclass
  public abstract boolean canFitInSpot(Slots slots);
  public abstract String print();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    /*if (o == null || getClass() != o.getClass()) {
      return false;
    }*/
    Vehicle vehicle = (Vehicle) o;
    return slotsNeeded == vehicle.slotsNeeded && size == vehicle.size && registrationNumber
        .equals(vehicle.registrationNumber) && color.equals(vehicle.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(slotsNeeded, size, registrationNumber, color);
  }

  @Override
  public int compareTo(Vehicle o) {
    return this.slots.get(0).getSpotNumber().compareTo(o.slots.get(0).getSpotNumber());
  }

public String getRegistrationNumber() {
	return registrationNumber;
}

public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}



public void setSlotsNeeded(int slotsNeeded) {
	this.slotsNeeded = slotsNeeded;
}

public String getTicketNumber() {
	return ticketNumber;
}

public void setTicketNumber(String ticketNumber) {
	this.ticketNumber = ticketNumber;
}
  
  
  
}