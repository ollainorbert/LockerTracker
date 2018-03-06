package com.lockertracker.service;

import com.lockertracker.model.EmployeeDBModel;

public interface EmployeeService {

	public EmployeeDBModel findByUsername(String username);

	public EmployeeDBModel registerUser(EmployeeDBModel employeeModel);
}
