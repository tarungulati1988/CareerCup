import java.util.Arrays;

/**
 * You have an array containing only '0's, '1's and '2's. Club same items together in single scan.
 * 0 - white
 * 1 - red
 * 2 - blue
 */

/**
 * @author tgulati
 * 
 */
public class DutchFlagProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] unsortedArr = {0,1,0,2,2,0,1,1,0,0,1,0,2,2,0,1,1,0};
		club012(unsortedArr);
	}

	/**
	 * @param unsortedArr
	 */
	private static void club012(int[] unsortedArr) {
		// TODO Auto-generated method stub
		int i = 0, j = 0, k = (unsortedArr.length - 1);
		int temp;
		while ( j <= k ) {
			if ( unsortedArr[j] == 0) {
				temp = unsortedArr[i];
				unsortedArr[i] = unsortedArr[j];
				unsortedArr[j] = temp;
				i++;
				j++;
			} else if (unsortedArr[j] == 1) {
				j++;
			} else if (unsortedArr[j] == 2) {
				temp = unsortedArr[j];
				unsortedArr[j] = unsortedArr[k];
				unsortedArr[k] = temp;
				k--;
			}
		}
		System.out.println(Arrays.toString(unsortedArr));
	}

}
