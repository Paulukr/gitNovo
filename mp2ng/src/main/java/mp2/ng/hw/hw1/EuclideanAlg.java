package mp2.ng.hw.hw1;

import java.util.Scanner;

public class EuclideanAlg {
/**
	 * 
	 */
	private EuclideanAlg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static int max(int a, int b){return (a > b)?a:b;}
	public static int min(int a, int b){return (a < b)?a:b;}
	public static int gcd(int a, int b){
		if(a < 0) a*= -1;
		if(b < 0) b*= -1;
		int d1;
		int max = max(a, b);
		int min = min(a, b);
		if(max == 0) return 1;
		if((max - min) == 1) return 1;
		while(max != min){
			d1 = max - min;
			max = max(d1, min);
			min = min(d1, min);
		}	
		return max;
	}
	public static int gcde(int a, int b){
		if(a < 0) a*= -1;
		if(b < 0) b*= -1;
		int d1;
		int max = max(a, b);
		int min = min(a, b);
		if(max == 0) return 1;
		if((max - min) == 1) return 1;
		while(max != min){	
			d1 = max%min; //d1 = max; while(d1 >= min) d1 -= min; now next if works always
			if(d1 == 0) return min;// if % used
			max = max(d1, min);
			min = min(d1, min);
		}	
		return max;
	}
	public static int gcdee(int a, int b){
		if (a == 0) return b;
		if (b == 0) return a;

		if(a < 0) a*= -1;
		if(b < 0) b*= -1;

		int shift;//remove and store common 2^shift divider
		for (shift = 0; ((a | b) & 1) == 0; ++shift) {
			a >>= 1;
		b >>= 1;
		}

		while ((a & 1) == 0)
			a >>= 1;
		// From here on, a is always odd. 
		do {
			/* remove all factors of 2 in v -- they are not common */
			while ((b & 1) == 0)  
				b >>= 1;

	if (a > b) {//swap
		int t = b; b = a; a = t;}  

	while(b >= a) b = (int) Additive.minus(b, a); //b = b%a;
	if(b == 0) return a << shift;
		} while (b != 0);

		return a << shift;//restore common factors of 2 
	}
	public static int gcdex(int a, int b){
		if(a < 0) a*= -1;
		if(b < 0) b*= -1;
		int d1;
		int max = max(a, b);
		int min = min(a, b);
/*
		int ax, bx;
		if(a > b){
			ax = 1;
			bx = -1;
		}else{
			ax = -1;
			bx = 1;
		}
*/
		if(max == 0) return 1;
		if((max - min) == 1) return 1;
		while(max != min){
			d1 = max;
			while(d1 >= min) d1 -= min;
			if (d1 == 0) return min;
			d1 = max - min;
			max = max(d1, min);
			min = min(d1, min);
		}	
		return max;
	}
	public static int mul_inv(int a, int b)
	{
		int b0 = b, t, q;
		int x0 = 0, x1 = 1;
		if (b == 1) return 1;
		while (a > 1) {
			q = a / b;
			
			t = b;
			b = a % b;
			a = t;
			
			t = x0;
			x0 = x1 - q * x0;
			x1 = t;
		}
		if (x1 < 0) x1 += b0;
		return x1;
	}
	 
	
	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		Scanner in = new Scanner(System.in);
		while(b != 100){
			a = in.nextInt();
			b = in.nextInt();
			System.out.println(gcdee(a,b));
//			System.out.println(mul_inv(a,b));
		}
		in.close();
	}
}

