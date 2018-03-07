package com.lockertracker.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.model.exception.BaseRegistrationValidationException;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.EmployeeService;
import com.lockertracker.service.exception.registration.BaseRegistrationException;

@Controller
public class RegistrationController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(RoutingConsts.REGISTRATION)
	public ModelAndView registration(Model model, Principal principal) {
		if (principal != null) {
			logger.error("User already logined, but tried to reach the registration page.");
			return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.HOME_NAME));
		}

		ModelAndView modelAndView = new ModelAndView(ViewConsts.REGISTRATION);
		modelAndView.addObject(PageAttributeConsts.Registration.USER, new EmployeeDBModel());

		return modelAndView;
	}

	@RequestMapping(value = RoutingConsts.REGISTRATION_REQUEST, method = RequestMethod.POST)
	public ModelAndView registrationRequest(@ModelAttribute EmployeeDBModel employeeModel)
			throws BaseRegistrationValidationException, BaseRegistrationException {
		employeeModel.validate();
		employeeService.checkUsernameDuplicateInDbBy(employeeModel.getUsername());
		employeeService.registerUser(employeeModel);

		return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOGIN_NAME));
	}
}
