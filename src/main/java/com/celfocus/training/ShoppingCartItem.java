package com.celfocus.training;

public class ShoppingCartItem {

    private Item itemInfo;
    private int quantity;
    private double discount;

    public Item getItem() {
        return itemInfo;
    }

    public void setItem(Item item) {
        this.itemInfo = itemInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
