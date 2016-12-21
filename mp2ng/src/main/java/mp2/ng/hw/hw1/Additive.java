package mp2.ng.hw.hw1;

import java.util.Arrays;

public class Additive {
	static final boolean TEST = false;
	public static void main(String[] args) {
		long x = 4;
		System.out.println(T1Bits.printBits(x, 16));
		System.out.println(lnzPosAfterSign(x));
//		System.out.println(minus(10,1));
//		System.out.println(minus(62,1));
	}
	
	static void go2(){
		// TODO Auto-generated method stub
		long[] log = new long[100];
		long startTime = System.nanoTime();
		log[0] = (long)10000000*(long)10000000;//10^7
		
		long lapTime = System.nanoTime(); 
		log[1] = (lapTime - startTime);
		startTime = lapTime;
		
		log[2] = (plus(5, 3));
		
		lapTime = System.nanoTime(); 
		log[3] = (lapTime - startTime);
		startTime = lapTime;
		
		log[4] = (plus(10000000, 10000000));
		
		lapTime = System.nanoTime(); 
		log[5] = (lapTime - startTime);
		startTime = lapTime;
		
		log[6] = (minus(10000000, 5000000));
		
		lapTime = System.nanoTime(); 
		log[7] = (lapTime - startTime);
		startTime = lapTime;
		
		System.out.println(Arrays.toString(log));
	}
	public static int lnzPosAfterSign(long x){
		long mask = 1L<<62;
//		System.out.println(T1Bits.printBits(mask, 64)+"\n");
//		mask = 1L<<48;
//		System.out.println(T1Bits.e(mask, 64)+"\n");
//		mask = 1L<<65;
//		System.out.println(T1Bits.printBits(mask, 64)+"\n");
//		mask = 1L<<64;
//		System.out.println(T1Bits.printBits(mask, 64)+"\n");
//		mask = 1L<<63;
//		System.out.println(T1Bits.printBits(mask, 64)+"\n");
		if(TEST) System.out.println(T1Bits.printBits(mask, 64)+"\n");
		int i = 63;
		for(; i > 0; i = (int) minus(i,1)){
			if(TEST) System.out.println(i);
			if(TEST) System.out.println(T1Bits.printBits(x, 64));
			if(TEST) System.out.println(T1Bits.printBits(mask, 64)+"\n");
			if((mask&x) == mask) break;
			mask>>=1;
		}
		return i;
	}
	public static long minus(long a, long b) {
    	return plus(a, bitComplement(b));
    }
	public static long bitComplement(long a) {
    	return plus(~a, 1);
    }
	public static long bitDeComplement(long a) {
    	return ~minus(a,1);
    }
    public static long plus(long a, long b) {
		if(TEST) System.out.println();
		if(TEST) System.out.println(T1Bits.printBits(a, 8));
		if(TEST) System.out.println(T1Bits.printBits(b, 8));
		long highestBit = 1<<31;
    	long result = a, shift = b, oldResult;
    	for(int i = 0; (i != highestBit)&&(shift != 0); i <<=1 ) {
    		if(TEST) System.out.println();

    		oldResult = result;
    		
    		result ^= shift;
    		//shift = (tempA & shift) << 1;
    		shift &= oldResult;
    		shift <<= 1;
    		
    		if(TEST) System.out.println(T1Bits.printBits(result, 8));
    		if(TEST) System.out.println(T1Bits.printBits(shift, 8));
    	}
		return result;
    }

}
