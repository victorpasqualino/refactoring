package com.celfocus.training;

public class ItemInfo extends ShoppingCartItem {


	public ItemInfo(ItemInfo item, Integer quantityItem, Double discount) {
		super(item, quantityItem, discount);
		// TODO Auto-generated constructor stub
	}

	public String name;

	public Double valor;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}