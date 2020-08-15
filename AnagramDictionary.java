

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {
	/**
	 * instance variable:HashMap to store all the canonical words and corresponding anagrams
	 * Key: canonical String 
	 * Value: ArrayList to store all anagrams of the key
	 * <Rep invar>: file is a valid Scrabble dictionary, the word in getAnagrams method is not empty
	 */
	
	
	private Map<String, ArrayList<String>> anaMap; // map to store the canonical word and its anagram words

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName the name of the file to read from
	 * @throws FileNotFoundException if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException {
		anaMap = new HashMap<String, ArrayList<String>>();
		File file = new File(fileName);
		Scanner in = new Scanner(file);

		while (in.hasNext()) {
			String word = in.next();
			String canonicalWord = getCanonical(word);// find its canonical word

			if (!anaMap.containsKey(canonicalWord)) {
				ArrayList<String> anagrams = new ArrayList<String>();
				anagrams.add(word);
				anaMap.put(canonicalWord, anagrams); // if canonical word doesn't exist in map, create a new one
			} else {
				anaMap.get(canonicalWord).add(word); // canonical word exists, add a new one in the arrayList
			}
		}
		in.close();
		anaMap.remove("");
	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g.
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s string to process
	 * @return a list of the anagrams of s
	 */
	public ArrayList<String> getAnagramsOf(String s) {
		// corner case
		if (s == null || s.length() == 0) {
			return null;
		}
		
		String str = getCanonical(s);
		if (anaMap.containsKey(str)) {
			ArrayList<String> anagrams = new ArrayList<>();// create a new ArrayList to hold the result
			anagrams.addAll(anaMap.get(str)); // deep copy of anaMap.get(str), so it won't invalidate the map
			return anagrams;
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * from the target string to a anagram whose char is in alphabetical order, eg. clam -> aclm
	 * 
	 * @param s: the target string
	 * @return A Canonical String of the target word s
	 */
	private String getCanonical(String s) {
		char[] ch = s.toCharArray();
		Arrays.sort(ch);
		String canonicalWord = new String(ch);
		return canonicalWord;
	}

}