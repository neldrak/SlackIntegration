package com.sap.iot.ch.slack.json.lh;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

	@JsonProperty("@Href")
	private String href;
	@JsonProperty("@Rel")
	private String rel;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Link() {
	}

	/**
	 * 
	 * @param rel
	 * @param href
	 */
	public Link(String href, String rel) {
		super();
		this.href = href;
		this.rel = rel;
	}

	@JsonProperty("@Href")
	public String getHref() {
		return href;
	}

	@JsonProperty("@Href")
	public void setHref(String href) {
		this.href = href;
	}

	@JsonProperty("@Rel")
	public String getRel() {
		return rel;
	}

	@JsonProperty("@Rel")
	public void setRel(String rel) {
		this.rel = rel;
	}

}
