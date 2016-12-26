package mp2.ng.hw.hw4.Polinom17;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Type{OB,CB,PLUS,MULTIPLY,COMMA,NUMBER};// 
class Symbol{
	Type type;
	double value;
	public Symbol( double value) {
		this.type = Type.NUMBER;
		this.value = value;
	}
	public Symbol(String operator) {
		switch(operator){
		case "(":
			type = Type.OB;
			break;
		case ")":
			type = Type.CB;
			break;
		case "*":
			type = Type.MULTIPLY;
			break;
		case "+":
			type = Type.PLUS;
			break;
		case ",":
			type = Type.COMMA;
			break;
		default:
			throw new IllegalArgumentException("No enum constant mp2.ng.hw.hw4.Polinom17.Type." + operator);
		}
	}
	@Override
	public String toString() {
		if(type == Type.NUMBER)
			return String.valueOf(value);
		return type.toString();
					
	}
}
public class Polinom {
	 Pattern p = Pattern.compile("a*b");
	Deque<Symbol> symbols = new ArrayDeque<>();
	static String ex1 = "(1+2)*4+5*(3+6)";
	static String exP = "+(*(+(1,2),4),*(5,+(3,6)))";
	public static void main(String[] args) {
		 Pattern p = Pattern.compile("a*b");
		 Matcher m = p.matcher("aaaaab");
		 boolean b = m.matches();
		// TODO Auto-generated method stub
		 Polinom p1 = new Polinom();
		 p1.parse(exP);
		 
		 System.out.println(p1);
		 fC(p1.symbols);
	}
	void parse(String expr){
		StringBuilder number = new StringBuilder();
		for (int i = 0; i < expr.length(); i++) {
			CharSequence nextChar = String.valueOf(expr.charAt(i));

			 	if(Pattern.matches("\\d", nextChar)){
			 		number.append(nextChar);
			 	}
			 	else{
			 		if(number.length() != 0){
			 			symbols.push(new Symbol(Double.valueOf(number.toString())));
			 			number = new StringBuilder();
			 		}
			 		symbols.push(new Symbol(nextChar.toString()));
			 	}
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Deque<Symbol> symbols = new ArrayDeque<>();
		for (Symbol symbol : this.symbols) {
			symbols.push(symbol);
		}
		return Arrays.deepToString(symbols.toArray());
	}
	void evP(Deque<Symbol> expr){
		Symbol symbol = symbols.pop();
		switch (symbol.type) {
		case CB:
			break;
		case COMMA:
			break;
		case PLUS:
			symbols.removeFirst();
			symbols.removeLast();
			break;
		case MULTIPLY:
			break;
		case NUMBER:
			break;
		case OB:
			break;
			
		default:
			break;

		}
//		symbols.r
//		ev1()
	}
	static void fC(Deque<Symbol> expr){
		System.out.println(Arrays.toString(expr.toArray()));
		int ob = 0;
		int cb = 0;
		for (Iterator iterator = expr.iterator(); iterator.hasNext();) {
			Symbol symbol = (Symbol) iterator.next();
			if(symbol.type == Type.OB)
				ob++;
			if(symbol.type == Type.CB)
				cb++;
			System.out.println(" " + symbol+" ob= "+ob+" cb= "+ cb);
			
		}
	}

}