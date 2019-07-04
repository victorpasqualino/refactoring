package com.celfocus.training.entity;

public class ShoppingCartItem {

    private Item item;

    private int quantity;

    private double discount;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Item item, int quantity, double discount) {
        this.item = item;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
