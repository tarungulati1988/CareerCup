/**
 * if an array is given like 11 
increment its value to 1 
o\p should be 12 

if the array has 99 
o\p should be 100 
 */

/**
 * @author tgulati
 * 
 */
public class IncrementValueInArray {
	public static Integer[] plusOne(final Integer[] input) {
		int carry = 1;
		for (int i = input.length - 1; i >= 0; i--) {
			int sum = input[i] + carry;
			carry = sum / 10;
			input[i] = carry > 0 ? sum % carry : sum;
		}
		if (carry != 0) {
			Integer[] result = new Integer[1 + input.length];
			result[0] = carry;
			for (int j = 0; j < input.length; j++) {
				result[j + 1] = input[j];
			}
			return result;
		}
		return input;
	}

	private static void printArray(final Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		printArray(plusOne(new Integer[] { 9, 9 }));
		printArray(plusOne(new Integer[] { 8, 2 }));
		printArray(plusOne(new Integer[] { 1, 8, 2 }));
		printArray(plusOne(new Integer[] { 1, 9, 9 }));
		printArray(plusOne(new Integer[] { 9, 9, 9 }));
	}
}