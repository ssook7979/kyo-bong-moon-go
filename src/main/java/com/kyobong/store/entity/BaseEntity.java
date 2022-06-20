package com.kyobong.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
	
	@Setter(AccessLevel.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dt", nullable = false, updatable = false, columnDefinition = "DATETIME")	
	protected Date createDateTime;

	@Setter(AccessLevel.NONE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_dt", nullable = false, updatable = false, columnDefinition = "DATETIME")
	protected Date modifiedDateTime;

	@Setter(AccessLevel.NONE)
	@Length(message="길이가 100보다 클 수 없습니다.", max = 100)
	@Column(name = "registrator", length = 100)
	protected String registrator;

	@Setter(AccessLevel.NONE)
	@Length(message="길이가 100보다 클 수 없습니다.", max = 100)
	@Column(name = "modifier", length = 100)
	protected String modifier;

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}		

	public String getRegistrator() {
		return registrator;
	}

	public String getModifier() {
		return modifier;
	}
	
	private String getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetails) principal).getUsername();
	}
	
	@PrePersist
	public void prePersist() {
		this.createDateTime = new Date();
		this.registrator = getCurrentUser();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.modifiedDateTime = new Date();
		this.modifier = getCurrentUser();
	}

}
