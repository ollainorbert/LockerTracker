package com.lockertracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.repository.EmployeeRepository;
import com.lockertracker.security.UserDetailsImpl;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private EmployeeRepository employeeRepo;

	@Autowired
	public void setEmployeeRepo(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeDBModel employeeModel = employeeRepo.findByUsername(username);
		if (employeeModel == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(employeeModel);
	}
}
