package com.lockertracker.service;

import com.lockertracker.model.UserDBModel;
import com.lockertracker.service.exception.registration.PasswordsDoesntMatchException;
import com.lockertracker.service.exception.registration.UsernameAlreadyExistException;

public interface UserService {

	public void checkTheTwoPasswordThatMustMatch(UserDBModel employeeDBModel) throws PasswordsDoesntMatchException;

	public void checkUsernameDuplicateInDbBy(String username) throws UsernameAlreadyExistException;

	public UserDBModel registerUser(UserDBModel employeeModel);
}
