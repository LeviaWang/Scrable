

/**
 * This class has information about Scrabble scores for scrabble letters and
 * words. In scrabble not every letter has the same value. Letters that occur
 * more often in the English language are worth less (e.g., 'e' and 's' are each
 * worth 1 point), and letters that occur less often are worth more (e.g., 'q'
 * and 'z' are worth 10 points each). You may use hardcoded values in its data.
 * Here are all the letter values: (1 point)-A, E, I, O, U, L, N, S, T, R (2
 * points)-D, G (3 points)-B, C, M, P (4 points)-F, H, V, W, Y (5 points)-K (8
 * points)- J, X (10 points)-Q, Z
 */
public class ScoreTable {
	/**
	 * <rep invar>: index is between 0 and 'Z' - 'A' (25), the value of char is between 1 - 10
	 * array i is i - 'A', the score the word > 0 
	 * charScores: a array to store the character and corresponding score
	 */
	private static int[] charScores = new int['Z' - 'A' + 1];

	// divide the characters into the different groups by their value
	private static final char[] score1 = { 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R' };
	private static final char[] score2 = { 'D', 'G' };
	private static final char[] score3 = { 'B', 'C', 'M', 'P' };
	private static final char[] score4 = { 'F', 'H', 'V', 'W', 'Y' };
	private static final char[] score5 = { 'K' };
	private static final char[] score8 = { 'J', 'K' };
	private static final char[] score10 = { 'Q', 'Z' };

	/**
	 * to update whole charScores array, writes all the character and its value
	 * respectively representation invariant: the index of character i is i - 'A'
	 */
	public static void initScore() {
		putScore(score1, 1);
		putScore(score2, 2);
		putScore(score3, 3);
		putScore(score4, 4);
		putScore(score5, 5);
		putScore(score8, 8);
		putScore(score10, 10);
	}

	/**
	 * calculate the score of the word by the score table precondition: before use
	 * the getScore() method, use initScoreMap first
	 * 
	 * @param word: the target word
	 * @return the score of the word
	 * PRE: word must not be null
	 */
	public static int getScore(String word) {
		word = word.toUpperCase();
		int wordScore = 0;
		for (char i : word.toCharArray()) {
			wordScore += charScores[i - 'A'];
		}
		return wordScore;
	}

	/**
	 * to update the charScores, add the char and corresponding score of the char
	 * into the it
	 * 
	 * @param charScore: the char array which corresponding to the score
	 * @param score:     the corresponding score of the char
	 */
	private static void putScore(char[] charScore, int score) {
		for (char i : charScore) {
			charScores[i - 'A'] = score;
		}
	}

}
