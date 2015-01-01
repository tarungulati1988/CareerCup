import java.util.ArrayList;
import java.util.Random;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	MainClass.java, Apr 6, 2014, 4:21:06 PM	
 *  MarkitOnDemand, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class MainClass {

	//public ArrayList<Double> numberCollection = new ArrayList<Double>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Double> numberCollection = new ArrayList<Double>();
		Random r = new Random();
		
		int rangeMin = -1000;
		int rangeMax = 1000;
		double randomValue;
		for(int i  = 0; i < 100 ; i++){
			randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
			numberCollection.add(randomValue);
			//System.out.println(randomValue);
		}
		
		AverageNumberBaseClass averageNumObj = new AverageNumberBaseClass(numberCollection);
		double average = averageNumObj.average();
		System.out.println("Average: " + average);
		double[] highAndLow = averageNumObj.highestAndLowest();
		averageNumObj.changeCollectionValues(numberCollection, 19.02938);
		highAndLow = averageNumObj.highestAndLowest();
		//averageNumObj.addElements(20000.099887);
		highAndLow = averageNumObj.highestAndLowest();
		AverageNumberBaseClass obj = new AverageNumberBaseClass();
		obj.average();
		DerivedClass derivedClassObj = new DerivedClass();
		double avg = derivedClassObj.average();
		System.out.println("Average: " + avg);
	}

}
