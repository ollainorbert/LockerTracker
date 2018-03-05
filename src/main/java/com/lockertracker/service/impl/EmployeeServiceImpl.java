package com.lockertracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lockertracker.model.EmployeeModel;
import com.lockertracker.model.RoleModel;
import com.lockertracker.repository.EmployeeRepository;
import com.lockertracker.repository.RoleRepository;
import com.lockertracker.resources.RoleConsts;
import com.lockertracker.security.UserDetailsImpl;
import com.lockertracker.service.EmployeeService;

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
	public EmployeeModel findByUsername(String username) {
		return employeeRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeModel employeeModel = this.findByUsername(username);
		if (employeeModel == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(employeeModel);
	}

	@Override
	public EmployeeModel registerUser(EmployeeModel employeeModel) {
		logger.info("New employee!");
		logger.debug(employeeModel.getUsername());
		logger.debug(employeeModel.getPassword());

		RoleModel roleModel = roleRepo.findByRole(RoleConsts.USER);
		if (roleModel != null) {
			employeeModel.addRoles(roleModel);
		} else {
			employeeModel.addRoles(RoleConsts.USER);
		}

		return employeeRepo.save(employeeModel);
	}

}
