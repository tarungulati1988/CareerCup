/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	TriangleTest.java, Mar 25, 2014, 6:39:10 PM	
 *  Yodle, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
import java.util.*;
import java.io.*;
import java.lang.Math;

public class TriangleTest {

	
	public static void main(String[] args) {
		// TODO code application logic here

		long startTime = System.currentTimeMillis();

		ArrayList<Integer> sav_info = new ArrayList<Integer>();

		try {

			File file = new File("triangle.txt");
			if (file.exists()) {

				FileReader l_fr = new FileReader(file);
				LineNumberReader l_lnr = new LineNumberReader(l_fr);
				//BufferedReader l_lnr = new BufferedReader(l_fr);
				
				String input_str;
				int num_line = 0;
				while ((input_str = l_lnr.readLine()) != null) {
					String[] values = input_str.split(" ");
					int num_input = values.length;
					if (num_input != num_line + 1) {
						// something wrong here
						System.out.println("Error in input file");
						throw new IllegalArgumentException("Invalid Input File");
					}
					if (num_line == 0) {
						// first line
						sav_info.add(Integer.parseInt(values[0]));
						num_line++;
						continue;
					}
					// for all the input with total number more than 1
					int prev_value = sav_info.get(0);
					sav_info.set(0, Integer.parseInt(values[0]) + prev_value);

					for (int i = 1; i < num_input - 1; i++) {
						int value = Integer.parseInt(values[i]);
						int cur = sav_info.get(i);
						int tmp_max = Math.max(value + prev_value, value + cur);
						prev_value = cur;
						sav_info.set(i, tmp_max);
					}

					sav_info.add(prev_value
							+ Integer.parseInt(values[num_input - 1]));
					num_line++;
				}
				l_lnr.close();

				int max_sum = Integer.MIN_VALUE;

				for (int i = 0; i < sav_info.size(); i++) {
					if (sav_info.get(i) > max_sum)
						max_sum = sav_info.get(i);
				}

				System.out.println("Max is " + max_sum);
			} else {
				System.out.println("File does not exists!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Run time: " + totalTime + " Millis");
	}
}