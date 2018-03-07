package com.lockertracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.lockertracker.model.exception.BaseRegistrationValidationException;
import com.lockertracker.model.exception.PasswordFormatException;
import com.lockertracker.model.exception.UserNameFormatException;

@Entity
@Table(name = "Employees")
public class EmployeeDBModel extends BaseDBModel {
	@Column(unique = true, nullable = false, length = MAX_NAME_LENGTH)
	@Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
	private String username;

	@Column(nullable = false, length = MAX_PASSWORD_LENGTH)
	@Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
	private String password;

	@Transient
	private String passwordAgain;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleDBModel> roles;

	public static final int MIN_NAME_LENGTH = 20;
	public static final int MAX_NAME_LENGTH = 20;
	public static final int MIN_PASSWORD_LENGTH = 20;
	public static final int MAX_PASSWORD_LENGTH = 20;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public Set<RoleDBModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDBModel> roles) {
		this.roles = roles;
	}

	public void addRoles(String roleName) {
		this.addRole(new RoleDBModel(roleName));
	}

	public void addRoles(RoleDBModel roleModel) {
		this.addRole(roleModel);
	}

	private void addRole(RoleDBModel roleModel) {
		if ((this.roles == null) || (this.roles.isEmpty())) {
			this.setRoles(new HashSet<>());
		}

		this.roles.add(roleModel);
	}

	@Override
	public void validate() throws BaseRegistrationValidationException {
		if ((StringUtils.isBlank(this.getUsername()) || this.getUsername().length() > MAX_NAME_LENGTH)) {
			throw new UserNameFormatException();
		}

		if (StringUtils.isBlank(this.getPassword()) || !this.getPassword().equals(this.getPasswordAgain())
				|| this.getPassword().length() > MAX_PASSWORD_LENGTH) {
			throw new PasswordFormatException();
		}
	}

}
