import java.util.ArrayList;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	DerivedClass.java, Apr 6, 2014, 8:40:15 PM	
 *  MarkitOnDemand, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class DerivedClass extends AverageNumberBaseClass{

	/* (non-Javadoc)
	 * @see AverageNumberBaseClass#average()
	 */
	@Override
	public double average() {
		ArrayList<Double> collectionArray = new ArrayList<Double>();
		collectionArray = AverageNumberBaseClass.getCollection();
		double avg = 0;
		double sum = 0;
		int size = collectionArray.size();
		for(int i = 0 ; i < size ; i++){
			if(collectionArray.get(i) > 0.00)
				sum += collectionArray.get(i);
		}
		avg = sum/size;
		return avg;
	}

}
