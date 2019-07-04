package com.celfocus.training;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.celfocus.training.user.User;

public class ShoppingCart {
	public User user;
	private static List<ShoppingCartItem> shoppingCartlistItem = new ArrayList<>();


	public ShoppingCart(User user, List<ShoppingCartItem> shoppingCartlistItem) {
		super();
		this.user = user;
		ShoppingCart.shoppingCartlistItem = shoppingCartlistItem;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public static List<ShoppingCartItem> getShoppingCartlistItem() {
		return shoppingCartlistItem;
	}



	public static void setShoppingCartlistItem(List<ShoppingCartItem> shoppingCartlistItem) {
		ShoppingCart.shoppingCartlistItem = shoppingCartlistItem;
	}



	/**
	 * Adicionar item ao carrinho
	 */    
	public void addItemShoppingCart(String userName, String itemName, int quantityToAdd) {
		
		itemName = itemName.toLowerCase().concat("_item");
		if ((this.user = Saver.findUser(userName)) != null ) {
			for (ShoppingCartItem item : shoppingCartlistItem) {
				if(itemName == item.getItem().getName()) {
					if(quantityToAdd >0 ) {
						item.setQuantityItem(quantityToAdd+item.getQuantityItem());
					}else {
						JOptionPane.showMessageDialog(null, "Insira uma Quantidade valida");
					}
				}else {
					shoppingCartlistItem.add(item);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "User não Existe");

		}
	}


	public void removeItemShoppingCart(String userName, String itemName) {
		itemName = itemName.toLowerCase().concat("_item");
		if ((this.user = Saver.findUser(userName)) != null ) {
			if(existItem(itemName,this.valueItem(itemName)) != null ) {
				shoppingCartlistItem.remove(existItem(itemName,this.valueItem(itemName)));
				JOptionPane.showMessageDialog(null, "Item removido");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "User não Existe");

		}
	}

	private double valueItem(String itemName) {
		for (ShoppingCartItem item : shoppingCartlistItem) {
			if(itemName == item.getItem().getName()) {
				return item.getItem().getValor();
			}else
			{
				JOptionPane.showMessageDialog(null, "Item without value.");
				return 0;				
			}
		}
		return 0;

	}

	private ShoppingCartItem existItem(String itemName, double itemValue) {
		for (ShoppingCartItem item : shoppingCartlistItem) {
			if(itemName == item.getItem().getName()) {
				if(itemValue == this.valueItem(itemName)) {
					return item;
				}
			}      
		}
		JOptionPane.showMessageDialog(null, "Item does not exist");
		return null;
	}

}