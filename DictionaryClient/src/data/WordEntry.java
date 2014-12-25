package data;

import java.util.ArrayList;

/**
 * WordEntry - stores the current word's information
 */
public class WordEntry {
	private static String word = null;
	private static ArrayList<Explanation> explanations = new ArrayList<>();
	
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
		for (int i = explanations.size(); i <= rating; i ++) {
			explanations.add(null);
		}
		explanations.set(rating, explanation);
	}
	
	public static Explanation getExplanation(int rating) {
		if (rating < 0 || rating >= explanations.size()) {
			return null;
		}
		return explanations.get(rating);
	}
	
	public static int getExplanationSize() {
		return explanations.size();
	}
	
	public static void filterExplanations() {
		for (int i = 0; i < explanations.size(); i ++) {
			if (!explanations.get(i).isSelected()) {
				explanations.remove(i);
				i --;
			}
		}
	}
	
	public static void clearExplanation() {
		explanations.clear();
	}
	
	public static void sortExplanation() {
		int resultSize = explanations.size();
		int tail = resultSize - 1;
		for (int i = 0; i < resultSize; i ++) {
			if (explanations.get(i) == null) {
				explanations.set(i, explanations.get(tail));
				explanations.set(tail, null);
				tail --;
			}
		}
		for (int i = 0; i < resultSize; i ++) {
			int popular = i;
			for (int j = i + 1; j < resultSize; j ++) {
				if (explanations.get(j).getLikeNumber() > explanations.get(popular).getLikeNumber()) {
					popular = j;
				}
			}
			Explanation temp = explanations.get(i);
			explanations.set(i, explanations.get(popular));
			explanations.set(popular, temp);
		}
	}
}
