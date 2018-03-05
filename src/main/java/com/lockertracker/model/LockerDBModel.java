package com.lockertracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lockers")
public class LockerDBModel {

	@GeneratedValue
	@Id
	private short id;

	@Column(nullable = false)
	private Date createdAt;

	private boolean rented;
	private long rentedByEmployeeId;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

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
