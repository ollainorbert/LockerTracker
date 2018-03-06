package com.lockertracker.service;

import com.lockertracker.base.BaseExceptionWithLocalization;

public interface ExceptionHandlerService {

	public String getMessageFrom(BaseExceptionWithLocalization exception);

}
