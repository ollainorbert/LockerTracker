package com.lockertracker.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Lockers")
public class LockerDBModel extends BaseDBModel {
	private boolean rented;
	private long rentedByEmployeeId;

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public long getRentedByEmployeeId() {
		return rentedByEmployeeId;
	}

	public void setRentedByEmployeeId(long rentedByEmployeeId) {
		this.rentedByEmployeeId = rentedByEmployeeId;
	}

}
