package com.bt.es.model;

import java.util.List;

public class Person {
	private String firstName;
	private String lastName;
	
	private int age;
	private List<Address> addressList;
	
	private String message;
	
	public Person() {}

	public Person(String firstName, String lastName, int age, List<Address> addressList, String message) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.addressList = addressList;
		this.message = message;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [firstName=").append(firstName).append(", lastName=").append(lastName).append(", age=")
				.append(age).append(", addressList=").append(addressList).append(", message=").append(message)
				.append("]");
		return builder.toString();
	}
	
}
