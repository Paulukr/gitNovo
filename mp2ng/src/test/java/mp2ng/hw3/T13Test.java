package mp2ng.hw3;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import mp2.ng.hw.hw3.t13.T13;;

public class T13Test {
	List<Integer> sorted;
	List<Integer> reverseSorted;
	List<Integer> random;

	@Before
	public void setUp() throws Exception {
		final int SIZE = 5000;
		int[] randomArray = new Random().ints(SIZE, -SIZE, SIZE).toArray();

		
		random = Arrays.stream(randomArray).boxed().collect(Collectors.toList());
		sorted =  Arrays.stream(randomArray).boxed().collect(Collectors.toList());
		reverseSorted = Arrays.stream(randomArray).boxed().collect(Collectors.toList());
		
		sorted.sort(null);
		reverseSorted.sort(Collections.reverseOrder());
	}

	@Test
	public void testQuickSortArray() {
		Comparator cmp = (x, y) -> Integer.compare((int)x, (int)y);
		assertThat(sorted, is(T13.quickSortArray(sorted, cmp)));
		assertThat(sorted, is(T13.quickSortArray(reverseSorted, cmp)));
		assertThat(sorted, is(T13.quickSortArray(random, cmp)));
		assertThat(sorted, is(T13.quickSortArray(random, cmp)));
		assertThat(null, is(T13.quickSortArray(null, cmp)));
	}

	@Test
	public void testQuickSortList() {
		assertThat(sorted, is(T13.quickSortList(sorted)));
		assertThat(sorted, is(T13.quickSortList(reverseSorted)));
		assertThat(sorted, is(T13.quickSortList(random)));
	}

}
