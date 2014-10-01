package com.sara.foodslist;


public class CategoryList{
	/**
	 * 
	 */
	private String listName;
	private LinkedList<String, Category> categoryList = new LinkedList<String, Category>();
	
	public CategoryList(String listName) {
		// TODO Auto-generated constructor stub
		this.listName = listName;
	}

	public void setName(String listName){
		this.listName = listName;
	}
	
	public String getName(){
		return this.listName;
	}
	
	public void setList(LinkedList<String, Category> categoryList){
		this.categoryList = categoryList;
	}
	
	public LinkedList<String, Category> getList(){
		return this.categoryList;
	}
	
	public Node<String, Category> Category(String categoryName){
		if(categoryList.findNode(categoryName))
			return categoryList.seekNode(categoryName);
		return null;
	}
	
	public void addCategory(Category category){
		//if(!categoryList.findNode(category.getName()))
			categoryList.addNode(category.getName(), category);
		
	}
	
	public void removeCategory(String categoryName){
		this.categoryList.removeNode(categoryName);
	}

	public void addFoodItem(Category category, FoodItem foodItem){
		if(categoryList.findNode(category.getName())){
			categoryList.seekNode(category.getName()).getValue().addFood(foodItem);
		} else {
			categoryList.addNode(category.getName(), category);
			categoryList.seekNode(category.getName()).getValue().addFood(foodItem);
		}
	}
	@Override
	public String toString(){
		String toString = "["+this.listName+"]\n";
		categoryList.restart();
		for(int cItem = 1; cItem<= categoryList.getLength(); cItem++){
			toString += categoryList.currentNode().getValue().toString();
			if(categoryList.hasNext())
				categoryList.nextNode();
		}
		return toString;
	}
	
}
