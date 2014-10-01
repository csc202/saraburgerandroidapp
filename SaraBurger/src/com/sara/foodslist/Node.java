package com.sara.foodslist;

public class Node <K, V> {
	private K key;
	private V value;
	private Node<K, V> next;
	
	Node(){
		
	}
	
	Node(K key,V value){
		this.key = key;
		this.value = value;
	}
	
	public Node<K, V> getNext() {
		return next;
	}

	public void setNext(Node<K, V> next) {
		this.next = next;
	}

	public void setKey(K key){
		this.key = key;
	}
	
	public void setValue(V value){
		this.value = value;
	}
		
	public K getKey(){
		return this.key;
	}
	
	public V getValue(){
		return this.value;
	}	
	
	public boolean hasNext(){
		if(this.next != null)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return this.value.toString();
	}

}
