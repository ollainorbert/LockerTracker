package com.lockertracker.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.lockertracker.model.exception.BaseRegistrationValidationException;
import com.lockertracker.model.exception.PasswordFormatException;
import com.lockertracker.model.exception.UserNameFormatException;

@Entity
@Table(name = "Employees")
public class EmployeeModel extends DBModel {

	// @GeneratedValue
	// @Id
	// private long id;

	@Column(unique = true, nullable = false, length = MAX_NAME_LENGTH)
	private String username;

	@Column(nullable = false, length = MAX_PASSWORD_LENGTH)
	private String password;

	@Column(updatable = false, nullable = false)
	private Date createdAt;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleModel> roles;

	public static final int MAX_NAME_LENGTH = 20;
	public static final int MAX_PASSWORD_LENGTH = 20;

	// public long getId() {
	// return id;
	// }
	//
	// public void setId(long id) {
	// this.id = id;
	// }

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	private void createdAt() {
		this.createdAt = new Date();
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public void addRoles(String roleName) {
		this.addRole(new RoleModel(roleName));
	}

	public void addRoles(RoleModel roleModel) {
		this.addRole(roleModel);
	}

	private void addRole(RoleModel roleModel) {
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

		if ((StringUtils.isBlank(this.getPassword()) || this.getPassword().length() > MAX_PASSWORD_LENGTH)) {
			throw new PasswordFormatException();
		}

	}

}
