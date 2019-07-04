package com.celfocus.domain;

import java.util.Date;

public class User {
	private String name;
	private Date birthDate;
	private boolean isOfAge;

	public User() {
	}
	
	public User(String name, Date birthDate, boolean isOfAge){
		this.name = name;
		this.birthDate = birthDate;
		this.isOfAge = isOfAge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isOfAge() {
		return isOfAge;
	}

	public void setOfAge(boolean isOfAge) {
		this.isOfAge = isOfAge;
	}
}