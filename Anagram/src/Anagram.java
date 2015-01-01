/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Anagram.java, Mar 3, 2014, 11:46:33 AM	
 *  Anagram, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class Anagram {

	public static boolean anagram(String s, String t){
		if(s.length() != t.length())
			return false;
		int[] letters = new int[256];
		int num_unique_chars = 0;
		int num_completed_t = 0;
		char[] s_array = s.toCharArray();
		for(char c : s_array){
			if(letters[c] == 0)
				++num_unique_chars;
			++letters[c];
			System.out.println(letters[c] + "  " + c);
		}
		System.out.println((int)'g');
		System.out.println(letters[0]);
		System.out.println(letters[103]);
		System.out.println(letters[01]);
		for(int i = 0 ; i < t.length() ; i++){
			int c = (int)t.charAt(i);
			if(letters[c] == 0)
				return false;
			--letters[c];
			if(letters[c] == 0){
				++num_completed_t;
				if(num_completed_t == num_unique_chars){
					System.out.println(num_completed_t);
					return i == t.length() - 1;
				}
					
			}
		}
		return false;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(anagram("ggod", "gdog"));
	}

}
