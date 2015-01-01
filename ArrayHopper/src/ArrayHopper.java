import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 
 * author: Tarun Gulati, tgulati@indiana.edu ArrayHopper.java, Feb 11, 2014,
 * 6:40:05 PM ArrayHopper,
 * 
 */

public class ArrayHopper {

	/*
	 * jump(ArrayList<Integer> arrayOfNumbers)
	 * arrayOfNumbers - is the array containing all the numbers that are present in the file
	 * this method calculates the minimum number of jumps required to reach the end of the array
	 */
	public static int jump(ArrayList<Integer> arrayOfNumbers) {
		ArrayList<Integer> arrayIndices = new ArrayList<Integer>();
		int max = 0;
		int jumps = 0;
		int next = 0;
		for (int i = 0; i < arrayOfNumbers.size(); i++) {
			max = max >= (i + arrayOfNumbers.get(i)) ? max : (i + arrayOfNumbers.get(i));
			if (i == next) {
				if (max == next) {
					System.out.println("failure");
					return -1;
				}
				next = max;
				++jumps;
				arrayIndices.add(i);
			}
		}
		for (int indices : arrayIndices) {
			System.out.print(indices);
			System.out.print(" , ");
		}
		System.out.print("out");
		return jumps;
	}

	/*
	 * readFromFile(String fileName)
	 * fileName is the name of the file passed to this method as an argument
	 * build the array based on the contents of the file
	 * once the array has been built successfully it call the jump method to get the minimum distance
	 */
	public static void readFromFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try {
			String line = br.readLine();
			while (line != null) {
				if (Integer.parseInt(line.toString()) < 0) {
					System.out.println("failure");
					break;
				}
				if (line.toString().matches("^-?[0-9]+.[0-9]{1,2}?$")) {
					System.out.println("failure");
					break;
				}
				numbers.add(Integer.parseInt(line.toString()));
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

		int jumps = jump(numbers);

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * call the method readFromFile with the filename of the file containing
		 * the array numbers
		 */
		readFromFile(args[0]);
	}

}
