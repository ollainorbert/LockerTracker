package com.lockertracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
public abstract class BaseDBModel {

	@GeneratedValue
	@Id
	private long id;

	@Column(updatable = false, nullable = false)
	private Date createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	private void prePersist() {
		this.setCreatedAt(new Date());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
