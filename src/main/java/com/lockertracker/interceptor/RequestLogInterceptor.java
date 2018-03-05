package com.lockertracker.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestLogInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String START_TIME_ID = "startTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		request.setAttribute(START_TIME_ID, System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		if (logger.isInfoEnabled()) {
			long endTime = System.currentTimeMillis();
			long startTime = (Long) request.getAttribute(START_TIME_ID);
			long elapsedTime = endTime - startTime;
			logger.info("Request method: " + request.getMethod());
			logger.info("Request URL: " + request.getRequestURL().toString());
			logger.info(String.format("%s: %d %s", "Elapsed Time between handle and exit", elapsedTime, "ms"));
		}
	}
}
