package mp2.ng.hw.hw1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RSA {
	
	int p;
	int q;
	int n;
	int phi;
	int e;
	int d;
	
	public int encode(short message){
			return ModExp.expM(message, e, n);
	}
	public int decode(int message){
		return (short)ModExp.expM(message, d, n);
	}
	public void generate(){
		do{
			
			p = getPrime();//157;//
			q = getPrime();//131;//
			n = p*q;
		}while(n< 0x10000);
		phi = (p-1)*(q-1);
		e = 0x11;
		d = EuclideanAlg.mul_inv(e, phi);
		
		int tm = Short.MAX_VALUE;
		int t = ModExp.expM(tm, e, n);
		int dt = ModExp.expM(t, d, n);
		
//		System.out.println("p q n " + " " + p + " " + q + " " + n);
//		System.out.println("e d phi" + " " + e + " " + d + " " + phi);
//		System.out.println(tm + " " + t + " " + dt);
	}
	public static int getPrime(){
		return BigInteger.probablePrime(9, new Random()).intValue();
	}
	public static void main(String[] args) {
		testStr();
	}
	public static void testShort() {
		RSA rsa1 = new RSA();
		rsa1.generate();
		Scanner in = new Scanner(System.in);
		short a;
		do{
			a = in.nextShort();
			int b = rsa1.encode(a);
			int c = rsa1.decode(b);
			System.out.println(a + " " + b + " " + c);
		}while(a != 100);
		in.close();
	}
	public static void testStr() {
		
		RSA rsa1 = new RSA();
		rsa1.generate();
		String m;
		StringBuilder dm = new StringBuilder();
		Scanner in = new Scanner(System.in);
		do{
			m = in.nextLine();
			int[] cm = new int[m.length()];
			for (int i = 0; i < cm.length; i++) {
				 cm[i] = rsa1.encode((short) m.charAt(i));				
			}

			for (int i : cm) {
				dm.append((char)rsa1.decode(i));
			}
			System.out.println(m + " \n" + Arrays.toString(cm) + " \n" + dm);
		}while(m != "q");
		in.close();

	}
}
