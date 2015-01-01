import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Tarun Gulati
 * 
 *         Question 2) Implement insert and delete in a tri-nary tree. A
 *         tri-nary tree is much like a binary tree but with three child nodes
 *         for each parent instead of two -- with the left node being values
 *         less than the parent, the right node values greater than the parent,
 *         and the middle nodes values equal to the parent.
 * 
 */

/*
 * Node class defines the left, middle and the right node along with the integer
 * datatype and their respective getters and setters
 */
class Node {
	Node leftChild;
	Node middleChild;
	Node rightChild;

	int data;

	/**
	 * @return the leftChild
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild
	 *            the leftChild to set
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the middleChild
	 */
	public Node getMiddleChild() {
		return middleChild;
	}

	/**
	 * @param middleChild
	 *            the middleChild to set
	 */
	public void setMiddleChild(Node middleChild) {
		this.middleChild = middleChild;
	}

	/**
	 * @return the rightChild
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild
	 *            the rightChild to set
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}
}

class Tree {

	Node root;

	public void insert(int key) {
		Node temp = new Node();
		temp.setData(key);
		if (root == null) {
			root = temp;
		} else {
			Node focusNode = root;
			Node parent = null;
			while (true) {
				parent = focusNode;
				if (key < focusNode.getData()) {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = temp;
						return;
					}

				} else if (key == focusNode.getData()) {
					focusNode = focusNode.middleChild;
					if (focusNode == null) {
						parent.middleChild = temp;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if (focusNode == null) {
						parent.rightChild = temp;
						return;
					}
				}
			}
		}
	}

	public void displayTree() {
		Node temp = root;
		if (root == null)
			return;
		else {
			LinkedList<Node> listOfRootNodes = new LinkedList<Node>();
			listOfRootNodes.add(temp);
			int count = 1;
			int nodeCount = 0;
			while (!listOfRootNodes.isEmpty()) {
				temp = listOfRootNodes.remove();
				count--;
				if (temp != null) {
					System.out.print("  " + temp.getData() + "   ");
					if (temp.leftChild != null) {
						listOfRootNodes.add(temp.leftChild);
						nodeCount++;
					}if (temp.middleChild != null) {
						listOfRootNodes.add(temp.middleChild);
						nodeCount++;
					}if (temp.rightChild != null) {
						listOfRootNodes.add(temp.rightChild);
						nodeCount++;
					}if (count == 0) {
						count = nodeCount;
						nodeCount = 0;
						System.out.println();
					}
				}
			}
		}
	}

	public Node getReplacementNode(Node replacedNode) {
		// returns the immediate successor of a given node
		Node next = replacedNode;
		Node parent = replacedNode;
		Node node = replacedNode.rightChild;
		while (node != null) {
			parent = next;
			next = node;
			node = node.leftChild;
		}
		if (parent.leftChild == next)
			parent.leftChild = null;
		else
			parent.rightChild = null;
		return next;
	}

	public boolean delete(int key) {
		Node focusNode = root;
		Node parent = root;
		boolean isItALeftChild = true;

		// check if the node exists in the left of the root or right of the root
		while (focusNode.getData() != key) {
			parent = focusNode;
			if (key < focusNode.getData()) {
				// Shift the focus Node to the left child
				isItALeftChild = true;
				focusNode = focusNode.leftChild;
			} else {
				// Shift the focus Node to the right child
				isItALeftChild = false;
				focusNode = focusNode.rightChild;
			}
			if (focusNode == null) {
				System.out.println("No node found.");
				return false;
			}
		}

		// If Node doesn't have children delete it
		if (focusNode.leftChild == null && focusNode.rightChild == null) {
			// If root delete it
			if (focusNode == root)
				root = null;
			else if (isItALeftChild)
				parent.leftChild = null; // if it is marked as left child of the
											// parent then delete it
			else
				parent.rightChild = null; // if it is marked as right child of
											// the parent then delete it
		} else if (focusNode.middleChild != null) { // if middle child is
													// present
			if (focusNode == root) {
				root = focusNode.middleChild;
				focusNode.middleChild.leftChild = focusNode.leftChild;
				focusNode.middleChild.rightChild = focusNode.rightChild;
			} else {
				if (isItALeftChild) {
					parent.leftChild = focusNode.middleChild;
				} else
					parent.rightChild = focusNode.middleChild;
				focusNode.middleChild.leftChild = focusNode.leftChild;
				focusNode.middleChild.rightChild = focusNode.rightChild;
			}
		} else if (focusNode.rightChild == null) {
			// if no right child
			if (focusNode == root)
				root = focusNode.leftChild;
			else if (isItALeftChild)
				parent.leftChild = focusNode.leftChild;
			else
				parent.rightChild = focusNode.leftChild;
		}
		// If no left child
		else if (focusNode.leftChild == null) {
			if (focusNode == root)
				root = focusNode.rightChild;
			else if (isItALeftChild)
				parent.leftChild = focusNode.rightChild;
			else
				parent.rightChild = focusNode.rightChild;
		}
		// if the node has both right and left child
		else {
			Node replacement = getReplacementNode(focusNode);
			if (focusNode == root) {
				root = replacement;
				replacement.leftChild = focusNode.leftChild;
				replacement.middleChild = focusNode.middleChild;
				replacement.rightChild = focusNode.rightChild;
			} else if (isItALeftChild)
				parent.leftChild = replacement;
			else
				parent.rightChild = replacement;

			replacement.leftChild = focusNode.leftChild;
			replacement.middleChild = focusNode.middleChild;
			replacement.rightChild = focusNode.rightChild;
		}
		return true;
	}
}

/*
 * TrinaryTreesolution class includes the main which would be used to call the
 * insert, delete, display
 */
public class TrinaryTreeSolution {

	public static void main(String[] args) {
		Tree treeObj = new Tree();
		// adding nodes to the tree
		treeObj.insert(5);
		treeObj.insert(4);
		treeObj.insert(9);
		treeObj.insert(5);
		treeObj.insert(7);
		treeObj.insert(2);
		treeObj.insert(2);

		System.out.println("----------------------------");
		treeObj.displayTree();
		System.out.println("----------------------------");

		System.out.println(treeObj.delete(9));
		System.out.println("Tree after deleting 9");
		treeObj.displayTree();
		System.out.println("----------------------------");

		System.out.println(treeObj.delete(5));
		System.out.println("Tree after deleting 5");
		treeObj.displayTree();

		System.out.println("----------------------------");
		System.out.println(treeObj.delete(15));
		System.out.println("Tree after deleting 15"); // tree structure remains
														// the same as no node
														// with data = 15 exists
														// in the tree
		treeObj.displayTree();

		System.out.println("----------------------------");
		System.out.println(treeObj.delete(5));
		System.out.println("Tree after deleting 5");
		treeObj.displayTree();
	}

}
