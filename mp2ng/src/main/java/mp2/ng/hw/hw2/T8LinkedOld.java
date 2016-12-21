package mp2.ng.hw.hw2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class T8LinkedOld<T> implements List<T>{
	Node first;
	Node last;
	int length;
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
	
	protected DoubleNode getNode(int index){
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		DoubleNode doubleNode = new DoubleNode();
		if(index == 0)
			doubleNode.current = first;
		Node currentNode = first;
        for (int i = 0; i != (index - 1); i++) 
        	currentNode = currentNode.next;
        doubleNode.prev = currentNode;
        doubleNode.current = currentNode.next;
        return doubleNode;
	}
	
	protected DoubleNode getNode(Object o){
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
		return length;
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
		return toArray(new Object[length]);
	}
	@Override
	public  <Tl> Tl[]  toArray(Tl[] arrayin) {

 	Object[] array;
		if(arrayin.length < length){
			array = new Object[length]; 
		}
		else
			array = arrayin;
		Node currentNode = first;
		for (int i = 0; i < array.length; i++) {
			array[i] = currentNode.value;
			currentNode = currentNode.next;
		}

        if (array.length > length)
        	array[length] = null;
		return  (Tl[]) array;
	
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
			length++;
			return true;
		}
		last.next = new Node(e);
		last = last.next;
		length++;
		return true;
	}
	@Override
	public boolean remove(Object o) {
		if(o == null){
			if(first.value == null){
				first = first.next;
				length--;
				return true;
			}
			Node currentNode = first;
			for (int i = 0; i < length - 1; i++) {
				if(currentNode.next.value == null){
					currentNode.next = currentNode.next.next;
					length--;
					return true;
				}
				currentNode = currentNode.next;
			}
			return false;
		}
		if(first.value.equals(o)){
			first = first.next;
			length--;
			return true;
		}
		Node currentNode = first;
		for (int i = 0; i < length - 1; i++) {
			if(currentNode.next.value.equals(o)){
				currentNode.next = currentNode.next.next;
				length--;
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
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
        length = 0;
	}
	@Override
	public T get(int index) {
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode = currentNode.next;
        return currentNode.value;
        
        
        
	}
	@Override
	public T set(int index, T element) {
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode = currentNode.next;
        T returnValue = currentNode.value;
        currentNode.value = element;
        return returnValue;
	}
	@Override
	public void add(int index, T element) {
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode = currentNode.next;
        currentNode.next =
        		new Node(element, currentNode.next.next);
		length++;	
	}
	@Override
	public T remove(int index) {
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		Node currentNode = first;
        for (int i = 0; i != index; i++) 
        	currentNode.next = currentNode.next.next;
        T returnValue = currentNode.next.value;
		length--;
        return returnValue;
	}
	@Override
	public int indexOf(Object o) {
		if(o == null){
			Node currentNode = first;
			for (int i = 0; currentNode != null; i++) {
				if(currentNode.value == null)
					return i;
			}
			return -1;
		}
		Node currentNode = first;
		for (int i = 0; currentNode != null; i++) {
			if(currentNode.value.equals(o))
				return i;
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
		if((index >= length)||(index < 0))
			throw new IndexOutOfBoundsException();
		return new OneWayIterator(index);
	}
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if((fromIndex < 0)||(toIndex >= length)||(toIndex < fromIndex))
			throw new IndexOutOfBoundsException();
		List<T> result = new T8LinkedOld<T>();
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
	            return nextIndex < length;
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
