package com.bt.es.model;

import java.util.List;
import java.util.Map;

public class Person {
	private String firstName;
	private String lastName;
	
	private int age;
	private List<Address> addressList;
	
	private String message;
	
	private List<Map<String, TelNumber>> telNumbers;
	
	public Person() {}

	public Person(String firstName, String lastName, int age, List<Address> addressList, String message,
			List<Map<String, TelNumber>> telNumbers) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addressList = addressList;
		this.message = message;
		this.telNumbers = telNumbers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Map<String, TelNumber>> getTelNumbers() {
		return telNumbers;
	}

	public void setTelNumbers(List<Map<String, TelNumber>> telNumbers) {
		this.telNumbers = telNumbers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [firstName=").append(firstName).append(", lastName=").append(lastName).append(", age=")
				.append(age).append(", addressList=").append(addressList).append(", message=").append(message)
				.append(", telNumbers=").append(telNumbers).append("]");
		return builder.toString();
	}

}
