package com.lockertracker.service.exception.locker;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class UserNotFoundException extends BaseLockerException {
	private static final long serialVersionUID = 4054908328739317699L;

	public UserNotFoundException() {
		super(ExceptionMessageIdConsts.USER_NOT_FOUND);
	}

}
