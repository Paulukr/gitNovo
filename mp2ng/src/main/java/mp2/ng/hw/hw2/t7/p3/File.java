package mp2.ng.hw.hw2.t7.p3;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class File {
	private String name;
	private Object object;
	private int size;
	private String extension;

	File(String name, int size, String extension) {
		this.name = name;
		this.size = size;
		this.extension = extension;
	}
	File(String name, int size) {
		this.name = name;
		this.size = size;
		this.extension = "bin";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	

public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub


	} 

static void test(Object[] o){System.out.println(1);}
static void test(String o){System.out.println(1);}
static void test(String... o){System.out.println(1);}
//static void test(Object...o){System.out.println(1);}

}
