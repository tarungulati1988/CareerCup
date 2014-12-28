/**
 * Design a hash table to store phone #s. Your job is to write a hash function 
 * that has a parameter username, and generate a key. Username is unique, 
 * length 5 and can be A-Z, 0-9, space. Write a hash function that generate 
 * keys without collisions and use minimum memory.
 */

/**
 * @author tgulati
 * 
 */
public class HashCodePhoneBook {

	static char[] arr = {'Z', '1', '0', ' ', 'A'};
	static int i, j = 4, total = 0;
	static double x;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//arr = {'z', '1', '0', ' ', 'a'};
		hashcode();
		System.out.println(total);
	}

	public static void hashcode() {
		for (i = 0; i < 5; i++) {
			if (arr[i] == ' ') // to caovert space to 0
				x = 0;

			if (arr[i] >= '0' && arr[i] <= '9') // to convert ascii of 0-9 to
												// 1-10
				x = (arr[i] - 47);

			if (arr[i] >= 'A' && arr[i] <= 'Z') // to convert ascii of A-Z to
												// 11-36
				x = (arr[i] - 54);

			x = x * Math.pow(37, j);
			total = total + (int) x;
			j--;
		}
	}

}
