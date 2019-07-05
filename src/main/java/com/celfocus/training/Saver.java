package com.celfocus.training;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import com.celfocus.training.user.User;


/**
 * Temos 4 entidades em nosso projeto User, ShoppingCart, ShoppingCartItem e ItemInfo
 */
public class Saver {

	private static List<User> userList = new ArrayList<>();
	private User user = new User(null, null, false);



	public Saver(User user) {
		super();
		this.user = user;
	}

	public static List<User> getUserList() {
		return userList;
	}


	public static void setUserList(List<User> userList) {
		Saver.userList = userList;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * Cria ou atualiza o usuário
	 */
	public User createOrUpdateUser(String userName, Date birthDate) {
		if (findUser(userName)!=null) {
			this.user.setNameOfUser(userName);
			this.user.setBirthDate(birthDate);
			this.user.setIfUserOlder(userIsOlder(birthDate));  
			JOptionPane.showMessageDialog(null, "Update Completed");

		} else {
			userList.add(this.user);
			JOptionPane.showMessageDialog(null, "User Added");
		}
		return user;
	}

	/**
	 * Validar se o Usuario existe
	 */

	public static User findUser(String userName) {
		for (User user : userList) {
			if (user.getNameOfUser().equals(userName)) {
				return user;
			}
		}
		JOptionPane.showMessageDialog(null, "User Does not exist");
		return null;
	}

	/**
	 * Valida se o Usuario tem mais de 65 anos.
	 */
	private boolean userIsOlder(Date birthDateUser) {
		Calendar yearBirth = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));
		Calendar presentYear = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));

		yearBirth.setTime(birthDateUser);
		int year = yearBirth.get(Calendar.YEAR);

		return ((presentYear.get(Calendar.YEAR) - year) < 65);			
	}

	/**
	 * Remover Usuario
	 */
	public void deleteUser(String userName) {	
		if(findUser(userName)!=null) {
			userList.remove(user);
			JOptionPane.showMessageDialog(null, "User deleted");
		}
	}

} 