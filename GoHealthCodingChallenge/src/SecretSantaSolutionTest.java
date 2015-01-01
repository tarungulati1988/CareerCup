import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu, tarun.gulati1988@gmail.com
 *	SecretSantaSolutionTest.java, Feb 24, 2014, 11:16:03 AM	
 *  GoHealthCodingChallenge, 
 *
 */

public class SecretSantaSolutionTest {
	final String[] participants = {"Kyle" , "Kennie" , "Stewie" , "Stan" , "Brian"};
	final String[] generatedAssignments = {"Brian" , "Stan" , "Kennie" , "Stewie" , "Kyle"};
	SecretSantaSolution testObj = new SecretSantaSolution();


	@Test
	public void testSecretSantaGeneratedArray() {
		/*
		 * Test 1
		 */
		for(int i  = 0 ; i < participants.length ; i++ ){
			assertNotSame(participants[i], generatedAssignments[i]);
		}

	}

	@Test
	public void testArrayNotNull(){
		/*
		 * Test 2
		 */
		assertNotNull(participants);
		assertNotNull(generatedAssignments);
	}

	@Test
	public void testArrayLengthsEqual(){
		/*
		 * Test 3
		 */

		assertEquals(participants.length, generatedAssignments.length);
	}


	@Test
	public void testIsPrime(){
		/*
		 * Test 4
		 */

		boolean isItPrime = testObj.isPrime(7);
		assertEquals(true, isItPrime);
	}

	@Test
	public void testGeneratePrime(){
		/*
		 * Test 5
		 */

		int primeNumber = testObj.generatePrimeNumber(participants.length);
		assertEquals(11, primeNumber);
	}
	
	
}
