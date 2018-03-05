package com.lockertracker.service.exception.locker;

public class LockerAlreadyReleasedException extends BaseLockerException {
	private static final long serialVersionUID = 7909403193944406227L;

	public LockerAlreadyReleasedException() {
		super(MESSAGE);
	}

	private static final String MESSAGE = "Locker already released!";
}
