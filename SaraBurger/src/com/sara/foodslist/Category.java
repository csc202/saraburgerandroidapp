package com.sara.foodslist;


public class Category {
	private String categoryName;
	private LinkedList<String, FoodItem> foodList = new LinkedList<String, FoodItem>();

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return this.categoryName;
	}

	public void setList(LinkedList<String, FoodItem> foodList) {
		this.foodList = foodList;
	}

	public LinkedList<String, FoodItem> getList() {
		return foodList;
	}

	public void addFood(FoodItem food) {
		if (this.foodList.findNode(food.getName()))
			this.foodList.seekNode(food.getName()).getValue()
					.increase(food.getQuantity());
		else
			this.foodList.addNode(food.getName(), food);
	}

	public void removeFood(FoodItem food) {
		this.foodList.seekNode(food.getName()).getValue()
				.decrease(food.getQuantity());
	}

	public void removeFoodNode(String foodName) {
		this.foodList.removeNode(foodName);
	}

	public Node<String, FoodItem> foodItem(String foodName) {
		if (this.foodList.findNode(foodName))
			return this.foodList.seekNode(foodName);
		return null;
	}
	
	@Override
	public String toString(){
		String toString = "";
		foodList.restart();		
		for(int cItem = 1; cItem<= foodList.getLength(); cItem++){
			toString += "["+this.categoryName+"]\t"+foodList.currentNode().getValue().toString();
			if(foodList.hasNext())
				foodList.nextNode();
		}
		return toString;
	}

}
