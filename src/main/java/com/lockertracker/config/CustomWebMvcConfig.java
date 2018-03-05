package com.lockertracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lockertracker.interceptor.RequestLogInterceptor;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;

@Configuration
public class CustomWebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestLogInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController(RoutingConsts.HOME).setViewName(ViewConsts.HOME);
		registry.addViewController(RoutingConsts.LOGIN).setViewName(ViewConsts.LOGIN);
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}