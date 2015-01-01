/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	KthLargest.java, Apr 21, 2014, 3:46:47 PM	
 *  KthLargest, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class KthLargest {

	public static void main(String[] args) {
		int a[] = { 1, 6, 2, 9, 13, 5, 3, 8 };
		int k = 7;
		int index = findKth(a,0, a.length -1, k);
		System.out.println(k+ "th largest element: "+ a[index]);

	}

	private static int findKth(int[] a, int low, int high, int k) {

		int partitionIndex = partition(a,low,high);
		if( k == partitionIndex - low) {
			return partitionIndex;
		} else if(k < partitionIndex){
			return findKth(a, low, partitionIndex-1, k);
		} else if(k> partitionIndex) {
			return findKth(a, partitionIndex+1, high, k-(partitionIndex+1));
		}
		return partitionIndex;
	}

	private static int partition(int[] a, int low, int high) {
		if(low<high){
		int pIndex = Math.abs((low + high) / 2);
		int pivot = a[pIndex];
		swap(a, pIndex, high);

		int i = low;
		int j = high - 1;
		while (i <= j) {
			while (i < high && a[i] <= pivot) {
				i++;
			}
			while (j >= low && a[j] >= pivot) {
				j--;
			}
			if (i < j) {
				swap(a, i, j);
				i++;
				j--;
			}
		}
		swap(a, i, high);
		return i;
		} else {
			return low;
		}
	}
	
	private static void swap(int[] a, int i, int pIndex) {
		int t = a[i];
		a[i] = a[pIndex];
		a[pIndex] = t;
		
	}
	
}