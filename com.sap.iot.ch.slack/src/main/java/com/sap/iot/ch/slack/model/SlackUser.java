package com.sap.iot.ch.slack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sap.iot.ch.slack.Constants;

/**
 * An Area to quickly select Container.
 */
@Entity
@Table(name = "SLACK_USER", schema = Constants.DEFAULT_SCHEMA)
public class SlackUser extends BaseObject implements Serializable {
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID")
	protected String userId = null;

	@Column(name = "OFFICE_STATION")
	protected int officeStation = 0;

	@Column(name = "HOME_STATION")
	protected int homeStation = 0;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getOfficeStation() {
		return officeStation;
	}

	public void setOfficeStation(int officeStation) {
		this.officeStation = officeStation;
	}

	public int getHomeStation() {
		return homeStation;
	}

	public void setHomeStation(int homeStation) {
		this.homeStation = homeStation;
	}

}
