package mp2.util;

import java.io.File;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NavigableMap;
import java.util.TreeMap;

//class Fu extends java.lang.Math{
//	
//}
interface A1 {
	 void m ();
}

class B extends A{
	B(){}
	int x =2;
	int getX(){
		return x + 1;
	}
	static void g(){
		
	}
}
public class A {
	A(){
		
	}
static void f(){
	
	int a =7, b=4;
	System.out.println((-a%b)==(a%-b));
	int then;

	
}
int x = 1;
int getX(){
	return x;
}

void printX(){
	System.out.println(getX());
}
//A(){
//B.g();
// new A();
//}
enum Ae{Ae}
//class 
static int sum(int a, int b){
	try{
		return a + b;
	}finally{
		if(a==b)
			return 0;
	}
}
enum Enum {
	O("oi"), T("ti"), TR("tri");
	private static String in = "";
//	Enum(String s){ in = s;} // error , not in enums
	Enum(String s){}
	}

static void mL(Long l){}
public static void main(String \u005B \u005D args) {
	byte  b =5;
//	mL(b);
	Short sh;
//	boolean boo = sh instanceof (Short);
	float hex = 0.1f;
	int iii =0;
//	iii=hex
	List<Integer> l  = new <Integer>LinkedList();
	Integer In = 343;
	long ll = 343L;
	System.out.println(In.equals(ll));
	System.out.println(In.equals(343L));
	
	Formatter fr = new Formatter(Locale.ROOT);
	fr.format("%.2f", 100.0/3);
	System.out.println("formatter " + fr);
	char ch1 = '\u005B';
	char ch2 = 93;
	System.out.println("Chars = " + ch1*ch2);
	System.out.println(ch1*ch2);
	int as = 0;
	assert (true): "assert";
	assert (as == 1): "false assert";
	int[] ii = new int[5];
	Integer[] II = new Integer[5];
	
	System.out.println("Class " + ii.getClass() + " \n" + II.getClass());
	System.out.println("try fin " + sum(3,3));
	System.out.println("try fin " + sum(3,4));
	boolean bool = false;
	if(bool=false)
		System.out.println(b);
	else
		System.out.println("no b");
	String a = "abc";
	System.out.println(a == "abc");
	byte xx = 0 ;
	byte x2;
	for (int i = 0; i < 3; i++) {
		if(i == 1) continue;
		System.out.println(i);
	}
	Object o;

//	x2 = (--xx <0)?++xx:-xx;
//	System.out.println("xx= "+x2);
	File f;
	System.out.println("atest");
	A aclass  = new B();
	aclass.printX();
//	String h = args[2];
	System.err.println((Object)null instanceof Object);
	
//	throw null;
	NavigableMap<String,String> nm = new TreeMap<String,String>();

	for(byte i = 6, j = 0, k = 0; ((j+=i++) <= 10)&&(k<5); k++, i >>= 1, System.out.println(--j));
}
static void q8(int i){
	new A();
}
}
