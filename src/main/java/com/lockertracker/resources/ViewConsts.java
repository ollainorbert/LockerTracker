package com.lockertracker.resources;

public class ViewConsts {

	public static final String HOME = "index";
	public static final String LOGIN = "auth/login";
	public static final String LOCKERS = "lockers";
	public static final String REGISTRATION = "registration";

	public static final String ViewWithRedirect(final String viewName) {
		return "redirect:" + viewName;
	}

}
