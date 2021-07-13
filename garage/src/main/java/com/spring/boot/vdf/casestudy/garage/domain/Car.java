package com.spring.boot.vdf.casestudy.garage.domain;

import com.spring.boot.vdf.casestudy.garage.services.VehicleSize;
import java.util.UUID;

public class Car extends Vehicle {

  
  public Car(String registrationNumber,String color) {
    slotsNeeded = 1;
    size = VehicleSize.Small;
    this.registrationNumber=registrationNumber;
    this.color=color;
    this.ticketNumber = UUID.randomUUID().toString();
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
