package com.lockertracker.model.exception;

import com.lockertracker.base.BaseExceptionWithLocalization;

public abstract class BaseRegistrationValidationException extends BaseExceptionWithLocalization {
	private static final long serialVersionUID = 7468535457605550115L;

	public BaseRegistrationValidationException(String msg) {
		super(msg);
	}
}
