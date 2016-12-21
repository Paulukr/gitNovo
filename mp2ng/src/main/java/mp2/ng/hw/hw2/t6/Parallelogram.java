package mp2.ng.hw.hw2.t6;

public class Parallelogram extends Triangle{
	Parallelogram(Vector a, Vector b) {
		super(a, b);
	}
	
	public Parallelogram(int ax, int ay, int bx, int by) {
		super(ax, ay, bx, by);
	}
	
	@Override
	public double area() {
		return 2*super.area();
	}
	
}

