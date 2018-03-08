package com.lockertracker.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lockertracker.model.RoleDBModel;
import com.lockertracker.model.UserDBModel;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -8315738959347170674L;

	private UserDBModel userDBModel;

	public UserDetailsImpl(UserDBModel userDBModel) {
		this.userDBModel = userDBModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<RoleDBModel> roles = userDBModel.getRoles();
		for (RoleDBModel role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return userDBModel.getPassword();
	}

	@Override
	public String getUsername() {
		return userDBModel.getUsername();
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
