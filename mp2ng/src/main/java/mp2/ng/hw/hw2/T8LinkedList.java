package mp2.ng.hw.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import mp2.ng.hw.hw2.T9DoubleLinked.Node;

public class T8LinkedList<T> implements List<T>{
	Node first;
	Node last;
	int size;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	class Node{
		Node next;
		T value;
		private Node(T value) {
			this.value = value;
		}
		private Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}
	}
	class DoubleNode{
		Node current;
		Node prev;
		//List l = new LinkedList();
	}
	private Node getNode(int index) {
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode = currentNode.next;
         return currentNode;
	}
	protected DoubleNode getDoubleNode(int index){
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		
		DoubleNode doubleNode = new DoubleNode();
		if(index == 0){
			doubleNode.current = first;
			return doubleNode;
		}
		
		Node currentNode = first;
        for (int i = 0; i != (index - 1); i++) 
        	currentNode = currentNode.next;
        
        doubleNode.prev = currentNode;
        doubleNode.current = currentNode.next;
        return doubleNode;
	}
	
	protected DoubleNode getDoubleNode(Object o){
		if(first == null)
			return null;
		Node currentNode = first;
		Node prev = null;
		DoubleNode doubleNode = new DoubleNode();
		if(o == null){
			 while(currentNode != null){
				 
	        	if(currentNode.value == null){
	        		doubleNode.current = currentNode;
	        		doubleNode.prev = prev;
	        		
	        		return doubleNode;
	        	}
	        prev = currentNode;
	        currentNode = currentNode.next;
	        }
	        return null;
		}
		
		while(currentNode != null){
			if(currentNode.value == null){
				prev = currentNode;
				currentNode = currentNode.next;
				continue;
			}
			if(currentNode.value.equals(o)){
				doubleNode.current = currentNode;
				doubleNode.prev = prev;

				return doubleNode;
			}
			prev = currentNode;
			currentNode = currentNode.next;
		}
		return null;
		}
	
	@Override
	public int size() {
		return size;
	}
	
	
	@Override
	public boolean isEmpty() {
		return first == null;
	}
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}
	@Override
	public Iterator<T> iterator() {
		return iterator(0);
	}
	@Override
	public Object[] toArray() {
		return toArray(new Object[size]);
	}
	@Override
	public  <TL> TL[]  toArray(TL[] arrayin) {

 	Object[] array;
		if(arrayin.length < size)
			arrayin = (TL[]) new Object[size]; 
		array = arrayin;
		Node currentNode = first;
		for (int i = 0; i < array.length; i++) {
			array[i] = currentNode.value;
			currentNode = currentNode.next;
		}

        if (array.length > size)
        	array[size] = null;
		return   arrayin;
	
		//TODO types;
		/*
		 * 		if(first != null){
			if(first.value != null){
				if(arrayin.getClass().getComponentType() != first.value.getClass()) throw new ClassCastException();
			}
		}
		(T[]) new Object[length]//Type safety: Unchecked cast from Object[] to T[]
		array = (T[])java.lang.reflect.Array.newInstance(
				arrayin.getClass().getComponentType(), length);
		arrayin = (T[])java.lang.reflect.Array.newInstance(
            		arrayin.getClass().getComponentType(), length);
	
	*/
	}
	
	@Override
	public boolean add(T e) {
		if(last == null){
			first = new Node(e);
			last = first;
			size++;
			return true;
		}
		last.next = new Node(e);
		last = last.next;
		size++;
		return true;
	}
	@Override
	public boolean remove(Object o) {
		DoubleNode doubleNode;
		doubleNode = getDoubleNode(o);
		if(doubleNode == null)
			return false;
		size--;
		if(size == 0){
			first = last = null;
			return true;
		}
		if(doubleNode.current == last){
			last = doubleNode.prev;
		}
		if(doubleNode.current == first){
			first = first.next;
			return true;
		}
		doubleNode.prev.next = doubleNode.current.next;
        return true;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void clear() {
        for (Node currentNode = first; currentNode != null; ) {
            Node next = currentNode.next;
            currentNode.value = null;
            currentNode.next = null;
            currentNode = next;
        }
        first = last = null;
        size = 0;
	}
	@Override
	public T get(int index) {
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode = currentNode.next;
        return currentNode.value;
        
        
        
	}
	@Override
	public T set(int index, T element) {
		Node currentNode = getNode(index);
        T returnValue = currentNode.value;
        currentNode.value = element;
        return returnValue;
	}
	
	@Override
	public void add(int index, T element) {
		if(index == size){
			add(element);
			return;
		}
		if(index == 0){
			first = new Node(element, first);
			size++;
			return;
		}
		DoubleNode currentNode = getDoubleNode(index);
        currentNode.prev.next =
        		new Node(element, currentNode.current);
		size++;	
	}
	
	@Override
	public T remove(int index) {
		List l = new LinkedList<>();

		DoubleNode doubleNode;
		doubleNode = getDoubleNode(index);
		size--;
		 if(size == 0){
	        	last = null;
	        	first = null;
	        	return doubleNode.current.value;
		 }
		if(index == 0){
			first = first.next;
	        return doubleNode.current.value;
		}
		if(index == size - 1){
			last = doubleNode.prev;
		}
		doubleNode.prev.next = doubleNode.current.next;
		
        return doubleNode.current.value;
	}
	@Override
	public int indexOf(Object o) {
		Node currentNode = first;
		if(o == null){
			for (int i = 0; i < size; i++) {
				if(currentNode.value == null)
					return i;
				currentNode = currentNode.next;
			}
			return -1;
		}
		for(int i = 0; i < size; i++) {
			if(currentNode.value == null){
				currentNode = currentNode.next;
				continue;
			}
			if(currentNode.value.equals(o))
				return i;
			currentNode = currentNode.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}
	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
	}
	@Override
	public ListIterator<T> listIterator(int index) {
			throw new UnsupportedOperationException();
	}
	public Iterator<T> iterator(int index) {
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		return new OneWayIterator(index);
	}
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if((fromIndex < 0)||(toIndex >= size)||(toIndex < fromIndex))
			throw new IndexOutOfBoundsException();
		List<T> result = new T8LinkedList<T>();
		//1 by value, no by reference
		Node currentNode = first;
		int i = 0;
        for (; i != fromIndex; i++) 
        	currentNode = currentNode.next;
        for (; i <= toIndex; i++){
        	result.add(currentNode.value);
        	currentNode = currentNode.next;
        }
		return result;
	}
	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
	 private class OneWayIterator implements Iterator<T> {
	        private Node lastReturned;
	        private Node next;
	        private int nextIndex;
	        

	        OneWayIterator(int index) {
	    		Node currentNode = first;
	            for (int i = 0; i != index; i++) 
	            	currentNode = currentNode.next;
	            next = currentNode;
	            nextIndex = index;
	        }
	    	@Override
	        public boolean hasNext() {
	            return nextIndex < size;
	        }
	    	@Override
	        public T next() {
	            if (!hasNext())
	                throw new NoSuchElementException();

	            lastReturned = next;
	            next = next.next;
	            nextIndex++;
	            return lastReturned.value;
	        }
	 }
}
