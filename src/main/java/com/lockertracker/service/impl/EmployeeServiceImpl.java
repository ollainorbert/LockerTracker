package com.lockertracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.model.RoleDBModel;
import com.lockertracker.repository.EmployeeRepository;
import com.lockertracker.repository.RoleRepository;
import com.lockertracker.resources.RoleConsts;
import com.lockertracker.security.UserDetailsImpl;
import com.lockertracker.service.EmployeeService;
import com.lockertracker.service.exception.registration.UsernameAlreadyExistException;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EmployeeRepository employeeRepo;
	private RoleRepository roleRepo;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepo, RoleRepository roleRepo) {
		this.employeeRepo = employeeRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public EmployeeDBModel findByUsername(String username) {
		return employeeRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeDBModel employeeModel = this.findByUsername(username);
		if (employeeModel == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(employeeModel);
	}

	@Override
	public EmployeeDBModel registerUser(EmployeeDBModel employeeModel) {
		logger.info("New employee!");
		logger.debug(employeeModel.getUsername());
		logger.debug(employeeModel.getPassword());

		RoleDBModel roleModel = roleRepo.findByRole(RoleConsts.USER);
		if (roleModel != null) {
			employeeModel.addRoles(roleModel);
		} else {
			employeeModel.addRoles(RoleConsts.USER);
		}

		return employeeRepo.save(employeeModel);
	}

	@Override
	public void checkUsernameDuplicateInDbBy(String username) throws UsernameAlreadyExistException {
		logger.info("input username: " + username);
		EmployeeDBModel employeeDBModel = employeeRepo.findByUsername(username);
		if (employeeDBModel != null) {
			throw new UsernameAlreadyExistException();
		}
	}

}
