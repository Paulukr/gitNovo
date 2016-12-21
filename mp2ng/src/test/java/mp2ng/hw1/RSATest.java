package mp2ng.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

import mp2.ng.hw.hw1.RSA;

public class RSATest {

	@Test
	public void testEncode() {


	}

	@Test
	public void testDecode() throws InterruptedException {
		RSA rsa1 = new RSA();
		rsa1.generate();
		
		Thread t1 = new Thread(() -> {

		int cm, dm;
		for(short m = 0; m < Short.MAX_VALUE; m++)
		{
			cm =  rsa1.encode(m);
			dm = rsa1.decode(cm);
			assertEquals(m, dm);
//			if(i != dc) System.out.println(i+ " "  + c +" " + dc);
		}
		});
		t1.join();
	}

}
