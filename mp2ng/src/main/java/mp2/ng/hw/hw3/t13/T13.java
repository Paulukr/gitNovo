package mp2.ng.hw.hw3.t13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class T13 {
private T13(){}
public static <T extends Comparable<? super T>> List<T> quickSortArray(List<? extends T> in, Comparator<? super T> cmp) {
	if(in == null) return null;
	@SuppressWarnings("unchecked")
	List<T> out = (List<T>) Arrays.asList(in.toArray());
	qSort(out,0, out.size() - 1, cmp);
	return out;
}
public static <T extends Comparable<? super T>> void qSort(List<T> out, int start, int end, Comparator<? super T> cmp) {
	if(start >= end)
		return;
	int pivot = partitionSort(out, start,end, cmp);
	qSort(out, start, pivot, cmp);
	qSort(out, pivot + 1, end, cmp);
}
private static  <T> void swap(List<T> in, int i, int j){
	T temp = in.get(i);
	in.set(i, in.get(j));
	in.set(j, temp);
}
private static <T extends Comparable<? super T>> int partitionSort(List<T> in, int start, int end, Comparator<? super T> cmp 	) {
	T pivotValue = in.get(start);
	int i = start - 1;
	int j = end+1;

	while(true){
		while(cmp.compare(in.get(++i), pivotValue) < 0 ); //in.get(++i).compareTo(pivotValue) < 0 
		while(cmp.compare(in.get(--j), pivotValue) > 0  );//in.get(--j).compareTo(pivotValue) > 0  
		if(i >= j) 
			return j;
		swap(in, i, j);
	}

	
}
//
/* PECS "Producer Extends, Consumer Super". producer get consumer set
 * private static  <T> void swap(List<? super T> in, List<? extends T> out, int i, int j){
	T temp = out.get(i);
	in.set(i, out.get(j));
	in.set(j, temp);
}
 */
	public static void main(String[] args) {
		List<Integer> al = new ArrayList<>();
		al.toArray();
		
		Integer[] ar1 = {1,3, 8,4,5,6,10,15,10};
		List<Integer> l1 = Arrays.asList(ar1);
		for (Integer is : l1) {
			System.out.println(is);
		}
		List<Integer> lr = quickSortList(l1);
		for (Integer is : lr) {
			System.out.println(is);
		}
		
		l1 = Arrays.asList(ar1);
		lr = quickSortList(l1);
		for (Integer is : lr) {
			System.out.println(is);
		}
		

	}
	public static <E extends Comparable<? super E>> List<E> quickSortList(List<E> arr) {
		if (!arr.isEmpty()) {
			E pivot= arr.get(0); //This pivot can change to get faster results
		 
		    List<E> less = new LinkedList<>();
		    List<E> pivotList = new LinkedList<>();
		    List<E> more = new LinkedList<>();
		 
		    // Partition
		    for (E i: arr) {
		        if (i.compareTo(pivot) < 0)
		            less.add(i);
		        else if (i.compareTo(pivot) > 0)
		            more.add(i);
		        else
		            pivotList.add(i);
		    }
		 
		    // Recursively sort sublists
		    less = quickSortList(less);
		    more = quickSortList(more);
		 
		    // Concatenate results
		    less.addAll(pivotList);
		    less.addAll(more);
		    return less;
		 }
		return arr;
		 
		}

}
