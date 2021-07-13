package com.spring.boot.vdf.casestudy.garage.domain;

import com.spring.boot.vdf.casestudy.garage.services.VehicleSize;


public class Jeep extends Vehicle {

  public Jeep(String registrationNumber, String color) {
    slotsNeeded = 2;
    size = VehicleSize.Medium;
    this.registrationNumber = registrationNumber;
    this.color = color;
  }

  public boolean canFitInSpot(Slots slots) {
    return true;
  }

  public String print() {
    return this.toString();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()+super.toString();
  }
}
