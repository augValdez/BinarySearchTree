/**
 * Binary search tree implementation.
 * 
 * We do not allow duplicates.
 * 
 * @author Greg Gagne
 */
import java.util.Iterator;

import bridges.base.BSTElement;
import bridges.base.BinTreeElement;

public class BinarySearchTree <K extends Comparable<? super K>> implements SearchTreeInterface<K> 
{
	// the root of the binary search tree
	private BSTElement<K, String> root;

	/**
	 * Create an empty binary search tree
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * This method has nothing to do with a binary search tree,
	 * but is necessary to provide the bridges visualization.
	 */
	public BSTElement<K, String> getRoot() {
		return root;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Solution that uses recursive helper method.
	 * We disallow duplicate elements in the tree. 
	 */
	public K add(K key) {
		if (contains(key)) {
			return null;
		} else {
			root = add(key, root);

			return key;
		}
	}


	/**
	 * private helper method for adding a node to the binary search tree
	 * @param key
	 * @param subtree
	 * @return the root of the tree
	 */
	private BSTElement<K, String> add(K key, BSTElement<K,String> subtree) {
		if (subtree == null) {
			// we have found the spot for the addition

			// create the new node
			// parameters are (1) label (2) key (3) empty string [not used]
			BSTElement<K, String> newNode = new BSTElement<K, String>(key.toString(), key, "");

			// we also set the color of a new node to red
			newNode.getVisualizer().setColor("red");

			return newNode;
		}
		int direction = key.compareTo(subtree.getKey());
		if (direction < 0) {
			subtree.setLeft( add(key, subtree.getLeft()) );
		} else if (direction > 0) {
			subtree.setRight( add(key, subtree.getRight()) );
		}

		return subtree;
	}

	public K getLargest() {
		if (root == null) {
			return null;
		} else {
			return getLargestHelper(root);
		}
	}
	
	private K getLargestHelper(BSTElement<K,String> node) {
		if (node.getRight() == null) {
			node.getVisualizer().setColor("pink");
			return node.getKey();
		} else {
			return getLargestHelper(node.getRight());
		}
	}

	public K getSmallest() {
		if (root == null) {
			return null;
		} else {
			return getSmallHelper(root);
		}
	}
	
	private K getSmallHelper(BSTElement<K,String> node) {
		if (node.getLeft() == null) {
			node.getVisualizer().setColor("yellow");
			return node.getKey();
		} else {
			return getSmallHelper(node.getLeft());
		}
	}
	
	public boolean contains(K key) {
		 BSTElement<K,String> node = root;
		if(containsHelper(key, node) == false) {
			return false;
		} else if(containsHelper(key, node) == true){
			return true;
		}
		return false;
	}

	private boolean containsHelper(K key , BSTElement<K,String> node) {
		if(node == null) {
			return false;
		}
		int compare = key.compareTo(node.getKey());
		if(compare == 0) {
			node.getVisualizer().setColor("pink");
			return true;
		}
		if(node.getLeft() == null && node.getRight() == null) {
			return false;
		} else if (compare < 0) {
			node = node.getLeft();
			return containsHelper(key, node);
		} else if (compare > 0) {
			node = node.getRight();
			return containsHelper(key, node);
		}
		return false;
		
	}
	
//FIX REMOVE
	public K remove(K key) {
		BSTElement<K,String> node = root;
		node = root;
		if(node == null) {
			return null;
		} else {
			int keyData = (Integer) key;
			int data = (Integer) node.getKey();
			if(keyData < data) {
				removeNode(key);
				
			}
		}
		
	return null;
	}
	
	public K removeNode(K key) {
		BSTElement<K,String> node = root;
		if(node == null) {
			return null;
		} else if (node.getRight() == null) {
			return remove(node.getKey());
	
		} else {
			return removeHelper(key);
		}
	}
	
	private K removeHelper(K key) {
		
		BSTElement<K,String> node = root.getRight();
		
		if((node.getLeft())!= null){
			BSTElement<K,String> nodeRemoved = (BSTElement<K, String>) key;
			node = node.getLeft();
			remove(nodeRemoved.getKey());
		} 
		if(node.getRight() == null){
			return remove(node.getKey());
		}
		return null;
	}
	

	public int size() {
		return getSizeHelper(root);
		}

	private int getSizeHelper(BSTElement<K,String> key) {
		int size;
		if (key == null) {
			return 0;
		} else {
			size = getSizeHelper(key.getLeft()) + getSizeHelper(key.getRight());
			return size+1;
		}
	}

	public Iterator<K> iterator() {
		return new InOrderIterator();
	}
		
	private class InOrderIterator implements Iterator<K>{
		private K[] elements;
		private int next;
		
		private InOrderIterator() {
			// create an array large enough to hold all elements in the tree
			elements = (K[])new Object[size()];
			next = 0;
			inOrder(root);
		}

		private void inOrder(BSTElement<K, String> node) {
			if (root != null) {
				inOrder(root.getLeft());
				elements[next++] = node.getKey();
				inOrder(root.getRight());
			}
		}
		
		@Override
		public boolean hasNext() {
			return (next < size());
		}

		@Override
		public K next() {
			return elements[next++];
		}
		
	}

	

	
	
}
