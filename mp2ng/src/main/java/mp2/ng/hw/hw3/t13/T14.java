package mp2.ng.hw.hw3.t13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class T14 {

	public synchronized  static <T extends Comparable<? super T>> List<T> concatAndSort(List<? extends T> in1, List<? extends T> in2) {
		if(in1 == null && in2 == null)
			return null;
		int newSize = 0;
		newSize += (in1 != null)?in1.size():0;
		newSize += (in2 != null)?in2.size():0;

		List<T> out = new ArrayList<>(newSize);
		if(in1 != null)
			out.addAll(in1);
		if(in2 != null)
			out.addAll(in2);
		T13.qSort(out, 0, out.size()-1, (x, y) -> Integer.compare((int)x, (int)y));

		return out;
	}
}
