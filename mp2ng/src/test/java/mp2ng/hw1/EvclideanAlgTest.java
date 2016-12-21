/**
 * 
 */
package mp2ng.hw1;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.function.IntBinaryOperator;

import org.junit.Test;

import mp2.ng.hw.hw1.EuclideanAlg;

/**
 * @author οσοκ³ν
 *
 */
public class EvclideanAlgTest {

	/**
	 * Test method for {@link mp2.ng.hw.hw1.EuclideanAlg#gcdee(int, int)}.
	 */
	@Test
	public final void testGcdee() {
		
		IntBinaryOperator sGSD = (int a, int b) ->{ 
			
		BigInteger bi1 = BigInteger.valueOf(a);
		BigInteger bi2 = BigInteger.valueOf(b);
		return bi1.gcd(bi2).intValue();
		};
		
		assertEquals(sGSD.applyAsInt(20,149), EuclideanAlg.gcdee(20,149));		
		assertEquals(sGSD.applyAsInt(0,0), EuclideanAlg.gcdee(0,0));
		assertEquals(sGSD.applyAsInt(1,1), EuclideanAlg.gcdee(1,1));
		assertEquals(sGSD.applyAsInt(-25,35), EuclideanAlg.gcdee(-25,35));
		assertEquals(sGSD.applyAsInt(Short.MAX_VALUE/4,1000000), EuclideanAlg.gcdee(Short.MAX_VALUE/4,1000000));
	}
	/**
	 * Test method for {@link mp2.ng.hw.hw1.EuclideanAlg#mul_inv(int, int)}.
	 */
	@Test
	public final void testMul_inv() {
		
		IntBinaryOperator sMInv = (int a, int b) ->{ 
			
		BigInteger bi1 = BigInteger.valueOf(a);
		BigInteger bi2 = BigInteger.valueOf(b);
		return bi1.modInverse(bi2).intValue();
		};
		
		assertEquals(sMInv.applyAsInt(20,149), EuclideanAlg.mul_inv(20,149));
		//fail("Not yet implemented"); // TODO
		
	}


}
