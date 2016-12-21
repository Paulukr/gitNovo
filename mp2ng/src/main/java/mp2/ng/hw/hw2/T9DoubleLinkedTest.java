package mp2.ng.hw.hw2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T9DoubleLinkedTest {
	T8LinkedList<Integer> ll = new T8LinkedList<>();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSize() {
		for (int i = 0; i < 10; i++)
			ll.add(i);
		ll.add(null);
		assertEquals(11, ll.size());
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
	public void testAddObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testIndexOf() {
		fail("Not yet implemented");
	}

}
