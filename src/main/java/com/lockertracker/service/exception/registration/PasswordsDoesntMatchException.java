package com.lockertracker.service.exception.registration;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class PasswordsDoesntMatchException extends BaseRegistrationException {
	private static final long serialVersionUID = -1569514327591720864L;

	public PasswordsDoesntMatchException() {
		super(ExceptionMessageIdConsts.Registration.PASSWORDS_DOESNT_MATCH);
	}

}
