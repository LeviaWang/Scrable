

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class WordFinder {
	/**
	 * read the dictionary with the exception check, input the rack, find the valid
	 * words in rack and subset of rack. When input "." to quit the loop
	 * <rep invar>: the command we input is valid 
	 * @param args the file want to read in this program
	 */
	public static void main(String[] args) {
		// choose the dictionary as of the game
		String fileName = "sowpods.txt";
		if (args.length != 0) {
			fileName = args[0];
		}
		
		try {
			AnagramDictionary anaDict = new AnagramDictionary(fileName);
			Scanner in = new Scanner(System.in);
			System.out.println("Type . to quit.");
			ScoreTable.initScore();
			findWord(anaDict, in);
		} catch (FileNotFoundException e) {
			System.out.println(fileName + " not found.");
		}
		return;
	}

	/**
	 * find the valid word in dictionary and get the score of every valid word,.
	 * finally print them out in decreasing score, with the same score, print the word in alphabet order
	 * use the string "." to quit the loop
	 * @param AnaDict: the AnagramDictionary we get
	 * @param in: Scanner get input from terminal
	 */
	private static void findWord(AnagramDictionary AnaDict, Scanner in) {
		while (true) {
			
			System.out.print("Rack? ");
			String command = in.next();
			if (command.equals(".")) {
				break;
			}
			
			//after delete non-letter char, command becomes the target word 
			String word = command.replaceAll("[^a-zA-Z]*", "");
			ArrayList<String> allSubsets = Rack.allSubsets(word);
			
			// to get score of the all subsets of the target word
			Map<String, Integer> scoreOfAllSubsets = new HashMap<>(); 
			for (String s : allSubsets) {
				ArrayList<String> anagrams = AnaDict.getAnagramsOf(s);
				if (anagrams != null) {
					int score = ScoreTable.getScore(s);
					for (String anagram : anagrams) {
						scoreOfAllSubsets.put(anagram, score);
					}
				}
			}
			
			/**
			 *  scrabbleResult show the result of the rack, score and word respectively
			 *  when construct scrabbleResult, put scoreOfAllSubsets.entrySet() immediately
			 */
			ArrayList<Map.Entry<String, Integer>> scrabbleResult = new ArrayList<>(scoreOfAllSubsets.entrySet());
			
			// use the lambda expression to implement a comparator class
			Collections.sort(scrabbleResult, 
					(n1, n2) -> (n2.getValue() - n1.getValue() != 0 ? 
					n2.getValue() - n1.getValue(): n1.getKey().compareTo(n2.getKey()))
					);
			
			System.out.println("We can make " + scoreOfAllSubsets.size() + " words from " + "\"" + command + "\"");
			
			// if scrabbleResult has 0 elements, do not print following words
			if (scrabbleResult != null && scrabbleResult.size() > 0) {
				System.out.println("All of the words with their scores (sorted by score):");
				for (Map.Entry<String, Integer> entry: scrabbleResult) {
					System.out.println(entry.getValue() + ": " + entry.getKey());
				}
			}
		}
		return;
	}

}
