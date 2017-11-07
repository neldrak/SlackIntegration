package com.sap.iot.ch.slack.json.sbb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stationboard {

	@JsonProperty("stop")
	private Stop stop;
	@JsonProperty("name")
	private String name;
	@JsonProperty("category")
	private String category;
	@JsonProperty("subcategory")
	private Object subcategory;
	@JsonProperty("categoryCode")
	private Object categoryCode;
	@JsonProperty("number")
	private String number;
	@JsonProperty("operator")
	private String operator;
	@JsonProperty("to")
	private String to;
	@JsonProperty("passList")
	private List<PassList> passList = null;
	@JsonProperty("capacity1st")
	private Object capacity1st;
	@JsonProperty("capacity2nd")
	private Object capacity2nd;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Stationboard() {
	}

	/**
	 * 
	 * @param to
	 * @param stop
	 * @param category
	 * @param capacity2nd
	 * @param passList
	 * @param subcategory
	 * @param name
	 * @param categoryCode
	 * @param number
	 * @param capacity1st
	 * @param operator
	 */
	public Stationboard(Stop stop, String name, String category, Object subcategory, Object categoryCode,
			String number, String operator, String to, List<PassList> passList, Object capacity1st,
			Object capacity2nd) {
		super();
		this.stop = stop;
		this.name = name;
		this.category = category;
		this.subcategory = subcategory;
		this.categoryCode = categoryCode;
		this.number = number;
		this.operator = operator;
		this.to = to;
		this.passList = passList;
		this.capacity1st = capacity1st;
		this.capacity2nd = capacity2nd;
	}

	@JsonProperty("stop")
	public Stop getStop() {
		return stop;
	}

	@JsonProperty("stop")
	public void setStop(Stop stop) {
		this.stop = stop;
	}

	public Stationboard withStop(Stop stop) {
		this.stop = stop;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Stationboard withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("category")
	public String getCategory() {
		return category;
	}

	@JsonProperty("category")
	public void setCategory(String category) {
		this.category = category;
	}

	public Stationboard withCategory(String category) {
		this.category = category;
		return this;
	}

	@JsonProperty("subcategory")
	public Object getSubcategory() {
		return subcategory;
	}

	@JsonProperty("subcategory")
	public void setSubcategory(Object subcategory) {
		this.subcategory = subcategory;
	}

	public Stationboard withSubcategory(Object subcategory) {
		this.subcategory = subcategory;
		return this;
	}

	@JsonProperty("categoryCode")
	public Object getCategoryCode() {
		return categoryCode;
	}

	@JsonProperty("categoryCode")
	public void setCategoryCode(Object categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Stationboard withCategoryCode(Object categoryCode) {
		this.categoryCode = categoryCode;
		return this;
	}

	@JsonProperty("number")
	public String getNumber() {
		return number;
	}

	@JsonProperty("number")
	public void setNumber(String number) {
		this.number = number;
	}

	public Stationboard withNumber(String number) {
		this.number = number;
		return this;
	}

	@JsonProperty("operator")
	public String getOperator() {
		return operator;
	}

	@JsonProperty("operator")
	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Stationboard withOperator(String operator) {
		this.operator = operator;
		return this;
	}

	@JsonProperty("to")
	public String getTo() {
		return to;
	}

	@JsonProperty("to")
	public void setTo(String to) {
		this.to = to;
	}

	public Stationboard withTo(String to) {
		this.to = to;
		return this;
	}

	@JsonProperty("passList")
	public List<PassList> getPassList() {
		return passList;
	}

	@JsonProperty("passList")
	public void setPassList(List<PassList> passList) {
		this.passList = passList;
	}

	public Stationboard withPassList(List<PassList> passList) {
		this.passList = passList;
		return this;
	}

	@JsonProperty("capacity1st")
	public Object getCapacity1st() {
		return capacity1st;
	}

	@JsonProperty("capacity1st")
	public void setCapacity1st(Object capacity1st) {
		this.capacity1st = capacity1st;
	}

	public Stationboard withCapacity1st(Object capacity1st) {
		this.capacity1st = capacity1st;
		return this;
	}

	@JsonProperty("capacity2nd")
	public Object getCapacity2nd() {
		return capacity2nd;
	}

	@JsonProperty("capacity2nd")
	public void setCapacity2nd(Object capacity2nd) {
		this.capacity2nd = capacity2nd;
	}

	public Stationboard withCapacity2nd(Object capacity2nd) {
		this.capacity2nd = capacity2nd;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Stationboard withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}