/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu 
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Triangle {

	public static void main (String[] args) throws FileNotFoundException{
		
		Scanner scanLines = new Scanner(new File("triangle.txt"));
		
		//arraylist will contain the triangle values
		ArrayList<int[]> inputTriangle = new ArrayList<int[]>();
		
		// to hold array of numbers in each line of the triangle in int format
		int[] intTemp;
		
		// to hold array of numbers in each line of the triangle in string format, converted to int later
		String[] strTemp;
		
		// count for number of lines in the triangle
		int count = 1;
		String line;
		
		while (scanLines.hasNextLine()){
			line = scanLines.nextLine();
			intTemp = new int[count];
			strTemp = new String[count];	
			strTemp = line.split(" ");
			for (int i=0; i<strTemp.length; i++){
				intTemp[i] = Integer.parseInt(strTemp[i]);
			}
			inputTriangle.add(intTemp);
			count++;
		}

		for (int j = inputTriangle.size() - 1 ; j > 0 ; j--){
			int[] newLine = calculateMax(inputTriangle.get( j - 1 ), inputTriangle.get(j));
			inputTriangle.set(j - 1 , newLine);
		}
		System.out.println("Maximum total: " + inputTriangle.get(0)[0]);
	}
	
	public static int[] calculateMax(int[] previousRow, int[] currentRow){
		int larger;
		int[] mergedRows = new int[previousRow.length];
		for (int i = 0 ; i < previousRow.length ; i++){
			larger = Math.max(currentRow[i]+previousRow[i], currentRow[i+1]+previousRow[i]);
			mergedRows[i] = larger;
		}
		return mergedRows;
	}

}