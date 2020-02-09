package com.bamboo.javapoet.module;

public abstract class Fruits {
	String name;
	float price;

	public Fruits(String name, float price) {
		this.name = name;
		this.price = price;
	}

	abstract void printDesc();
}
