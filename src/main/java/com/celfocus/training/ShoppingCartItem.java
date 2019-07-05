package com.celfocus.training;



public class ShoppingCartItem {

	private ItemInfo item;

	private int quantityItem;

	private double discount;  



	public ShoppingCartItem(ItemInfo item, Integer quantityItem, Double discount) {
		super();
		this.item = item;
		this.quantityItem = quantityItem;
		this.discount = discount;
	}

	public ItemInfo getItem() {
		return item;
	}

	public void setItem(ItemInfo item) {
		this.item = item;
	}

	public Integer getQuantityItem() {
		return quantityItem;
	}

	public void setQuantityItem(Integer quantityToAdd) {
		this.quantityItem =  quantityToAdd;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}  
}

