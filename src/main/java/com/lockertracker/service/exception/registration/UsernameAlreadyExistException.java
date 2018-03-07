package com.lockertracker.service.exception.registration;

import com.lockertracker.resources.ExceptionMessageIdConsts;

public class UsernameAlreadyExistException extends BaseRegistrationException {
	private static final long serialVersionUID = -8440622164507197367L;

	public UsernameAlreadyExistException() {
		super(ExceptionMessageIdConsts.Registration.USERNAME_ALREADY_TAKEN);
	}

}
