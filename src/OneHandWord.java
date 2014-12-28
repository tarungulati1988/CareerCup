/**
 * 
 */

/**
 * @author tgulati
 * 
 */
public class OneHandWord {
	// Strings containing all of the left and right characters
	// of a qwerty keyboard including space
	public static String left = "qwerasdfzxcvtgb ";
	public static String right = "uiopjklmnhy ";

	/****************************************************/
	/****
	 * Given a list of strings /**** these methods find the largest /**** and
	 * smallest words in a list that can /**** be typed with one hand. /
	 ****************************************************/

	public static String largestLeft(String[] words) {
		String ret = "";
		for (String s : words) {
			if (isLeft(s.toLowerCase()) && s.length() > ret.length()) {
				ret = s;
			}
		}
		return ret;
	}

	public static String smallestLeft(String[] words) {
		String ret = "";
		for (String s : words) {
			if (isLeft(s.toLowerCase())) {
				if (ret.length() > s.length())
					ret = s;
				else if (ret.equals(""))
					ret = s;
			}
		}
		return ret;
	}

	public static String largestRight(String[] words) {
		String ret = "";
		for (String s : words) {
			if (isRight(s.toLowerCase()) && s.length() > ret.length()) {
				ret = s;
			}
		}
		return ret;
	}

	public static String smallestRight(String[] words) {
		String ret = "";
		for (String s : words) {
			if (isRight(s.toLowerCase())) {
				if (ret.length() > s.length())
					ret = s;
				else if (ret.equals(""))
					ret = s;
			}
		}
		return ret;
	}

	public static String largestOneHandWord(String[] words) {
		String right = largestRight(words);
		String left = largestLeft(words);
		if (right.length() >= left.length())
			return right;
		else
			return left;
	}

	public static String smallestOneHandWord(String[] words) {
		String right = smallestRight(words);
		String left = smallestLeft(words);
		if (left.length() < right.length())
			return left;
		else
			return right;
	}

	// Recursive call that determines if a string is on the left
	// hand side of a keyboard
	private static boolean isLeft(String s) {
		boolean isGood = left.contains(s.subSequence(0, 1));
		if (s.length() < 2 && isGood) {
			return true;
		} else if (isGood) {
			return isLeft(s.substring(1));
		} else
			return false;
	}

	// Recursive call that determines if a string is on the right
	// hand side of a keyboard
	private static boolean isRight(String s) {
		boolean isGood = right.contains(s.subSequence(0, 1));
		if (s.length() < 2 && isGood) {
			return true;
		} else if (isGood) {
			return isRight(s.substring(1));
		} else
			return false;
	}

	public static void main(String args[]) {
		String words[] = { "asds", "wqewsdass", "uyjnjnkhkjlmnjkolmkjk", "as",
				"l", "k" };
		System.out.println(smallestLeft(words));
		System.out.println(smallestRight(words));
		System.out.println(largestLeft(words));
		System.out.println(largestOneHandWord(words));
		System.out.println(largestRight(words));
	}
}