/**
 * Game of master mind: you have four balls, and four different colors, as a solution.
 * The user tries to guess the solution. If they guess the right color for the right spot,
 * it counts as a 'hit'. If it's the right color, but the wrong spot, it counts as a pseudo-hit.
 */

/**
 * @author tgulati
 * 
 */
public class MasterMind {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		char[] solution = { 'r', 'g', 'g', 'b' };
		char[] guess = { 'y', 'r', 'g', 'b' };
		int solution_mask = 0, i, hits = 0, pseudoHits = 0;
		for (i = 0; i < 4; ++i)
			solution_mask = solution_mask | 1 << (solution[i] - 64);
		for (i = 0; i < 4; ++i) {
			if (guess[i] == solution[i]) {
				hits++;
			} else if ((solution_mask & (1 << (guess[i] - 64))) >= 1) {
				pseudoHits++;
			}
		}
		System.out.println(hits);
		System.out.println(pseudoHits);
		hits = pseudoHits = 0;
		int[] arr = new int[256];
		// approach 2
		for (i = 0; i < 4; i++)
			// initialize all char in solution to set
			arr[solution[i]] = 1;
		for (i = 0; i < 4; i++) {
			if (guess[i] == solution[i]) // if match then increment hit
				hits++;
			else
				arr[guess[i]]++; // no match increment guess array index by 1
		}
		for (i = 0; i < 4; i++) {
			if (arr[solution[i]] > 1) {
				pseudoHits++;
				arr[solution[i]]--;
			}
		}
		System.out.println(hits);
		System.out.println(pseudoHits);
	}

}
