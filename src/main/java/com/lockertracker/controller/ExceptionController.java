package com.lockertracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.model.exception.BaseRegistrationValidationException;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.ExceptionHandlerService;
import com.lockertracker.service.exception.locker.BaseLockerException;
import com.lockertracker.service.exception.registration.BaseRegistrationException;

@ControllerAdvice
public class ExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ExceptionHandlerService exceptionHandlerService;

	@Autowired
	public void setExceptionHandlerService(ExceptionHandlerService exceptionHandlerService) {
		this.exceptionHandlerService = exceptionHandlerService;
	}

	@ExceptionHandler(value = BaseLockerException.class)
	public ModelAndView baseLockerHandler(BaseLockerException baseLockerException) {
		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));

		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG,
				exceptionHandlerService.getMessageFrom(baseLockerException));

		return modelAndView;
	}

	@ExceptionHandler(value = BaseRegistrationValidationException.class)
	public ModelAndView baseRegistrationValidationHandler(
			BaseRegistrationValidationException baseRegistrationValidationException) {
		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.REGISTRATION));

		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG,
				exceptionHandlerService.getMessageFrom(baseRegistrationValidationException));

		return modelAndView;
	}

	@ExceptionHandler(value = BaseRegistrationException.class)
	public ModelAndView baseRegistrationHandler(BaseRegistrationException baseRegistrationException) {
		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.REGISTRATION));

		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG,
				exceptionHandlerService.getMessageFrom(baseRegistrationException));

		return modelAndView;
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ModelAndView runTimeExceptionHandler(RuntimeException exception) {
		String throwedBaseClassName = exception.getCause().getClass().getSuperclass().getName();
		logger.error("throwedBaseClassName: " + throwedBaseClassName);

		Throwable throwedExceptionCause = exception.getCause();

		if (throwedBaseClassName.equals(BaseLockerException.class.getName())) {
			return baseLockerHandler((BaseLockerException) throwedExceptionCause);
		} else if (throwedBaseClassName.equals(BaseRegistrationValidationException.class.getName())) {
			return baseRegistrationValidationHandler((BaseRegistrationValidationException) throwedExceptionCause);
		} else {
			return anyRandomExceptionHandler(exception);
		}
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView anyRandomExceptionHandler(Exception exception) {
		logger.error("anyRandomExceptionHandler: " + exception.toString());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, exception.getMessage());

		return modelAndView;
	}

}
