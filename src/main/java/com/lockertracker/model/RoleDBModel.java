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
	private Set<UserDBModel> users;

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

	public Set<UserDBModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDBModel> users) {
		this.users = users;
	}

}
