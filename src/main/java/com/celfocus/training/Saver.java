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

	public static List<User> userList = new ArrayList<>();
	private User user = new User(null, null, false);

	public ShoppingCart shoppingCart = new ShoppingCart(this.user, null);


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

	public static List<User> getUserlist() {
		return userList;
	}

	/**
	 * Cria ou atualiza o usuário
	 */
	public User createOrUpdateUser(String userName, Date birthDate, boolean userOlder) {
		if (findUser(userName)!=null) {
			this.user.setNameOfUser(userName);
			this.user.setBirthDate(birthDate);
			this.user.setIfUserOlder(userIsOlder(userName, birthDate));  

		} else {
			userList.add(this.user);
		}
		return user;
	}

	/**
	 * Validar se o Usuario existe
	 */

	public static User findUser(String userName) {
		for (User user : userList) {
			if (user.nameOfUser.equals(userName)) {
				return user;
			}
		}
		JOptionPane.showMessageDialog(null, "User Does not exist");
		return null;
	}

	/**
	 * Valida se o Usuario tem mais de 65 anos.
	 */
	private boolean userIsOlder(String userName, Date birthDateUser) {

		userName = userName.toUpperCase();
		Calendar yearBirth = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));
		Calendar presentYear = Calendar.getInstance(TimeZone.getTimeZone("Europe/Lisbon"));

		yearBirth.setTime(birthDateUser);
		int year = yearBirth.get(Calendar.YEAR);

		if ((presentYear.get(Calendar.YEAR) - year) < 65) {
			return false;
		}
		return true;
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