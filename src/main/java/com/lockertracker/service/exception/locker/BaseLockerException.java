package com.lockertracker.service.exception.locker;

import com.lockertracker.base.BaseExceptionWithLocalization;

public abstract class BaseLockerException extends BaseExceptionWithLocalization {

	private static final long serialVersionUID = -2765558095965955147L;
	// private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public BaseLockerException(String msgID) {
		super(msgID);
	}

	// private MessageByLocaleService messageByLocaleService;

	// @Autowired
	// public void setMessageByLocaleService(MessageByLocaleService
	// messageByLocaleService) {
	// this.messageByLocaleService = messageByLocaleService;
	// }

	// @Override
	// public String getMessage() {
	// MessageByLocaleService messageByLocaleService = new
	// MessageByLocaleServiceImpl();
	//
	// logger.info("super getMessage: " + super.getMessage());
	// // if (messageByLocaleService == null) {
	// // logger.info("MESSAGE BY LOCALE SERVICE IS NULL");
	// // } else {
	// logger.info(messageByLocaleService.toString());
	// // }
	//
	// String msgID = super.getMessage();
	// return messageByLocaleService.getMessage(msgID);
	// }

}
