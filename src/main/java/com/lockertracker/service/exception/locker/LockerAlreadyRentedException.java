package com.lockertracker.service.exception.locker;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class LockerAlreadyRentedException extends BaseLockerException {
	private static final long serialVersionUID = -6979160215111971033L;

	public LockerAlreadyRentedException() {
		super(ExceptionMessageIdConsts.LOCKER_ALREADY_RENTED);
	}

}
