package com.celfocus.training.util;

import com.celfocus.training.Saver;

public class ShoppingCartItem {
    private ItemInfo item;

    private int qt;

    private double discount;

    public ItemInfo getItem() {
        return item;
    }

    public void setItem(ItemInfo item) {
        this.item = item;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
