package com.lockertracker.service.exception.locker;

public abstract class BaseLockerException extends Exception {

	private static final long serialVersionUID = -2765558095965955147L;

	public BaseLockerException(String msg) {
		super(msg);
	}
}
