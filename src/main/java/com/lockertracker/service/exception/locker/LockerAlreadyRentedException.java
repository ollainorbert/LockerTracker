package com.lockertracker.service.exception.locker;

public class LockerAlreadyRentedException extends BaseLockerException {
	private static final long serialVersionUID = -6979160215111971033L;

	public LockerAlreadyRentedException() {
		super(MESSAGE);
	}

	private static final String MESSAGE = "Locker already rented!";
}
