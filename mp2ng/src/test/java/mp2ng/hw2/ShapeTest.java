package mp2ng.hw2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import mp2.ng.hw.hw2.t6.Parallelogram;
import mp2.ng.hw.hw2.t6.Ring;
import mp2.ng.hw.hw2.t6.Trapezium;
import mp2.ng.hw.hw2.t6.Triangle;

public class ShapeTest {

	@Test
	public void testSum() {
		Ring ring = new Ring(1);
		Triangle triangle = new Triangle(2,2, 2,0);
		Parallelogram parallelogram = new Parallelogram(2,2, 4,0);
		Trapezium trapezium = new Trapezium(1,2,2,3);
		double delta = 0.0001;
		assertEquals(2,triangle.area(), delta);
		assertEquals(4,triangle.sum(triangle), delta);
		assertEquals(5.1415,ring.sum(triangle), delta);
		assertEquals(10,parallelogram.sum(triangle), delta);
		assertEquals(6,trapezium.sum(triangle), delta);
	}
}

	/*
	 *
	 *public static void main(String[] args) throws Exception {

		System.out.println("2x Ring(r = 1):" + new Ring(1).sum(new Ring(1)));
		
		Triangle triangle1 = new Triangle(new Vector(1,1), new Vector(0,-1));
		System.out.println("triangle1 " + triangle1.area());
		
		Parallelogram parallelogram1 = new Parallelogram(new Vector(1,1), new Vector(0,-1));
		System.out.println("parallelogram1 " + parallelogram1.area());
		
		Trapezium tz1 = new Trapezium(1,2,2,3);
		System.out.println("Trapezium " + tz1.area());


		System.out.println("triangle1 + parallelogram1 " + triangle1.sum(parallelogram1));
		System.out.println("Trapezium + parallelogram1 " + tz1.sum(parallelogram1));

	}
}
*/
