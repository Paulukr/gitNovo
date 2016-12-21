package mp2.ng.hw.hw2.t6;

public class Ring implements Shape{
	private int radius;
	public Ring(int radius){
		this.radius = radius;
	}

	int getRadius() {
		return radius;
	}

	void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI*radius*radius;
	}
}
