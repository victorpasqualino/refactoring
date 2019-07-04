package com.celfocus.domain;

public class ItemInfo {
	private String name;
	private double valor;

	public ItemInfo() {
	}
	
	public ItemInfo(String name, double valor){
		this.name = name;
		this.valor = valor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}