package data;

/**
 * WordCard - encapsulation the word card's detail
 */
public class WordCard {
	private String word = null;
	private String source = null;
	private String explanation = null;
	private String sender = null;
	
	public WordCard(String word, String source, String explanation, String sender) {
		this.word = word;
		this.source = source;
		this.explanation = explanation;
		this.sender = sender;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Override
	public String toString() {
		return "          " + sender + "                                            "
				+ word + "                                            " + source;
	}
}
