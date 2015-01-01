/**
 * @author Tarun Gulati
 *
 */
/**
 * 
 * @author Sneha S.
 * 
 * This class defines the Trinary tree functionalities - Insert, Delete and Display.
 */

import java.util.LinkedList;
import java.util.Queue;

//class to define the node structure
class TrinaryNode {
	int data;
	TrinaryNode left;
	TrinaryNode right;
	TrinaryNode middle;

	public TrinaryNode(int data) {
		this.data = data;
	}
}

// class containing all the relevant methods for Trinary tree.
class Trinary {
	TrinaryNode root;

	public Trinary() {
		root = null;
	}

	// method to insert the nodes in the tree
	public void insert(int val) {
		TrinaryNode node = new TrinaryNode(val);
		if (root == null) {
			root = node;
		} else {
			TrinaryNode current = root;
			TrinaryNode parent = null;
			while (current != null) {
				parent = current;
				if (current.data < val) {
					current = current.right;
				} else if (current.data == val) {
					current = current.middle;
				} else {
					current = current.left;
				}
			}
			if (current == null) {
				// current = node;
				if (parent.data == val)
					parent.middle = node;
				else if (parent.data < val)
					parent.right = node;
				else
					parent.left = node;
				return;
			}
		}
	}

	// method to print the tree nodes
	public void display() {
		TrinaryNode node = root;
		if (root == null)
			return;
		else {

			Queue<TrinaryNode> queue = new LinkedList<TrinaryNode>();
			queue.add(node);
			int count = 1;
			int nodeCount = 0;
			while (!queue.isEmpty()) {
				node = queue.remove();
				count--;
				if (node != null) {
					System.out.print(node.data + " ");
					if (node.left != null) {
						queue.add(node.left);
						nodeCount++;
					}
					if (node.middle != null) {
						queue.add(node.middle);
						nodeCount++;
					}
					if (node.right != null) {
						queue.add(node.right);
						nodeCount++;
					}

					if (count == 0) {
						count = nodeCount;
						nodeCount = 0;
						System.out.println();
					}

				}
			}
		}
		System.out.println();
	}

	// method to find the immediate successor of the given node
	public TrinaryNode FindNextNode(TrinaryNode current) {
		// TrinaryNode parent = current;
		TrinaryNode next = current;
		TrinaryNode parent = current;
		TrinaryNode node = current.right;
		while (node != null) {
			parent = next;
			next = node;
			node = node.left;
		}
		if(parent.left == next) 
			parent.left = null;
		else
			parent.right=null;
		System.out.println("Parent: "+parent.data);
		System.out.println("Successor: "+next.data);
		return next;

	}

	// method to Delete a given node in the tree if present
	public void Delete(int val) {
		TrinaryNode current = root;
		TrinaryNode parent = root;
		boolean leftNode = true;

		while (current.data != val) {
			parent = current;
			if (current.data > val) {

				current = current.left;
				leftNode = true;
			} else if (current.data < val) {
				current = current.right;
				leftNode = false;
			}

			if (current == null) {
				System.out.println("No node found!");
				return;
			}
		}

		// check if leaf node
		if (current.left == null && current.right == null
				&& current.middle == null) {
			if (current == root)
				root = null;
			else {
				if (leftNode)
					parent.left = null;
				else
					parent.right = null;
			}
		}
		// check if middle element is present
		else if (current.middle != null) {
			if (current == root) {
				root = current.middle;
				current.middle.left = current.left;
				current.middle.right = current.right;
			} else {
				if (leftNode) {
					parent.left = current.middle;
				} else
					parent.right = current.middle;

				current.middle.left = current.left;
				current.middle.right = current.right;
			}
		}
		// if node has only left child
		else if (current.left != null && current.right == null) {
			System.out.println("In Here!");
			if (current == root) {
				root = current.left;
			} else {
				System.out.println("yess");
				if (leftNode) {
					parent.left = current.left;
				} else
					parent.right = current.left;
			}
		}
		// if node has only right child
		else if (current.right != null && current.left == null) {
			if (current == root) {
				root = current.right;
			} else {
				if (leftNode) {
					parent.left = current.right;
				} else
					parent.right = current.right;
			}
		}
		// if node has both left and right children
		else {

			TrinaryNode node = FindNextNode(current);

			if (current == root) {
				root = node;
				node.left = current.left;
				node.middle = current.middle;
				node.right = current.right;

			} else {
				if (leftNode) {
					parent.left = node;

				}

				else {
					
					parent.right = node;

				}
				node.left = current.left;
				node.right = current.right;
				node.middle = current.middle;

			}
		}

	}
}

// main class
public class TrinaryTree {

	public static void main(String[] args) {
		Trinary tree = new Trinary();
		int[] numbers = { 5, 4, 9,5, 7, 2, 2, 17, 15 };
		for (int i = 0; i < numbers.length; i++)
			tree.insert(numbers[i]);
		tree.display();
		tree.Delete(17);
	
		tree.Delete(9);
		tree.Delete(5);
		System.out.println("Tree Structure after Deletion");
		tree.display();

	}
}
