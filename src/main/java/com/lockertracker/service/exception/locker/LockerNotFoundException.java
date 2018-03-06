package com.lockertracker.service.exception.locker;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class LockerNotFoundException extends BaseLockerException {
	private static final long serialVersionUID = 909335782815990716L;

	public LockerNotFoundException() {
		super(ExceptionMessageIdConsts.LOCKER_NOT_FOUND);
	}

}
