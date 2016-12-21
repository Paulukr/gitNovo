package mp2.ng.hw.hw1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class RSAbi1Public {

	RSAbi1Public(BigInteger n, BigInteger encoder) {
		this.n = n;
		this.encoder = encoder;
	}
	BigInteger n;
	BigInteger encoder;
	public String encode(String message){
		byte[] inbytes = message.getBytes();
		byte[] outbytes = new BigInteger(inbytes).modPow(encoder, n).toByteArray();
		return new String(outbytes);
	}
}
class RSAbi1Private {
	final boolean TEST = true;
	RSAbi1Private(){
		generate();
	}
	public BigInteger getN() {
		return n;
	}
	public BigInteger getEncoder() {
		return encoder;
	}
	BigInteger p;
	BigInteger q;
	BigInteger n;
	BigInteger phi;
	BigInteger encoder;
	BigInteger decoder;
	
	public String encode(String message){
		return code(message, encoder);
	}
	public String decode(String message){
		return code(message, decoder);
	}
	private String code(String message, BigInteger coder){
		byte[] inbytes = message.getBytes();
		byte[] outbytes = new BigInteger(inbytes).modPow(coder, n).toByteArray();
		if(TEST){
			System.out.println(Arrays.toString(inbytes));
			System.out.println(Arrays.toString(outbytes));
		}
		return new String(outbytes);
	}
	public void generate(){
		do{
			
			p = BigInteger.probablePrime(512, new Random());
			q = BigInteger.probablePrime(512, new Random());
			n = p.multiply(q);
		}while(n.bitLength()<1024&&n.compareTo(BigInteger.ONE.shiftLeft(1022)) < 0);
		final BigInteger one = BigInteger.ONE;//System.arrayCopy
		encoder = new BigInteger("65537");
		System.out.println(encoder.gcd(p.subtract(one)));
		System.out.println(encoder.gcd(q.subtract(one)));
		phi = (p.subtract(one)).multiply(q.subtract(one));
		String eAsString = new String("10");

		decoder = encoder.modInverse(phi);
		BigInteger test10 = new BigInteger("20");
		System.out.println(test10.modPow(encoder.multiply(decoder),n));
		System.out.println(encode(decode("1")));
		
	}

}


public class RSAbi1 {

	public static void main(String[] args) {
		RSAbi1Private pkey = new RSAbi1Private();
		//pkey.generate();
		String encoded = pkey.encode("Hello");
		System.out.println(pkey.decode(encoded));

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
