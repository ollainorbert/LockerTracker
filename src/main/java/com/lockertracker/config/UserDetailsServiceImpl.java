package com.lockertracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.security.UserDetailsImpl;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDBModel employeeModel = userRepository.findByUsername(username);
		if (employeeModel == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(employeeModel);
	}
}
