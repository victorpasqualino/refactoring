package com.celfocus.training;



public class ShoppingCartItem {

	public ItemInfo item;

    public int quantityItem;

    public double discount;  
    
    
    public ShoppingCartItem(ItemInfo item, int quantityItem, double discount) {
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

	public int getQuantityItem() {
		return quantityItem;
	}

	public void setQuantityItem(double quantityToAdd) {
		this.quantityItem = (int) quantityToAdd;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}  
}

