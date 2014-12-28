import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an int array which might contain duplicates, find the largest subset of it which form a sequence. 
 Eg. {1,6,10,4,7,9,5} 
 then ans is 4,5,6,7 
 */

/**
 * @author tgulati
 * 
 */
public class LongestSequenceSubArray {

	public int[] longestConsecutiveSequence(int[] a) {
		int first = Integer.MAX_VALUE; // the first number of the maximum
										// consecutive sequence
		int length = 0; // the length of the maximum consecutive sequence
		Map<Integer, Integer> table = new HashMap<Integer, Integer>();
		for (int i : a) {
			if (!table.containsKey(i)) {
				int start = i;
				int end = i;
				if (table.containsKey(i + 1) && table.get(i + 1) >= i + 1) {
					end = table.get(i + 1);
					table.remove(i + 1);
					table.remove(end);
				}
				if (table.containsKey(i - 1) && table.get(i - 1) <= i - 1) {
					start = table.get(i - 1);
					table.remove(i - 1);
					table.remove(start);
				}
				table.put(start, end);
				table.put(end, start);
				if (end - start + 1 > length) {
					first = start;
					length = end - start + 1;
				}
			}
			System.out.println(table);
		}
		System.out.println(table);
		System.out.println(length);
		int[] s = new int[length];
		for (int i = 0; i < length; i++){
			s[i] = first + i;
		}
		return s;
	}

	public static void main(String[] args) {
		LongestSequenceSubArray obj = new LongestSequenceSubArray();
		int a[] = { 1, 6, 10, 4, 7, 9, 5 };
		System.out.println(Arrays.toString(obj.longestConsecutiveSequence(a)));
	}

}
