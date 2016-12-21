package mp2.ng.hw.hw2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import mp2.ng.hw.hw2.T8LinkedList.Node;

public class T9DoubleLinked<T> implements List<T> {
	//private Node header;
	
	private Node first;
	private Node last;
	int size;
	class Node{
		Node next;
		Node prev;
		T value;
		private Node(T value) {
			this.value = value;
		}
		private Node(T value, Node prev, Node next) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}
	}

	protected Node getNode(int index){
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		
		Node node = first;
        for (int i = 0; i != index; i++) 
        	node = node.next;
        return node;
	}
	
	protected Node getNode(Object o){
		if(size == 0)
			return null;
		Node node = first;
		if(o == null){
			 while(node != null){
	        	if(node.value == null)
	        		return node;
	        	node = node.next;
	        }
	        return null;
		}
		
		while(node != null){
			if(node.value == null){
				node = node.next;
				continue;
			}
			if(node.value.equals(o))
				return node;
			node = node.next;
		}
		return null;
		}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[size]);
	}

	@Override
	public  Object[]  toArray(Object[] arrayin) {

		Object[] array;
		if(arrayin.length < size)
			arrayin = new Object[size]; 
		array = arrayin;
		Node currentNode = first;
		for (int i = 0; i < array.length; i++) {
			array[i] = currentNode.value;
			currentNode = currentNode.next;
		}

		if (array.length > size)
			array[size] = null;
		return   arrayin;
	}

	@Override
	public boolean add(Object e) {
		if(size == 0){
			first = last = new Node((T) e);
			size++;
			return true;
		}
		last.next = new Node((T) e, last, null);
		last = last.next;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node node = getNode(o);
		if(node == null)
			return false;
		if (size == 1){
			first = last = null;
			size--;
			return true;
		}
		if (node == last){
			last = last.prev;
			size--;
			return true;
		}
		if (node == first){
			first = first.next;
			first.prev = null;
			size--;
			return true;
		}
		node.prev.next = node.next;
		size--;
		return true;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
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
			first = new Node(element, null, first);
			if(size == 1)
				last.prev = first;
			size++;
			return;
		}
		Node currentNode = getNode(index);
        currentNode.prev.next =
        		new Node(element, currentNode.prev, currentNode);
        currentNode.prev = currentNode.prev.next;
		size++;	
	}

	@Override
	public T remove(int index) {
		Node node;
		node = getNode(index);
		size--;
		 if(size == 0){
	        	last = first =null;
	        	return node.value;
		 }
		if(index == 0){
			first = first.next;
			first.prev = null;
	        return node.value;
		}
		if(index == size){
			last = node.prev;
			return node.value;
		}
		
		node.prev.next = node.next;
		node.next.prev = node.prev;		
        return node.value;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

}
