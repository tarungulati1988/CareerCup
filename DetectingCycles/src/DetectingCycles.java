/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	DetectingCycles.java, Aug 29, 2013, 1:25:37 PM	
 *  DetectingCycles, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
import java.io.*;
import java.util.ArrayList;

public class DetectingCycles {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File file = new File("checkForCycles");
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line; 
		String contentOfFile[];
		ArrayList<String> sequence;
		sequence = new ArrayList<String>();
		int index = 0;
		while ((line = in.readLine()) != null){	
			contentOfFile=line.split(" ");
			for(String s:contentOfFile){
				if(!sequence.contains(s))
					sequence.add(s);
				else{
					index=sequence.indexOf(s);
					break;
				}
			}
			for(int i=index;i<sequence.size();i++){
				if(i==sequence.size()-1)
					System.out.println(sequence.get(i));
				else
					System.out.print(sequence.get(i)+" ");
			}
		}
	}

}
