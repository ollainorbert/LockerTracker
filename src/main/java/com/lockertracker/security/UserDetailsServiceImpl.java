package com.lockertracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDBModel userDBModel = userRepository.findByUsername(username);
		if (userDBModel == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(userDBModel);
	}
}
