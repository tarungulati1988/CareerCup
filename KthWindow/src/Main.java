import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	Main.java, Mar 15, 2014, 8:02:28 PM	
 *  KthWindow, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class Main {
	public static List<Integer> findIntersection(List<Integer> a, List<Integer> b) {
	    Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
	    List<Integer> intersection = new ArrayList<Integer>();
	    for(int i = 0; i < a.size(); i++) {
	    	if(a.get(i) != null) {
		        if(hashTable.get(a.get(i)) != null) {
		            hashTable.put(a.get(i), hashTable.get(a.get(i)) + 1);
		        } else {
		            hashTable.put(a.get(i), 1);
		        }
	    	}
	    }
	    
	    for(int i = 0; i < b.size(); i++) {
	    	int bValue = b.get(i);
	    	int value;
	        if(hashTable.get(bValue) != null) {
	        	value = hashTable.get(bValue);
	        } else {
	        	continue;
	        }
	        
		    if(hashTable.get(bValue)!= 0) {
		        hashTable.put(bValue, value - 1); 
		        intersection.add(bValue);
		    }
	    }
	    return intersection;
	}
	
	public static void main(String[] args) {
		//Test list and tree
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		a1.add(4);
		a1.add(2);
		a1.add(73);
		a1.add(11);
		a1.add(-5);
		
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		a2.add(-5);
		a2.add(73);
		a2.add(-1);
		a2.add(9);
		a2.add(9);
		a2.add(4);
		a2.add(7);
		
		List<Integer> a3 = findIntersection(a1, a2);
		Collections.sort(a3);
		System.out.println(a3.toString());
	}
}