package com.lockertracker.service.exception.locker;

public class LockerNotFoundException extends BaseLockerException {

	private static final long serialVersionUID = 909335782815990716L;

	public LockerNotFoundException() {
		super(MESSAGE);
	}

	private static final String MESSAGE = "Locker not found!";
}
