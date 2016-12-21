package mp2ng.hw2;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import mp2.ng.hw.hw2.T8LinkedList;
import mp2.ng.hw.hw2.T9DoubleLinked;

public class T8LinkedListTest {

	T8LinkedList<Integer> ll = new T8LinkedList<>();
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testSize() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		ll.add(null);
		ll.remove(null);
		assertEquals(10, ll.size());
	}

	@Test
	public void testIsEmpty() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		ll.add(null);
		
		while(!ll.isEmpty())
			ll.remove(ll.size()-1);
		assertEquals(true, ll.isEmpty());
	}

	@Test
	public void testContains() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		ll.add(null);
		Double dNull = null;
		Double d2  = new Double(2);
		assertEquals(true, ll.contains(3));
		assertEquals(false, ll.contains(15));
		assertEquals(false, ll.contains(3.0));
		assertEquals(true, ll.contains(dNull));
		assertEquals(false, ll.contains(d2));
	}

	@Test
	public void testToArray() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		Integer[] testArray = new Integer[10];
		for (int i = 0; i < testArray.length; i++) 
			testArray[i] = new Integer(i);
		assertArrayEquals(testArray, ll.toArray());
	}

	@Test
	public void testAddT() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		Integer[] testArray = new Integer[10];
		for (int i = 0; i < testArray.length; i++) 
			testArray[i] = new Integer(i);
		
		assertArrayEquals(testArray, ll.toArray());
	}

	@Test
	public void testAddIntT() {
		
		Integer[] testArray = new Integer[10];
		for (int i = 0; i < testArray.length; i++) 
			testArray[i] = new Integer(i);
		
		for (int i = 0; i < 10; i++)
			ll.add(i,i);
		assertArrayEquals(testArray, ll.toArray());
		
		ll =  new T8LinkedList<>();
		for (int i = 9; i >= 0; i--)
			ll.add(0,i);
		assertArrayEquals(testArray, ll.toArray());
		
		ll =  new T8LinkedList<>();
		ll.add(0);
		ll.add(9);
		for (int i = 8; i >= 1; i--)
			ll.add(1,i);
		assertArrayEquals(testArray, ll.toArray());
		
		ll =  new T8LinkedList<>();
		ll.add(0);
		ll.add(9);
		for (int i = 1; i < 9; i++)
			ll.add(ll.size() - 1, i);
		assertArrayEquals(testArray, ll.toArray());
	}
	
	@Test
	public void testRemoveObject() {
//		for (int i = 0; i < 10; i++)
//			ll.add(i);
//		
//		ll.add(null);
		ll.remove(Integer.valueOf(0));
		ll.remove(Integer.valueOf(9));
		ll.remove(null);
		ll.remove(Integer.valueOf(8));
		ll.remove(Integer.valueOf(3));
		
//		Integer[] testArray = {1,2,4,5,6,7};
		Integer[] testArray = {};
		assertArrayEquals(testArray, ll.toArray());
	}

	@Test
	public void testRemoveInt() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		
		for (int i = 9; i > 5; i--) 
			ll.remove(i);

		Integer[] testArray = new Integer[6];
		for (int i = 0; i < testArray.length; i++) 
			testArray[i] = new Integer(i);

		assertArrayEquals(testArray, ll.toArray());
		
		
		ll.remove(0);
		Integer[] testArray2 = {1,2,3,4,5};
		assertArrayEquals(testArray2, ll.toArray());
		
		ll.remove(1);
		ll.remove(2);
		Integer[] testArray3 = {1,3,5};
		assertArrayEquals(testArray3, ll.toArray());
	}



}
