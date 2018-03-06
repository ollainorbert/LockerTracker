package com.lockertracker.model.exception;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class PasswordFormatException extends BaseRegistrationValidationException {
	private static final long serialVersionUID = 2487074329262334024L;

	public PasswordFormatException() {
		super(ExceptionMessageIdConsts.PASSWORD_FORMAT);
	}

}
