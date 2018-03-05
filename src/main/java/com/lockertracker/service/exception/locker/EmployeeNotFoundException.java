package com.lockertracker.service.exception.locker;

public class EmployeeNotFoundException extends BaseLockerException {
	private static final long serialVersionUID = 4054908328739317699L;

	public EmployeeNotFoundException() {
		super(MESSAGE);
	}

	private static final String MESSAGE = "User not found!";

}
