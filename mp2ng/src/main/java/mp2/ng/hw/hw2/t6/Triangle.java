package mp2.ng.hw.hw2.t6;

import static java.lang.Math.abs;

public class Triangle implements Shape{
	Vector a;
	Vector b;
	Triangle(Vector a, Vector b) {
		this.a = a;
		this.b = b;
	}
	Triangle(int[][] vectors) {
		
		this.a = new Vector(vectors[0]);
		this.b = new Vector(vectors[1]);
	}
	public Triangle(int ax, int ay, int bx, int by) {
		this.a = new Vector(ax, ay);
		this.b = new Vector(bx, by);
	}
	@Override
	public double area() {
		return abs(0.5*a.cross(b));
	}
}
