package data;


public class WordEntry {
	private static String word = null;
	private static Explanation[] explanations = new Explanation[3];
	
	public WordEntry() {}
	
	public static String getWord() {
		return word;
	}
	
	public static void setWord(String word) {
		WordEntry.word = word;
	}
	
	public static void setExplanation(int rating, Explanation explanation) {
		if (rating < 0 || rating >= 3) {
			return;
		}
		explanations[rating] = explanation;
	}
	
	public static Explanation getExplanation(int rating) {
		if (rating < 0 || rating >= 3) {
			return null;
		}
		return explanations[rating];
	}
	
	public static void sortExplanation() {
		/*int nullNumber = 0;
		int likedNubmer = 0;
		for (int i = 0; i < 3; i ++) {
			if (explanations[i] == null) {
				if (i < 2 - nullNumber) {
					explanations[i] = explanations[2 - nullNumber];
					explanations[2 - nullNumber] = null;
					++ nullNumber;
				}
			}
			else if (explanations[i].isLiked()) {
				if (i > likedNubmer) {
					Explanation temp = explanations[i];
					explanations[i] = explanations[likedNubmer];
					explanations[likedNubmer] = temp;
					++ likedNubmer;
				}
			}
		}*/
		// TODO
		int tail = 2;
		for (int i = 0; i < 3; i ++) {
			if (explanations[i] == null) {
				explanations[i] = explanations[tail];
				explanations[tail] = null;
				tail --;
			}
		}
		for (int i = 0; i < 3; i ++) {
			int popular = i;
			for (int j = i + 1; j < 3; j ++) {
				if (explanations[j].getLikeNumber() > explanations[popular].getLikeNumber()) {
					popular = j;
				}
			}
			Explanation temp = explanations[i];
			explanations[i] = explanations[popular];
			explanations[popular] = temp;
		}
	}
}
