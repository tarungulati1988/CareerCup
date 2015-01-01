import java.util.ArrayList;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	AverageNumberBaseClass.java, Apr 6, 2014, 4:20:18 PM	
 *  MarkitOnDemand, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class AverageNumberBaseClass {
	private static ArrayList<Double> numCollection = new ArrayList<Double>();
	private static AverageNumberBaseClass avgObj2;
	
	public AverageNumberBaseClass(ArrayList<Double> listOfItems){
		this.numCollection = listOfItems;
	}
	
	public AverageNumberBaseClass(){
		super();
	}
	
	public AverageNumberBaseClass(AverageNumberBaseClass avergareNumberObj){
		this.avgObj2 = avergareNumberObj;
	}
	
	public void addElements(double n){
		this.numCollection.add(n);
	}
	
	public double[] highestAndLowest(){
		double[] highLow = new double[2];
		if(numCollection.size() > 0){
			highLow[0] = numCollection.get(0);
			highLow[1] = numCollection.get(0);
			for(int i = 0 ; i < numCollection.size() ; i++){
				if(numCollection.get(i) < highLow[0])
					highLow[0] = numCollection.get(i);
				if(numCollection.get(i) > highLow[1])
					highLow[1] = numCollection.get(i);
			}
			return highLow;
		}
		return null;
	}
	
	public double average(){
		double avg = 0;
		double sum = 0;
		int size = numCollection.size();
		for(int i = 0 ; i < size ; i++){
			sum += numCollection.get(i);
		}
		avg = sum/size;
		return avg;
	}
	
	public static ArrayList<Double> getCollection(){
		return numCollection;
	}
	
	public void changeCollectionValues(ArrayList<Double> list, double value){
		for(int i = 0 ; i < list.size() ; i++){
			list.set(i, value);
		}
		
	}
}
