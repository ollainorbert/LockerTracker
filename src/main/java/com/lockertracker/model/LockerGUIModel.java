package com.lockertracker.model;

public class LockerGUIModel {
	public enum RentedByEnum {
		NOT_RENTED, RENTED_BY_OTHER, RENTED_BY_YOU
	}

	private short id;
	private boolean rented;
	private boolean rentedByLoginedUser;
	private RentedByEnum rentedByEnum;
	private String rentedByString;

	private static final String NOT_RENTED = "Not rented!";
	private static final String RENTED_BY_OTHER = "Rented by Other!";
	private static final String RENTED_BY_YOU = "Rented by You!";

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

	public void setRentedByLoginedUser(boolean rentedByLoginedUser) {
		this.rentedByLoginedUser = rentedByLoginedUser;
	}

	public RentedByEnum getRentedByEnum() {
		return rentedByEnum;
	}

	public void setRentedByEnum(RentedByEnum rentedByEnum) {
		String rentedByStringLOCAL = "error";

		switch (rentedByEnum) {

		case NOT_RENTED:
			rentedByStringLOCAL = NOT_RENTED;
			break;

		case RENTED_BY_OTHER:
			rentedByStringLOCAL = RENTED_BY_OTHER;
			break;

		case RENTED_BY_YOU:
			rentedByStringLOCAL = RENTED_BY_YOU;
			break;
		}

		this.setRentedByString(rentedByStringLOCAL);
		this.rentedByEnum = rentedByEnum;
	}

	public String getRentedByString() {
		return rentedByString;
	}

	public void setRentedByString(String rentedByString) {
		this.rentedByString = rentedByString;
	}

}
