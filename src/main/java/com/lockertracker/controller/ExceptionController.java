package com.lockertracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.registration.BaseRegistrationException;

@ControllerAdvice
public class ExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = BaseLockerException.class)
	public ModelAndView baseLockerExceptionHandler(BaseLockerException baseLockerException) {

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, baseLockerException.getMessage());

		return modelAndView;
	}

	@ExceptionHandler(value = BaseRegistrationException.class)
	public ModelAndView baseRegistrationExceptionHandler(BaseRegistrationException baseRegistrationException) {

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.REGISTRATION));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, baseRegistrationException.getMessage());

		return modelAndView;
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView anyRandomExceptionHandler(Exception exception) {
		logger.error("anyRandomExceptionHandler: " + exception.toString());
		return new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.HOME_NAME));
	}

}
