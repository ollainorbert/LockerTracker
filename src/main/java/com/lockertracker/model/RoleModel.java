package com.lockertracker.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleModel {

	@Id
	@GeneratedValue
	private Long id;

	private String role;

	@ManyToMany(mappedBy = "roles")
	private Set<EmployeeModel> users;// = new HashSet<EmployeeModel>();

	public RoleModel() {
	}

	public RoleModel(String roleName) {
		this.setRole(roleName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<EmployeeModel> getUsers() {
		return users;
	}

	public void setUsers(Set<EmployeeModel> users) {
		this.users = users;
	}
}
