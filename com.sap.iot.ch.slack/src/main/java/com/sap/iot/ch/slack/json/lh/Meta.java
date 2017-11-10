package com.sap.iot.ch.slack.json.lh;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {

	@JsonProperty("@Version")
	private String version;
	@JsonProperty("Link")
	private List<Link> link = null;
	@JsonProperty("TotalCount")
	private Integer totalCount;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Meta() {
	}

	/**
	 * 
	 * @param totalCount
	 * @param link
	 * @param version
	 */
	public Meta(String version, List<Link> link, Integer totalCount) {
		super();
		this.version = version;
		this.link = link;
		this.totalCount = totalCount;
	}

	@JsonProperty("@Version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("@Version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("Link")
	public List<Link> getLink() {
		return link;
	}

	@JsonProperty("Link")
	public void setLink(List<Link> link) {
		this.link = link;
	}

	@JsonProperty("TotalCount")
	public Integer getTotalCount() {
		return totalCount;
	}

	@JsonProperty("TotalCount")
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}