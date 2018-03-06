package com.lockertracker.model;

public class LockerGUIModel {
	public enum RentedByEnum {
		NOT_RENTED, RENTED_BY_OTHER, RENTED_BY_YOU
	}

	private long id;
	private boolean rented;
	private boolean rentedByLoginedUser;
	private RentedByEnum rentedByEnum;
	private String rentedByString;
	private long rentedByEmployeeId;

	private static final String NOT_RENTED = "Not rented!";
	private static final String RENTED_BY_OTHER = "Rented by Other!";
	private static final String RENTED_BY_YOU = "Rented by You!";

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getRentedByEmployeeId() {
		return rentedByEmployeeId;
	}

	public void setRentedByEmployeeId(long rentedByEmployeeId) {
		this.rentedByEmployeeId = rentedByEmployeeId;
	}

	public void setRentingAttributesBy(long loginedUserID) {
		if (!this.isRented()) {
			this.setRentedByEnum(RentedByEnum.NOT_RENTED);
		} else {
			boolean isRentedByThisUser = (this.getRentedByEmployeeId() == loginedUserID) ? true : false;
			this.setRentedByLoginedUser(isRentedByThisUser);

			RentedByEnum rentedBy = this.isRentedByLoginedUser() ? RentedByEnum.RENTED_BY_YOU
					: RentedByEnum.RENTED_BY_OTHER;
			this.setRentedByEnum(rentedBy);
		}
	}

}
