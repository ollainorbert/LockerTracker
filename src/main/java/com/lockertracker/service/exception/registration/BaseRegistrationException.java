package com.lockertracker.service.exception.registration;

import com.lockertracker.base.BaseExceptionWithLocalization;

public abstract class BaseRegistrationException extends BaseExceptionWithLocalization {
	private static final long serialVersionUID = 6891892482045203363L;

	public BaseRegistrationException(String msgId) {
		super(msgId);
	}
}
