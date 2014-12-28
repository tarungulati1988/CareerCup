/**
 * given an array of sorted integers with duplicate values , sort the array so 
 * that there are only unique values left in sorted order
 *  ( do not use any additional data type , do inplace sort )
 */

/**
 * @author tgulati
 * 
 */
public class DuplicateRemoval {
	public static void main(String[] args) {
		int a[] = { 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6, 6, 7, 7, 7,
				8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
		int j = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1] != a[i]) {
				a[j] = a[i];
				j++;
			}
		}

		for (int k = 0; k < j; k++) {
			System.out.print(a[k]);
		}
	}
}