package com.lockertracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lockertracker.model.RoleDBModel;
import com.lockertracker.model.UserDBModel;
import com.lockertracker.repository.RoleRepository;
import com.lockertracker.repository.UserRepository;
import com.lockertracker.resources.RoleConsts;
import com.lockertracker.service.UserService;
import com.lockertracker.service.exception.registration.PasswordsDoesntMatchException;
import com.lockertracker.service.exception.registration.UsernameAlreadyExistException;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void checkTheTwoPasswordThatMustMatch(UserDBModel userDBModel) throws PasswordsDoesntMatchException {
		String password1 = userDBModel.getPassword();
		String password2 = userDBModel.getPasswordAgain();
		if (!password1.equals(password2)) {
			throw new PasswordsDoesntMatchException();
		}
	}

	@Override
	public void checkUsernameDuplicateInDbBy(String username) throws UsernameAlreadyExistException {
		logger.info("input username: " + username);

		UserDBModel userDBModel = userRepository.findByUsername(username);
		if (userDBModel != null) {
			throw new UsernameAlreadyExistException();
		}
	}

	// ezek inkabb legyenek private-k?
	@Override
	public UserDBModel registerUser(UserDBModel userDBModel) {
		logger.info("New user! {}, password: xxxx", userDBModel.getUsername());

		RoleDBModel roleModel = roleRepository.findByRole(RoleConsts.USER);
		if (roleModel != null) {
			userDBModel.addRoles(roleModel);
		} else {
			userDBModel.addRoles(RoleConsts.USER);
		}

		return userRepository.save(userDBModel);
	}

}
