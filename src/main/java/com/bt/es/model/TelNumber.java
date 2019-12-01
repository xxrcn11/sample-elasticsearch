package com.bt.es.model;

public class TelNumber {
	private String type;
	private String number;
	
	public TelNumber() {}
	
	public TelNumber(String type, String number) {
		this.type = type;
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TelNumber [type=").append(type).append(", number=").append(number).append("]");
		return builder.toString();
	}

}
