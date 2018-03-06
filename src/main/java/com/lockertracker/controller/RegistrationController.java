package com.lockertracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.EmployeeService;

@Controller
public class RegistrationController {
	private EmployeeService employeeService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(RoutingConsts.REGISTRATION)
	public String registration(Model model) {
		model.addAttribute(PageAttributeConsts.Registration.USER, new EmployeeDBModel());
		return ViewConsts.REGISTRATION;
	}

	@RequestMapping(value = RoutingConsts.REGISTRATION_REQUEST, method = RequestMethod.POST)
	public String registrationRequest(@ModelAttribute EmployeeDBModel employeeModel) {
		employeeService.registerUser(employeeModel);
		return ViewConsts.ViewWithRedirect(ViewConsts.LOGIN);
	}
}
