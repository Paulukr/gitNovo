package mp2.ng.hw.hw2;

import java.util.Arrays;

public class T9Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		T9DoubleLinked<Integer> ll = new T9DoubleLinked<>();
		for (int i = 0; i < 10; i++) {
			ll.add(i);
		}
		Object[] array = ll.toArray();
		
		System.out.println(" " + array.getClass() + array[1].getClass());
//		Integer[] iiArray = (Integer[])array;
		Object o = array[1];
		Integer ii = (Integer)o;
		System.out.println(ii.intValue());
		
		Integer[] testArray = new Integer[10];
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = new Integer(i);
			
		}
		System.out.println(
		Arrays.equals(testArray, ll.toArray()));
		System.out.println(Arrays.toString(ll.toArray()));
		for (int i = 9; i > 5; i--) {
			ll.remove(i);
		}
		System.out.println(Arrays.toString(ll.toArray()));
		ll.remove(ll.size() - 2);//TODO
		System.out.println(Arrays.toString(ll.toArray()));
		ll.remove(0);//TODO
		System.out.println(Arrays.toString(ll.toArray()));
		ll.remove(1);//TODO
		System.out.println(Arrays.toString(ll.toArray()));
		
		ll = new T9DoubleLinked<>();
		for (int i = 0; i < 10; i++)
			ll.add(i);
		ll.add(null);
		Double dNull = null;
		Double d2  = new Double(2);
		Integer I = new Integer(0);
		System.out.println("equals");
		System.out.println(I.equals(0));
		System.out.println(d2 == null);
		System.out.println(d2 != null);
		boolean b = ll.contains(0);
		ll.contains(3);
		ll.contains(15);
		System.out.println(" " + ll.contains(3) );
		System.out.println(" " + ll.contains(15) ); 
		System.out.println(" " + ll.contains(3.0));
		System.out.println(ll.contains(dNull));
		System.out.println(ll.contains(d2));

	}

}
