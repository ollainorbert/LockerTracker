package com.lockertracker.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lockertracker.model.EmployeeDBModel;
import com.lockertracker.model.RoleDBModel;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -8315738959347170674L;

	private EmployeeDBModel employeeModel;

	public UserDetailsImpl(EmployeeDBModel employeeModel) {
		this.employeeModel = employeeModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<RoleDBModel> roles = employeeModel.getRoles();
		for (RoleDBModel role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return employeeModel.getPassword();
	}

	@Override
	public String getUsername() {
		return employeeModel.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
