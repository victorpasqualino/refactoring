package com.celfocus.training;

public class ItemInfo extends ShoppingCartItem {

	public ItemInfo(ItemInfo item, Integer quantityItem, Double discount, String name, Double valor) {
		super(item, quantityItem, discount);
		this.name = name;
		this.valor = valor;
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

	public void setValor(Double valor) {
		this.valor = valor;
	}
}