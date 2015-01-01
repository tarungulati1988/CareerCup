import java.util.HashMap;

/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	BinarySearchTree.java, Feb 26, 2014, 2:02:53 PM	
 *  CernerBST, 
 *
 */


class Node {
	Node leftChild;
	Node rightChild;
	int key;
	String value;

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
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}

public class BinarySearchTree {

	/**
	 * @param args
	 */
	Node root;

	public void add(int key, String value) {
		Node temp = new Node();
		temp.setKey(key);
		temp.setValue(value);
		if (root == null) {
			root = temp;
		} else {
			Node focusNode = root;
			Node parent;
			while (true) {
				parent = focusNode;
				if (key < focusNode.getKey()) {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = temp;
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

	public void inOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			inOrderTraversal(focusNode.leftChild);
			System.out.println(focusNode.getKey() + " : "
					+ focusNode.getValue());
			inOrderTraversal(focusNode.rightChild);
		}
	}

	public void preOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			System.out.println(focusNode.getKey() + " : "
					+ focusNode.getValue());
			preOrderTraversal(focusNode.leftChild);
			preOrderTraversal(focusNode.rightChild);
		}
	}

	public void postOrderTraversal(Node focusNode) {
		if (focusNode != null) {
			postOrderTraversal(focusNode.leftChild);
			postOrderTraversal(focusNode.rightChild);
			System.out.println(focusNode.getKey() + " : "
					+ focusNode.getValue());
		}
	}

	public Node findNode(int key) {
		Node tempNode = root;
		while (tempNode.getKey() != key) {
			if (tempNode.getKey() > key) {
				tempNode = tempNode.leftChild;

			} else {
				tempNode = tempNode.rightChild;
			}
			if (tempNode == null) {
				return null;
			}
		}
		return tempNode;
	}

	public boolean remove(int key) {

		// Start at the top of the tree

		Node focusNode = root;
		Node parent = root;

		// When searching for a Node this will
		// tell us whether to search to the
		// right or left

		boolean isItALeftChild = true;

		// While we haven't found the Node
		// keep looking

		while (focusNode.key != key) {

			parent = focusNode;

			// If we should search to the left

			if (key < focusNode.key) {

				isItALeftChild = true;

				// Shift the focus Node to the left child

				focusNode = focusNode.leftChild;

			} else {

				// Greater than focus node so go to the right

				isItALeftChild = false;

				// Shift the focus Node to the right child

				focusNode = focusNode.rightChild;

			}

			// The node wasn't found

			if (focusNode == null)
				return false;

		}

		// If Node doesn't have children delete it

		if (focusNode.leftChild == null && focusNode.rightChild == null) {

			// If root delete it

			if (focusNode == root)
				root = null;

			// If it was marked as a left child
			// of the parent delete it in its parent

			else if (isItALeftChild)
				parent.leftChild = null;

			// Vice versa for the right child

			else
				parent.rightChild = null;

		}

		// If no right child

		else if (focusNode.rightChild == null) {

			if (focusNode == root)
				root = focusNode.leftChild;

			// If focus Node was on the left of parent
			// move the focus Nodes left child up to the
			// parent node

			else if (isItALeftChild)
				parent.leftChild = focusNode.leftChild;

			// Vice versa for the right child

			else
				parent.rightChild = focusNode.leftChild;

		}

		// If no left child

		else if (focusNode.leftChild == null) {

			if (focusNode == root)
				root = focusNode.rightChild;

			// If focus Node was on the left of parent
			// move the focus Nodes right child up to the
			// parent node

			else if (isItALeftChild)
				parent.leftChild = focusNode.rightChild;

			// Vice versa for the left child

			else
				parent.rightChild = focusNode.rightChild;

		}

		// Two children so I need to find the deleted nodes
		// replacement

		else {

			Node replacement = getReplacementNode(focusNode);

			// If the focusNode is root replace root
			// with the replacement

			if (focusNode == root)
				root = replacement;

			// If the deleted node was a left child
			// make the replacement the left child

			else if (isItALeftChild)
				parent.leftChild = replacement;

			// Vice versa if it was a right child

			else
				parent.rightChild = replacement;

			replacement.leftChild = focusNode.leftChild;

		}

		return true;

	}

	public Node getReplacementNode(Node replacedNode) {

		Node replacementParent = replacedNode;
		Node replacement = replacedNode;

		Node focusNode = replacedNode.rightChild;

		// While there are no more left children

		while (focusNode != null) {

			replacementParent = replacement;

			replacement = focusNode;

			focusNode = focusNode.leftChild;

		}

		// If the replacement isn't the right child
		// move the replacement into the parents
		// leftChild slot and move the replaced nodes
		// right child into the replacements rightChild

		if (replacement != replacedNode.rightChild) {

			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;

		}

		return replacement;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree bstObj = new BinarySearchTree();
		// char to ascii conversion
		final String cerner= "CERNER cerner";
		HashMap<String, Integer> cernerMap = new HashMap<String, Integer>();
		System.out.println("cerner lenght: " + cerner.length());
		for(int i  = 0 ; i < cerner.length() ; i++){
			System.out.println(cerner.charAt(i) + " --> " + (int)cerner.charAt(i));
		}
		bstObj.add(67, "C");
		bstObj.add(69, "E");
		bstObj.add(82, "R");
		bstObj.add(78, "N");
		bstObj.add(69, "E");
		bstObj.add(82, "R");
		System.out.println("In order traversal");
		bstObj.inOrderTraversal(bstObj.root);
		System.out.println("------------------------------------");
		System.out.println("Pre order traversal");
		bstObj.preOrderTraversal(bstObj.root);
		System.out.println("------------------------------------");
		System.out.println("Post order traversal");
		bstObj.postOrderTraversal(bstObj.root);
		System.out.println("------------------------------------");
		

	}

}
