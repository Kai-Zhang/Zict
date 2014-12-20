package data;

public class WordCard {
	private String word;
	private String explanation;
	
	public WordCard(String word, String explanation) {
		this.word = word;
		this.explanation = explanation;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
