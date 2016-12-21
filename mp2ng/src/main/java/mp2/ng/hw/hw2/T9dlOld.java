package mp2.ng.hw.hw2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import mp2.ng.hw.hw2.T8LinkedList.Node;

public class T9dlOld<T> implements List {
	private Node header;
	
//	private Node first;
//	private Node last;
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
		}
	}

	protected Node getNode(int index){
		if((index >= size)||(index < 0))
			throw new IndexOutOfBoundsException();
		
		Node node = header;
        for (int i = 0; i != index; i++) 
        	node = node.next;
        return node;
	}
	
	protected Node getNode(Object o){
		if(header == null)
			return null;
		Node node = header;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		if(size == 0){
			header = new Node((T) e);
			size++;
			return true;
		}
		if(size == 1){
			header.next = new Node((T) e, header, header);
			header.prev = header.next;
			size++;
			return true;
		}
		header.prev.next = new Node((T) e, header.prev, header);
		header.prev = header.prev.next;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node node = getNode(o);
		if(node == null)
			return false;
		if (size == 1){
			header = null;
			size--;
		}
		if (node == header){
			header = null;
			size--;
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
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		Node currentNode = header;
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

}
