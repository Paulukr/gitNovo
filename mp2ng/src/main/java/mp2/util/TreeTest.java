package mp2.util;
import java.util.Collections;
import java.util.AbstractSet;
import java.util.TreeSet;

class TreeTest { 
	Printer2 printer = new Printer2();
	TreeTest(){
		Thread thread = new Thread(()->printer.print());
		thread.start();
	}
    private static TreeSet<String> set = new TreeSet<String>(); 
        public static void main(String[] args) {
        	 TreeTest tt = new TreeTest();
        set.add("one"); 
        set.add("two"); 
        set.add("three"); 
        set.add("/u000a"); 
        set.add("/u000d"); 
        set.add("/u000c"); 
        set.add("1"); 
        set.add("2"); 
        set.add("3");         
        for (String string : set) { 
            System.out.print(string + " "); 
        } 
    }  
}

class Printer implements Printable{

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("I`m printing");
	}
	
}
interface Printable {
	public void print();
}
class Printer2 {
	public void print() {
		System.out.println("I`m printing");
	}
	
}