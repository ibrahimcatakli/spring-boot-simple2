package com.spring.boot.vdf.casestudy.garage.domain;


public class Result {
  private String message;
  private boolean pStatus=false;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public boolean ispStatus() {
	return pStatus;
}
public void setpStatus(boolean pStatus) {
	this.pStatus = pStatus;
}
  
  
}
