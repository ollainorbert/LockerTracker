package com.lockertracker.base;

public class BaseExceptionWithLocalization extends Exception {
	private static final long serialVersionUID = 5415736471011297610L;

	public BaseExceptionWithLocalization(String msg) {
		super(msg);
	}
}
