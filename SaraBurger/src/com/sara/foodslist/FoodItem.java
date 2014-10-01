package com.sara.foodslist;

public class FoodItem {
	private String name;
	private String size;
	private double price;
	private String description;
	private int quantity;
	private boolean specialOrder;
	
	public FoodItem(String name){
		this.name = name;
	}

	public FoodItem(String name, int quantity){
		this.name = name;
		this.quantity = quantity;		
	}
	
	public FoodItem(String name, String size, double price, String description,
			int quantity, boolean specialOrder) {
		this.name = name;
		this.size = size;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.specialOrder = specialOrder;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void increase(int quantity) {
		this.quantity += quantity;
	}

	public void decrease(int quantity) {
		if ((this.quantity - quantity) >= 0)
			this.quantity -= quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity = quantity;
	}

	public boolean getSpecialOrder() {
		return specialOrder;
	}

	public void setSpecialOrder(boolean specialOrder) {
		this.specialOrder = specialOrder;
	}

	public boolean equals(String name) {
		return this.name.equalsIgnoreCase(name);
	}

	@Override
	public String toString() {		
		return "FoodItem [name=" + name + ", size=" + size + ", price=" + price
				+ ", description=" + description + ", quantity=" + quantity
				+ ", special order=" + specialOrder + "]\n";
	}

}
