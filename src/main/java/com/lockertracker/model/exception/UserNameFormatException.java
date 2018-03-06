package com.lockertracker.model.exception;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class UserNameFormatException extends BaseRegistrationValidationException {
	private static final long serialVersionUID = 8954197535312575265L;

	public UserNameFormatException() {
		super(ExceptionMessageIdConsts.USERNAME_FORMAT);
	}

}
