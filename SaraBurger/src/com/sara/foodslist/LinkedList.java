package com.sara.foodslist;


public class LinkedList<K, V> {

	private int nElements;
	private Node<K, V> head, current, tail;

	LinkedList() {
		head = current = tail = null;
		nElements = 0;
	}
	
	public int getLength(){
		return this.nElements;
	}

	public void addNode(K key, V value) {
		if (nElements == 0) {
			head = new Node<K, V>(key, value);
			current = tail = head;
		} else {
			current = tail;
			tail = new Node<K, V>(key, value);
			current.setNext(tail);
			current = tail;
		}
		this.nElements++;
	}

	public void removeNode(K key) {
		Node<K, V> found, last = new Node<K, V>();
		last = found = current = head;
		while (!found.getKey().equals(key)) {			
			last = found;
			found = found.getNext();
			if(found==null) break;
		}
		last.setNext(found.getNext());		
		if (last != null){
			current = last;
			this.nElements--;
		}
	}

	public Node<K, V> currentNode() {
		return current;
	}
	
	public Node<K, V> nextNode(){
		current = current.getNext();
		return current;
	}
	
	public boolean hasNext(){
		if(current.getNext()!=null)
			return true;
		return false;
	}

	public void restart() {
		current = head;
	}

	public boolean isEmpty() {
		if (this.nElements <= 0)
			return false;
		return true;
	}

	public Node<K, V> seekNode(K key) {
		Node<K, V> found = new Node<K, V>();
		found = current = head;
		while (!found.getKey().equals(key)) {
			found = found.getNext();
			if(found==null) break;
		}
		current = found;
		return current;
	}

	public boolean findNode(K key) {
		Node<K, V> found = new Node<K, V>();
		if (head == null) return false;
		found = current = head;
		while (!found.getKey().equals(key)) {
			found = found.getNext();
			if(found==null) break;
		}
		return ((found != null) ? true : false);
	}

	@Override
	public String toString() {
		current = head;
		String makeString = "";
		for (int c = 1; c <= this.nElements; c++) {
			makeString += String.format("%s\n", current.toString());
			current = current.getNext();
		}
		return makeString;
	}

}
