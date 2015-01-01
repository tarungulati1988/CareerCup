/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu, tarun.gulati1988@gmail.com
 *	SecretSantaSolution.java, Feb 24, 2014, 11:12:15 AM	
 *  GoHealthCodingChallenge, 
 *
 */


public class SecretSantaSolution {


	/*
	 * hashFunction(int end, int primeNumber, int location)
	 * returns - a unique hashcode representing the array index in the new array
	 * using a prime number which is > twice the size of the array guarantees minimum/no
	 * collisions in the resulting hash codes
	 */
	public int hashFunction(int end, int primeNumber, int location) {
		return ((end - location) % primeNumber);
	}

	/*
	 * isPrime(int number)
	 * given  a number is returns true is it is prime or else false
	 */
	public boolean isPrime(int number) {
		if (number % 2 == 0)
			return false;
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/*
	 * generatePrimeNumber(int size)
	 * size - the size of the array halves
	 * returns a prime number which is greater than twice the size of the half of the array
	 */
	public int generatePrimeNumber(int size) {
		size = size *2;
		for (int i = size ; true ; i++) {
			if (isPrime(i))
				return i;
		}
	}

	/*
	 * 
	 * String[] generateAssignments(final String[] participants)
	 * String[] participants - array of strings containing the names of all the participants
	 * return - array of generated participant names for secret santa
	 * logic - split the array in two halves, apply a hashing function on both the halves and swap
	 * two sub arrays to get a new generated list of names for secret santa, all the mappings are
	 * unique and it guarantees a mapping for all the participants, no participant is mapped
	 * to himself in the new list of names.
	 * 
	 */
	public String[] generateAssignments(final String[] participants){
		if(participants.length > 1){
			final String[] generatedAssignmentArray = new String[participants.length];

			int mid = (participants.length-1)/2;
			int start = 0;
			int end = participants.length - 1;

			int primeNumberSub1 = generatePrimeNumber((mid - start));
			int primeNumberSub2 = generatePrimeNumber((end - mid));
			// check is the size of the array is even
			if(((participants.length) % 2) == 0){
				for(int i = 0 ; i < participants.length/2 ; i++){
					generatedAssignmentArray[hashFunction(mid, primeNumberSub1, i) + (mid + 1)] = participants[i];
				}
				for(int j = participants.length/2 ; j < participants.length ; j++){
					generatedAssignmentArray[(hashFunction(end, primeNumberSub2, j)+mid+1) - (mid + 1)] = participants[j];
				}
			}// check if the size of the array is odd
			else{
				for(int i = 0 ; i <= participants.length/2 ; i++){
					// handle the special case when the array length is odd, this
					// would cause the shuffled array to have the middle element placed at 
					// middle again, place it at the starting of the generatedAssignments manually
					if((hashFunction(mid, primeNumberSub1, i) + (mid)) == mid){
						generatedAssignmentArray[0] = participants[i];
					}
					generatedAssignmentArray[hashFunction(mid, primeNumberSub1, i) + (mid)] = participants[i];
				}
				for(int j = (participants.length/2) +1 ; j < participants.length ; j++){
					generatedAssignmentArray[(hashFunction(end, primeNumberSub2, j)+mid+1) - (mid)] = participants[j];

				}
			}
			return generatedAssignmentArray;
		}
		return null;

	}

	public static void main(String[] args) {
		SecretSantaSolution secretSantaObj = new SecretSantaSolution();

		final String[] participants = {"Kyle" , "Kenny" , "Eric" , "Stan" , "Stewie" , "David" , "Brian"};


		String[] generatedMappings = secretSantaObj.generateAssignments(participants);

		if(generatedMappings != null){
			for(int i = 0 ; i < generatedMappings.length ; i++){
				System.out.println(participants[i] + " --> " + generatedMappings[i]);
			}
		}else{
			System.out.println("No mappings.");
		}

	}

}
