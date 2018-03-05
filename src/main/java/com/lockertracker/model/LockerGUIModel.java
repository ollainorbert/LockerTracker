package com.lockertracker.model;

public class LockerGUIModel {
	private short id;
	private boolean rented;
	private boolean rentedByLoginedUser;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public boolean isRentedByLoginedUser() {
		return rentedByLoginedUser;
	}

	public void setRentedByLoginedUser(boolean rentedByYou) {
		this.rentedByLoginedUser = rentedByYou;
	}

}
