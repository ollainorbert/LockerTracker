package com.lockertracker.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.lockertracker.converter.LockerModelConverter;
import com.lockertracker.interceptor.RequestLogInterceptor;
import com.lockertracker.resources.PageAttributeConsts;
import com.lockertracker.resources.RoutingConsts;
import com.lockertracker.resources.ViewConsts;

@Configuration
public class CustomWebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestLogInterceptor())
				.addPathPatterns(PageAttributeConsts.REQUEST_LOG_INTERCEPTOR_PATTERN);
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController(RoutingConsts.HOME).setViewName(ViewConsts.HOME_PATH);
		registry.addViewController(RoutingConsts.LOGIN).setViewName(ViewConsts.LOGIN_PATH);
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new LockerModelConverter());
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale defaultLocale = new Locale(PageAttributeConsts.LANGUAGE.DEFAULT_LOCALE);
		localeResolver.setDefaultLocale(defaultLocale);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(PageAttributeConsts.LANGUAGE.LANG);
		return localeChangeInterceptor;
	}

}