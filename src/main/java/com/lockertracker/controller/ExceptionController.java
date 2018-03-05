package com.lockertracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.exception.locker.BaseLockerException;

@ControllerAdvice
public class ExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = BaseLockerException.class)
	public ModelAndView baseLockerHandler(BaseLockerException exception) {
		logger.error("baseLockerHandler: " + exception.getMessage());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, exception.getMessage());

		return modelAndView;
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView anyExceptionHandler(Exception exception) {
		logger.error("anyExceptionHandler: " + exception.toString());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, exception.getMessage());

		return modelAndView;
	}
}
