package com.lockertracker.resources;

public class ExceptionMessageIdConsts {
	public static final String LOCKER_NOT_FOUND = "exception.lockerNotFound";
	public static final String LOCKER_ALREADY_RENTED = "exception.lockerAlreadyRented";
	public static final String LOCKER_ALREADY_RELEASED = "exception.lockerAlreadyReleased";
	public static final String USER_NOT_FOUND = "exception.userNotFound";
	public static final String DATABASE_PROBLEM = "exception.databaseProblem";

	public static final String USERNAME_FORMAT = "exception.registration.usernameFormat";
	public static final String PASSWORD_FORMAT = "exception.registration.passwordFormat";

	public static class Registration {
		public static final String USERNAME_ALREADY_TAKEN = "exception.registration.usernameAlreadyTaken";
		public static final String PASSWORDS_DOESNT_MATCH = "exception.registration.passwordsDoenstMatch";
	}
}
