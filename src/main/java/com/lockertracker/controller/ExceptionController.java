package com.lockertracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.ViewConsts;
import com.lockertracker.service.MessageByLocaleService;
import com.lockertracker.service.exception.locker.BaseLockerException;

@ControllerAdvice
public class ExceptionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MessageByLocaleService messageByLocaleService;

	@Autowired
	public void setMessageByLocaleService(MessageByLocaleService messageByLocaleService) {
		this.messageByLocaleService = messageByLocaleService;
	}

	@ExceptionHandler(value = BaseLockerException.class)
	public ModelAndView baseLockerHandler(BaseLockerException baseLockerException) {
		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, getMessageFrom(baseLockerException));
		return modelAndView;
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView anyExceptionHandler(Exception exception) {
		logger.error("anyExceptionHandler: " + exception.toString());

		ModelAndView modelAndView = new ModelAndView(ViewConsts.ViewWithRedirect(ViewConsts.LOCKERS));
		modelAndView.addObject(PageAttributeConsts.Locker.RESULT_MSG, exception.getMessage());

		return modelAndView;
	}

	private String getMessageFrom(BaseLockerException baseLockerException) {
		logger.error("baseLockerHandler: " + baseLockerException.getMessage());
		String exceptionMessageId = baseLockerException.getMessage();
		return messageByLocaleService.getMessage(exceptionMessageId);
	}
}
