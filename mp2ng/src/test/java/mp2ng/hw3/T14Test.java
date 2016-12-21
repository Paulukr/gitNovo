package mp2ng.hw3;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.print.attribute.IntegerSyntax;

import org.junit.Before;
import org.junit.Test;

import mp2.ng.hw.hw3.t13.T14;

public class T14Test {

	List<Integer> in1;
	List<Integer> in2;
	List<Integer> random;


	@Before
	public void setUp() throws Exception {
		final int SIZE = 5000;
		int[] randomArray = new Random().ints(SIZE, -SIZE, SIZE).toArray();

		
		random = Arrays.stream(randomArray).boxed().collect(Collectors.toList());
		in1 = new ArrayList<>();
		in2 = new ArrayList<>();
		in1.addAll(random.subList(0, SIZE/2));
		in2.addAll(random.subList(SIZE/2, SIZE));
		random.sort(null);

	}

	@Test
	public void testConcatAndSort() {
		List result  = T14.concatAndSort(in1,in2);
		List<Integer> in1Test = new ArrayList<>();
		List<Integer> in2Test = new ArrayList<>();
		in1Test.addAll(in1);
		in2Test.addAll(in2);
		in1Test.sort(null);
		in2Test.sort(null);
		
		
		assertThat(T14.concatAndSort(in1,in2), is(random));
		assertThat(T14.concatAndSort(in1,null), is(in1Test));
		assertThat(T14.concatAndSort(null,in2), is(in2Test));
		assertEquals(T14.concatAndSort(null, null), null);
//		assertThat(result, is(random));
	}

}
