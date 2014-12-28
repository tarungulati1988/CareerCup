/**
 * A person can take m steps at a time to reach a particular floor( say in a building). 
 * How many different ways can a person reach the nth floor?
 */

/**
 * @author tgulati
 * 
 */
public class NStairs {

	public static int n;
	public static int m;

	public static int Fibonacci(int n) {
		int total = 0, i;
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		for (i = 1; i <= m && i <= n; i++)
			total += Fibonacci(n - i); // for m steps
		return total;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		n = 4;
		m = 2;
		System.out.println(Fibonacci(n+1));
	}

}
