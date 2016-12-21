package mp2.ng.hw.hw1;

public class T1Bits {

	public static void main(String[] args) {
		//System.out.println(Arrays.toString(sizes));

	}
	public static long set(long x, int poz){
		long mask = 1L;
		if(poz != 1){
			mask = (1 << poz);
			mask >>=1;
		}
//		System.out.println(printBits(mask, 32));
//		System.out.println(printBits(x|mask, 32));
		return x|mask;
	}
	public static long reset(long x, int poz){

		long mask = (1 << poz);
		System.out.println();
		long maskAll1 = ~0L;
		mask >>=1;
		mask = maskAll1^mask;
		return x&(mask);
	}
	public static String printBits(long x, int size) {
		String s = "";
		long mask = 1;
		//for(int i = 0; i < size; i ++) s = "" + (((x & mask << i) == 0)?0:1) + s;
		for(int i = 0; i < size; i ++){
			s = "" + (((x & mask << i) == 0)?0:1) + s;
			if(i%8 == 0) s += " ";
		}
		return s;
	}

	
    public static int sizeof(byte b) { return sizes[0]; } 
    public static int sizeof(short s) { return sizes[1]; }
    public static int sizeof(char b) { return sizes[2]; } 
    public static int sizeof(int s) { return sizes[3]; }
    public static int sizeof(long b) { return sizes[4]; } 
    
	static int sizeofPrimitive(int type) {
		byte b = 1;
		char c = 1;
		short s = 1;
		int i = 1;
		long l = 1;
		int length = 1; // 1 bit for sign
		
		switch(type) {
			case 0: while ((b <<= 1) != 0) length++; break;
			case 1: while ((c <<= 1) != 0) length++; break;
			case 2: while ((s <<= 1) != 0) length++; break;
			case 3: while ((i <<= 1) != 0) length++; break;
			case 4: while ((l <<= 1) != 0) length++; break;
		}	
		//System.out.println(length);
		return length;
	}
	static String[] types = {"bytes", "chars", "short", "int", "long"};
	static int[] sizes = new int[types.length];
	static {
		for (int i = 0; i < types.length; i++)
			sizes[i] = sizeofPrimitive(i);
	}

}
//if((x == 0)||(x&(1L<<63))==1)  return x;
