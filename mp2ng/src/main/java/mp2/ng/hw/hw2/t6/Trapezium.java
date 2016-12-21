package mp2.ng.hw.hw2.t6;

import static java.lang.Math.abs;

public class Trapezium implements Shape{
	Vector ab;//left leg  ABCD // a||b c/ \d  ac = diagonal 
	Vector ad;//lower base
	Vector ac;//diag
	Trapezium(){
		ab = new Vector();
		ad = new Vector();
		ac = new Vector();
	}
	Trapezium(Vector ad, Vector ab, Vector ac) {
		this.ab = ad;
		this.ad = ab;
		this.ac = ac;
	}
	public Trapezium(int Bx, int By, int Cx, int Dx) {
		
		this.ab = new Vector(Bx, By);
		this.ad = new Vector(0, Dx);
		this.ac = new Vector(By, Cx);
	}
	public void set(int[][] vectors) throws Exception {
		if(vectors.length != 3)
			throw new Exception("Invalid argument cout. 3 x 2 needed");
		this.ab.set(vectors[0]);
		this.ad.set(vectors[1]);
		this.ac.set(vectors[2]);
	}
	@Override
	public double area() {
		return 0.5*(abs(ac.cross(ab)) + abs(ac.cross(ad)));//  S = S(ABC) + S(ADC); //Sonar thinks it's so smart :)
	}
}
