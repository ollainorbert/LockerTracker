package com.lockertracker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleDBModel extends BaseDBModel {

	private String role;

	@ManyToMany(mappedBy = "roles")
	private Set<EmployeeDBModel> users;

	public RoleDBModel() {
	}

	public RoleDBModel(String roleName) {
		this.setRole(roleName);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<EmployeeDBModel> getUsers() {
		return users;
	}

	public void setUsers(Set<EmployeeDBModel> users) {
		this.users = users;
	}

}
