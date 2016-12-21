package mp2.ng.hw.hw1;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Li {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger i;
		Bi b = new Bi(10);
		System.out.println(b);
		System.out.println(b.toBitString());
	}

}

class Bi{
//	@Override
//	public String toString() {
//		return "Bi [mag=" + mag + ", toString()=" + super.toString() + "]";
//	}
	@Override
	public String toString() {

		String str = "";
		Iterator<Boolean> b = mag.iterator();//descending
		for (int i = 0; i < 1; i++) {
			int yield =0;
			int p = 1;
			for (int j = 0; (j < 31)&&(b.hasNext()); j++) {
				if(b.next())
					yield += p;
				p <<= 1;
			}
			str += Integer.toString(yield) + " ";
		}
		return str;
	}
	public String toBitString() {
		String str = "";
		for (Iterator iterator = mag.iterator(); iterator.hasNext();) {
			str += ((boolean) iterator.next())?1:0;			
		}
		return str;
	}
	Deque<Boolean> mag = new ArrayDeque<Boolean>();

	Bi(String digits) {		// TODO Auto-generated constructor stub
		int i = 0;
		while((digits.charAt(i) < 49)||(digits.charAt(i) >57))
			i++;//remove leading zeroez or letters
		for(; i < digits.length(); i++){
			{//1
				int currentDigit = digits.charAt(i);
				if((currentDigit < 48)||(currentDigit >57))
					break;
				
					
			}
			
		}
			
	}
	Bi(int x){
		attachInt(x);
	}
//	private static Boolean nextBit(int)
	private void attachInt(int x){
		if (x <= 0)
			return;
		int bitmask = 1 << 30;
		while ((bitmask >>= 1) != 0){
			if((x&bitmask) != 0){
				mag.addFirst(true);
				break;
			}
		}
		while ((bitmask >>= 1) != 0){
			mag.addFirst(((x&bitmask) != 0)?true:false );
		}
	}
	public static int lnzPosAfterSign(long x){
		int mask = 1<<30;
		int i = 63;
		for(; i > 0; i = (int) i--){

			if((mask&x) == mask) break;
			mask>>=1;
		}
		return i;
	}
	
}
