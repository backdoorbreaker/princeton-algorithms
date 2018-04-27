/**
 * @author Alan
 * 
 * Coursera: Princeton Algorithms and Data Structures 1
 * Randomized Queues and Deques
 * 
 */

import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
	//* size of stack *//
	private int n;
	
	//* front of stack *//
	private Node first;
	
	//* end of stack *//
	private Node last;
	
	//* helper linked list class *//
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
		
		Node() {
			item = null;
			next = null;
			prev = null;
		}
	}
	
	//* Deque iterator helper class *//
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		
		public boolean hasNext() { 
			return current != null; 
		}
		
		public void remove() { 
			throw new UnsupportedOperationException(); 
		}
		
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}	
	}
	
	//* constructor for empty deque *//
	public Deque() {
		first = null;
		last = null;
		n = 0;
		//assert check();
	}
	
	//* check if deque is empty *//
	public boolean isEmpty() {
		return n == 0;
	}
	
	//* return number of items in deque *//
	public int size() {
		return n;
	}
	
	//* add item to the front *//
	public void addFirst(Item item) {
		if (item == null ) throw new IllegalArgumentException();
		
		if (isEmpty()) {
			first = new Node();
			first.item = item;
			last = first;
		} else {
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
			first.prev = null;
			oldFirst.prev = first;
		}
		n++;
	}
	
	//* add item to the end *//
	public void addLast(Item item) {
		if (item == null ) throw new IllegalArgumentException();
		
		if(isEmpty()) {
			first = new Node();
			first.item = item;
			last = first;
		} else {
			Node oldLast = last;
			last = new Node();
			last.next = null;
			last.item = item;
			last.prev = oldLast;
			oldLast.next = last;
		}
		n++;
	}
	
	//* remove and return item from the front *//
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty()) last = first;
		else first.prev = null;
		return item;
		
	}
	
	//* remove and return item from the end *//
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		last = last.prev;
		n--;
		if (isEmpty()) first = last;
		else last.next = null;
		return item;
	}
	
	//* return an iterator over items in order from front to end *//
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	//* Unit Testing *//
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		
		for (int i=1; i<6; i++) {
			deque.addFirst(i);
		}
		int startingSize = deque.size();
		for (int i=0; i<startingSize; i++) {
			System.out.println(deque.removeLast());
		}
	}
}
