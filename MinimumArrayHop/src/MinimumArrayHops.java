import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	MinimumArrayHops.java, Feb 11, 2014, 7:43:37 PM	
 *  MinimumArrayHop, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class MinimumArrayHops {
	public static int jump(ArrayList<Integer> arrayOfNumbers) {  
		int steps = 0;  
		ArrayList<Integer> arrayIndices = new ArrayList<Integer>();
		
		for (int i=0, max=0, next=0; i<arrayOfNumbers.size() && next<arrayOfNumbers.size(); ++i) {  
			max = Math.max(max, i+arrayOfNumbers.get(i));  
			if (i == next) {  // ready to jump  
				if (max == next) return -1; // unreachable  
				next = max;
				arrayIndices.add(i);
				++steps;  
			}  
		}  
		for(int indices : arrayIndices){
			System.out.print(indices);
			System.out.print(" , ");
		}
		System.out.println("out");
		return steps;  
	}


	public static void readFromFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		try {
			String line = br.readLine();
			while (line != null) {
				String[] fileInputs = line.split(" ");
				for (String temp : fileInputs) {
					if(Integer.parseInt(temp) < 0){
						System.out.println("String Failure!!!");
						break;
					}
					if(temp.matches("^-?[0-9]+.[0-9]{1,2}?$")){
						System.out.println("String Failure!!!");
						break;
					}
					//System.out.println(temp);
					numbers.add(Integer.parseInt(temp));
				}
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		/*int sizeOfArray = numbers.size();
		System.out.println(sizeOfArray);
		for(int num : numbers){
			System.out.println(num);
		}*/
		int result;
		result = jump(numbers);
		System.out.println("Minimum number of hops = " + result);
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * call the method readFromFile with the filename of the file containing
		 * the array numbers
		 */
		readFromFile("InputElements");
		//readFromFile(args[0]);
	}

}