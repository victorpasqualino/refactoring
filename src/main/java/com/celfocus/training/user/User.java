package com.celfocus.training.user;

import java.util.Date;

public class User {

	//public static final List<User> userlist = new ArrayList<>();
	
	
		
	public User(String nameOfUser, Date birthDate, boolean ifUserOlder) {
		super();
		this.nameOfUser = nameOfUser;
		this.birthDate = birthDate;
		this.ifUserOlder = ifUserOlder;
	}

	public String nameOfUser; // nome

	public Date birthDate; // data de nascimento

	public boolean ifUserOlder; // se usuário é maior de idade

	/**
	 * Cria ou atualiza usuario
	 * @param userName
	 * @param birthDateUser
	 * @param isOlder
	 */
	
	

	public String getNameOfUser() {
		return nameOfUser;
	}


	public void setNameOfUser(String nameOfUser) {
		this.nameOfUser = nameOfUser;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public boolean getIfUserOlder() {
		return ifUserOlder;
	}


	public void setIfUserOlder(boolean ifUserOlder) {
		this.ifUserOlder = ifUserOlder;
	}


}
