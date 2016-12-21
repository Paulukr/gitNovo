package mp2.ng.hw.hw1;

import java.util.Scanner;

public class ModExp {

	/**
	 * 
	 */
	private ModExp() {
	}
	public static int expM(int a, int e,int n){
		if (e == 0) return 1;
		if (e == 1) return a%n;
		int ra = a%n;
		long t = ra;
		for (int i = 1; i < e; i++) {
			t *= ra;
			t = t%n;
		}
		return (int) t;
	}
	public static void main(String[] args) {
		int a, e, n;
		Scanner in = new Scanner(System.in);
		do{
			a = in.nextInt();
			e = in.nextInt();
			n = in.nextInt();
			System.out.println(expM(a,e,n));
//			int d = EuclideanAlg.mul_inv(e,n - 1);
//			System.out.println(expM(a,e,n) + " " + d + " "
//			+ expM(expM(a,e,n), d, n));
			


			
		}while(a != 100);
		in.close();

	}

}
