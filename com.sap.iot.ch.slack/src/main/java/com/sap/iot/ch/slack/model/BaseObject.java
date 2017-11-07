package com.sap.iot.ch.slack.model;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModelProperty;

/**
 * Base class for all domain model objects.
 */
@Access(AccessType.FIELD)
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public abstract class BaseObject {
	

	/**
	 * The (globally unique) ID of the object.
	 */
	@Id
	@NotNull
	@Column(name = "ID", length = 36, insertable = true, updatable = false)
	@ApiModelProperty(required = false, hidden = true)
	private String id = UUID.randomUUID().toString();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).toString();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !(obj instanceof BaseObject)) {
			return false;
		}

		BaseObject other = (BaseObject) obj;

		if (id == null) {
			return false;
		}

		return id.equals(other.getId());
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public final int hashCode() {
		if (id != null) {
			return id.hashCode();
		} else {
			return super.hashCode();
		}
	}
}
