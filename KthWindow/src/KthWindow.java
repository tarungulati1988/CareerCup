import java.util.ArrayList;
import java.util.List;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	KthWindow.java, Mar 15, 2014, 7:37:52 PM	
 *  KthWindow, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class KthWindow {

	
	public static List<Integer> calculateWindowSums(List<Integer> list, int windowSize) {
		List<Integer> tempList = new ArrayList<Integer>();
		int count = 0, k, sum;
		for(int i : list){
			if((count + windowSize - 1) < list.size()){
				sum = 0;
				for(k = count ; k < (count + windowSize) ; k++){
					sum += list.get(k); 
				}
				tempList.add(sum);
				count++;
			}
			
		}
		return tempList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list1 = new ArrayList();
		list1.add(4);
		list1.add(2);
		list1.add(73);
		list1.add(11);
		list1.add(-5);
		List<Integer> list2 = new ArrayList();
		list2 = calculateWindowSums(list1, 3);
		for(int j : list2){
			System.out.println(j);
		}
	}

}
