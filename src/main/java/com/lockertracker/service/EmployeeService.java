package com.lockertracker.service;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.service.exception.registration.PasswordsDoesntMatchException;
import com.lockertracker.service.exception.registration.UsernameAlreadyExistException;

public interface EmployeeService {

	public void checkTheTwoPasswordThatMustMatch(EmployeeDBModel employeeDBModel) throws PasswordsDoesntMatchException;

	public void checkUsernameDuplicateInDbBy(String username) throws UsernameAlreadyExistException;

	public EmployeeDBModel registerUser(EmployeeDBModel employeeModel);
}
