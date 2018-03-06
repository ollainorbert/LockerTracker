package com.lockertracker.service.exception.locker;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class LockerAlreadyReleasedException extends BaseLockerException {
	private static final long serialVersionUID = 7909403193944406227L;

	public LockerAlreadyReleasedException() {
		super(ExceptionMessageIdConsts.LOCKER_ALREADY_RELEASED);
	}

}
