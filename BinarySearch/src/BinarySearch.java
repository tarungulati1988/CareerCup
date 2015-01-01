/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	BinarySearch.java, Feb 25, 2014, 3:18:16 PM	
 *  BinarySearch, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class BinarySearch {

	public int binarySearchRecursive(int A[], int key, int imin, int imax)
	{
	  // test if array is empty
	  if (imax < imin){
		// set is empty, so return value showing not found
		  System.out.println("Key not found!!!");
	    return -1; 
	  }
	    
	  else
	    {
	      // calculate midpoint to cut set in half
	      int imid = (imin + imax) / 2;
	 
	      // three-way comparison
	      if (A[imid] > key)
	        // key is in lower subset
	        return binarySearchRecursive(A, key, imin, imid-1);
	      else if (A[imid] < key)
	        // key is in upper subset
	        return binarySearchRecursive(A, key, imid+1, imax);
	      else
	        // key has been found
	        return imid;
	    }
	}
	
	int binarySearchIterative(int A[], int key, int imin, int imax)
	{
	  // continue searching while [imin,imax] is not empty
	  while (imax >= imin)
	    {
	      // calculate the midpoint for roughly equal partition
	      int imid = (imin + imax) / 2;
	      if(A[imid] == key)
	        // key found at index imid
	        return imid; 
	      // determine which subarray to search
	      else if (A[imid] < key)
	        // change min index to search upper subarray
	        imin = imid + 1;
	      else         
	        // change max index to search lower subarray
	        imax = imid - 1;
	    }
	  // key was not found
	  return -1;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearch obj = new BinarySearch();
		int[] sortedArray = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13};
		int keyIndexRecursive = obj.binarySearchRecursive(sortedArray, 13, 0, sortedArray.length-1);
		if(keyIndexRecursive != -1){
			System.out.println("key index is: " + keyIndexRecursive);
		}
		
		int keyIndexIterative = obj.binarySearchIterative(sortedArray, 13, 0, sortedArray.length-1);
		if(keyIndexIterative != -1){
			System.out.println("key index is: " + keyIndexIterative);
		}
	}

}
