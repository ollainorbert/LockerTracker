package com.lockertracker.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lockertracker.base.BaseExceptionWithLocalization;
import com.lockertracker.service.ExceptionHandlerService;
import com.lockertracker.service.MessageByLocaleService;

@Component
public class ExceptionHandlerServiceImpl implements ExceptionHandlerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private MessageByLocaleService messageByLocaleService;

	@Autowired
	public void setMessageByLocaleService(MessageByLocaleService messageByLocaleService) {
		this.messageByLocaleService = messageByLocaleService;
	}

	@Override
	public String getMessageFrom(BaseExceptionWithLocalization exception) {
		logger.error("BaseExceptionWithLocalization: " + exception.getMessage());
		String exceptionMessageId = exception.getMessage();
		return messageByLocaleService.getMessage(exceptionMessageId);
	}

}
