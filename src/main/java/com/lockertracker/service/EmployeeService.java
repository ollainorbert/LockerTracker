package com.lockertracker.service;

import com.lockertracker.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel findByUsername(String username);

	public EmployeeModel registerUser(EmployeeModel employeeModel);
}
