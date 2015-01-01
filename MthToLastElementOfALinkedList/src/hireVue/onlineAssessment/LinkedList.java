/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	LinkedList.java, Feb 5, 2014, 9:30:06 PM	
 *  MthToLastElementOfALinkedList, hireVue.onlineAssessment
 *
 */
package hireVue.onlineAssessment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 *  Node Class
 *   this class is used to define the structure of the linked list and
 *   the setters and getters for the node and the data
 */
class Node {
	private Node next;
	private String data;

	public void setData(String data2) {
		data = data2;
	}

	public void setNext(Node newNext) {
		next = newNext;
	}

	public String getData() {
		return data;
	}

	public Node getNext() {
		return next;
	}
}

// -----------------Linked List implementation class----------
class LinkedListImplementation {
	// first defines the very first node that would be added into the linked
	// list
	Node first;

	/*
	 * add(String temp2) temp2 - the element that needs to be added into the
	 * linked list this method iterates to the end of the list and adds the new
	 * element at the end of the list if no element exists in the list, then
	 * first is set to that element
	 */
	void add(String temp2) {
		Node temp = new Node();
		temp.setData(temp2);
		if (first == null) {
			first = temp;
		} else {
			Node prev = new Node();
			prev = first;
			while (prev.getNext() != null) {
				prev = prev.getNext();
			}
			prev.setNext(temp);
		}
	}

	/*
	 * mThToLastElement(int m) m - index of the mth element from the end of the
	 * linked list that needs to be printed to the console we use two nodes to
	 * iterate to the mth to the last element of the list the two nodes are
	 * mThToLast and last, we keep iterating over the linked list till m = 1,
	 * doing so enables us to have a fast node and a slow node separated by a
	 * gap which is equal to m we then iterate over the list and print the data
	 * mThToLast points to. This is the data that was required.
	 */
	void mThToLastElement(int m) {
		Node mThToLast = first;
		Node last = first;
		while (m > 0) {
			last = last.getNext();
			--m;
		}
		while (last.getNext() != null) {
			mThToLast = mThToLast.getNext();
			last = last.getNext();
		}
		System.out.println(mThToLast.getData());

	}
	
	/*
	 * 
	 * boolean hasLoop(Node first)
	 * 
	 */
	boolean hasLoop(Node first) {

	    if(first == null) // list does not exist..so no loop either.
	        return false;

	    Node slow, fast; // create two references.

	    slow = fast = first; // make both refer to the start of the list.

	    while(true) {

	        slow = slow.getNext();          // 1 hop.

	        if(fast.getNext() != null)
	            fast = fast.getNext().getNext(); // 2 hops.
	        else
	            return false;          // next node null => no loop.

	        if(slow == null || fast == null) // if either hits null..no loop.
	            return false;

	        if(slow == fast) // if the two ever meet...we must have a loop.
	            return true;
	    }
	}

	/*
	 * displayList() displays the linked list in order, used for debugging
	 * purposes
	 */
	void displayList() {
		Node temp = new Node();
		temp = first;
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}

}

/*
 * LinkedList is the public class which has the main function defined in it this
 * class runs the code to find the mth to last element in a list
 */
public class LinkedList {

	/*
	 * readFromFile(String fileName) fileName - a string which is the path to
	 * the filename, in our case the file is kept at the project level and hence
	 * only the filename is passed to this function. readFromFile reads each
	 * list from the input file and prints the mth to last element onto the
	 * console/standard output if no index is provided at the end of the list in
	 * each line then it skips and goes to the next line to create a new list
	 * and find the mth to last element for that particular list
	 */
	public static void readFromFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		LinkedListImplementation list = new LinkedListImplementation();
		int listSize = 0;
		try {
			String line = br.readLine();
			while (line != null) {
				String[] fileInputs = line.split(" ");
				listSize = 0;
				for (String temp : fileInputs) {
					list.add(temp);
					listSize++;
					/*
					 * regex to check if the element is not a decimal, not an
					 * integer and also to check if the list size counter is
					 * equal to the list length if yes then there is no index
					 * provided and we skip to the next line containing the new
					 * list that we can parse
					 */
					if (fileInputs.length == listSize
							&& !temp.matches("^-?[0-9]+.[0-9]{1,2}?$")
							&& !temp.matches("^-?[0-9]+$")) {
						line = br.readLine();
					}
					/*
					 * check if the element isn't a decimal, either positive or
					 * negative decimal, if true then read the list on the next
					 * line and proceed
					 */
					if (temp.matches("^-?[0-9]+.[0-9]{1,2}?$")) {
						line = br.readLine();
					}
					/*
					 * check if the element isn't a positive or negative
					 * integer, if true then read the list on the next line and
					 * proceed
					 */
					if (temp.matches("^-?[0-9]+$")) {
						if (Integer.parseInt(temp) < listSize
								&& Integer.parseInt(temp) > 0) {
							list.mThToLastElement(Integer.parseInt(temp));
							line = br.readLine();
						} else {
							line = br.readLine();
						}

					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * call the method readFromFile with the filename of the file containing
		 * the lists as an argument
		 */
		readFromFile("InputElements");
		//readFromFile(args[0]);
	}

}
