package com.lockertracker.service.exception.locker;

public class DatabaseProblemException extends BaseLockerException {

	private static final long serialVersionUID = 2269502939489872061L;

	public DatabaseProblemException() {
		super(MESSAGE);
	}

	private static final String MESSAGE = "Problem with the locker renting, please try again later";

}
