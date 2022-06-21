package com.kyobong.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
	
	@Setter(AccessLevel.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dt", nullable = false, updatable = false, columnDefinition = "DATETIME")	
	protected Date createDateTime;

	@Setter(AccessLevel.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dt", nullable = true, updatable = false, columnDefinition = "DATETIME")
	protected Date modifiedDateTime;

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}		

	
	@PrePersist
	public void prePersist() {
		this.createDateTime = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.modifiedDateTime = new Date();
	}

}
