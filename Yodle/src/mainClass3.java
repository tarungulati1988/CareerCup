/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	mainClass3.java, Mar 25, 2014, 6:54:31 PM	
 *  Yodle, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class mainClass3 {

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
			int[] newLine = rowReduce(inputTriangle.get( j - 1 ), inputTriangle.get(j));
			System.out.println();
			for(int i : newLine)
				System.out.print(i + "     ");
			inputTriangle.set( j - 1 ,newLine);
			System.out.println("j-1 = " + (j-1));
		}
		System.out.println("Answer: "+inputTriangle.get(0)[0]);
		
	}
	
	public static int[] rowReduce(int[] aboveRow, int[] currRow){
		int larger;
		int[] mergedRows = new int[aboveRow.length];
		for (int i=0; i<aboveRow.length; i++){
			larger = Math.max(currRow[i]+aboveRow[i], currRow[i+1]+aboveRow[i]);
			mergedRows[i] = larger;
		}
		/*for(int i : mergedRows){
			System.out.println(i);
		}*/
		return mergedRows;
	}

}