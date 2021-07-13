package com.spring.boot.vdf.casestudy.garage.domain;

import com.spring.boot.vdf.casestudy.garage.services.VehicleSize;

public class Truck extends Vehicle {
  
  public Truck(String registrationNumber,String color) {
    super();
    slotsNeeded = 4;
    size = VehicleSize.Large;
    this.registrationNumber=registrationNumber;
    this.color=color;
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

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
