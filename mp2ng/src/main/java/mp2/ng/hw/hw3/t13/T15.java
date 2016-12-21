package mp2.ng.hw.hw3.t13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class T15<T extends Comparable<? super T> > {
	private List<T> body = new LinkedList<>();
	int outPointer;
	public void endQueue(T e){
		int pos = Collections.binarySearch(body, e);
//		if(pos < 0)
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
