package com.lockertracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.lockertracker.resources.RoleConsts;
import com.lockertracker.resources.RoutingConsts;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
		
// IN MEMORY		
//		auth
//			.inMemoryAuthentication()
//					.withUser("user")
//					.password("pass")
//					.roles(ROLE_USER)
//				.and()
//					.withUser("admin")
//					.password("admin")
//					.roles(ROLE_ADMIN);		
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
					.antMatchers("/admin/**").hasRole(RoleConsts.ADMIN)
					.antMatchers(RoutingConsts.REGISTRATION).permitAll()
					.antMatchers(RoutingConsts.REGISTRATION_REQ).permitAll()
					.antMatchers("/h2/**").permitAll()
					.antMatchers(RoutingConsts.LOGIN + "/**").permitAll()
					.anyRequest().authenticated()
				.and()
					.formLogin()
						.loginPage(RoutingConsts.LOGIN)
						.permitAll()
				.and()
					.logout()
						.logoutSuccessUrl(RoutingConsts.LOGIN + "?logout")
						.permitAll();
		
		// H2 DB-HEZ!
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		// H2 DB-HEZ!
	}

}
