package com.arm.challenge.commandline.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Board implements Serializable{

	private static final long serialVersionUID = 1L;
	@JsonProperty(required = true)
	private String name;
	@JsonProperty(required = true)
	private String vendor;
	@JsonProperty(required = true)
	private String core;
	@JsonProperty(required = true, value = "has_wifi")
	private Boolean hasWifi;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public String getCore() {
		return core;
	}


	public void setCore(String core) {
		this.core = core;
	}


	public Boolean getHasWifi() {
		return hasWifi;
	}


	public void setHasWifi(Boolean hasWifi) {
		this.hasWifi = hasWifi;
	}


	@Override
	public String toString() {
		return "Board [name=" + name + ", vendor=" + vendor + ", core=" + core + ", hasWifi=" + hasWifi + "]";
	}
}
