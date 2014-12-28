/**
 * 
 */

/**
 * @author tgulati
 *
 */
import java.util.HashMap;
import java.util.Map;

/*
 * Write a method to determine if two strings are anagrams of each other.
 e.g. isAnagram(“secure”, “rescue”) false
 e.g. isAnagram(“conifers”, “fir cones”) true
 e.g. isAnagram(“google”, “facebook”) false

 */
public class Anagram {

	public static void main(String[] args) {
		Anagram anag = new Anagram();
		boolean isAnag = anag.isAnagram("secure", "rescuerescue");
		System.out.println(isAnag);
	}

	private boolean isAnagram(String firstString, String secondString) {
		if (firstString.equals(secondString))
			return true;
		Map<Character, Integer> mapOfLettersWithOccurence = new HashMap<Character, Integer>();
		for (int i = 0; i < firstString.length(); i++) {
			char ch = firstString.charAt(i);
			if (mapOfLettersWithOccurence.get(ch) != null) {
				int occur = mapOfLettersWithOccurence.get(ch);
				++occur;
				mapOfLettersWithOccurence.put(ch, occur);
			} else {
				mapOfLettersWithOccurence.put(ch, 1);

			}
		}
		for (int i = 0; i < secondString.length(); i++) {
			char ch = secondString.charAt(i);
			if (ch == ' ')
				continue;
			if (mapOfLettersWithOccurence.get(ch) == null)
				return false;
			else {
				int occur = mapOfLettersWithOccurence.get(ch);
				--occur;
				if (occur == 0)
					mapOfLettersWithOccurence.remove(ch);
				else
					mapOfLettersWithOccurence.put(ch, occur);

			}
		}
		if (mapOfLettersWithOccurence.isEmpty())
			return true;

		return false;
	}
}