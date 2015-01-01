/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	MaxDifference.java, Mar 2, 2014, 5:53:14 PM	
 *  MaxDifference, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class MaxDifference {

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int seed[] = {2, 3, 10, 2, 4, 8, 1};//2 3 10 2 4 8 1
		int maxDiff=-2147483648, minNumber = 2147483647;
		System.out.println(maxDiff);
		System.out.println(minNumber);
		for (int i = 0; i < seed.length ; i++){
		    if(minNumber > seed[i]) 
		    minNumber = seed[i];

		    maxDiff = Math.max(maxDiff, (seed[i]-minNumber));
		}
		System.out.println(maxDiff);
	}

}
