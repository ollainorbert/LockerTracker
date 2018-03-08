package com.lockertracker.service.exception.locker;

import com.lockertracker.base.BaseExceptionWithLocalization;

public abstract class BaseLockerException extends BaseExceptionWithLocalization {

	private static final long serialVersionUID = -2765558095965955147L;

	public BaseLockerException(String msgID) {
		super(msgID);
	}

}
