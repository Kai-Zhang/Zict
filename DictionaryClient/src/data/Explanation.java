package data;

/**
 * Explanation - encapsulation the explanation details
 */
public class Explanation {
	private String source = null;
	private String explanation = null;
	private int likeNumber = 0;
	private boolean isLiked = false;
	
	public Explanation() { }

	public Explanation(String source, String explanation, int likeNumber) {
		this.source = source;
		this.explanation = explanation;
		this.likeNumber = likeNumber;
		this.isLiked = false;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
}
