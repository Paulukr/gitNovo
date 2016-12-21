package mp2.ng.hw.hw2;

class Text{
	String head;
	Sentence[] sentenses;
	@Override
	public String toString(){
		StringBuilder out = new StringBuilder();
		for (Sentence sentence : sentenses) {
			out.append(sentence.toString());
		}
		return out.toString();
	}
}
class Sentence{
	String[] words;
	public String toString(){
		StringBuilder out = new StringBuilder();
		for (String word : words) {
			out.append(word + " ");
		}
		out.append(". ");
		return out.toString();
	}
}
public class t7 {

}
