package com.lockertracker.service.exception.locker;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class DatabaseProblemException extends BaseLockerException {

	private static final long serialVersionUID = 2269502939489872061L;

	public DatabaseProblemException() {
		super(ExceptionMessageIdConsts.DATABASE_PROBLEM);
	}

}
