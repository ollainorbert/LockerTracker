package com.lockertracker.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.model.UserDBModel;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.UserService;
import com.lockertracker.service.exception.registration.BaseRegistrationException;

@Controller
public class RegistrationController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(RoutingConsts.REGISTRATION)
	public ModelAndView registration(
			@ModelAttribute(PageAttributeConsts.Registration.USER) UserDBModel employeeDBModel,
			Principal principal) {
		if (principal != null) {
			logger.error("User already logined, but tried to reach the registration page.");
			return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.HOME_NAME));
		}

		return new ModelAndView(ViewConsts.REGISTRATION);
	}

	@RequestMapping(value = RoutingConsts.REGISTRATION_REQUEST, method = RequestMethod.POST)
	public ModelAndView registrationRequest(@ModelAttribute UserDBModel employeeModel)
			throws BaseRegistrationException {
		userService.checkTheTwoPasswordThatMustMatch(employeeModel);
		userService.checkUsernameDuplicateInDbBy(employeeModel.getUsername());
		userService.registerUser(employeeModel);

		return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOGIN_NAME));
	}
}
