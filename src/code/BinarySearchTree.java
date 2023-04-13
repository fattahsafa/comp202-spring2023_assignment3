package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import given.iMap;
import given.iBinarySearchTree;

/*
 * Implement a vanilla binary search tree using a linked tree representation
 * Use the BinaryTreeNode as your node class
 */

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value>, Comparator<Key> {

	/*
	 * 
	 * YOUR CODE BELOW THIS
	 * 
	 */
	BinaryTreeNode<Key, Value> root;
	int size;
	Comparator<Key> comparator;
	Comparator<Value> comparator2;

	public BinarySearchTree() {
		root = new BinaryTreeNode<Key, Value>(null, null);
		size = 0;
		comparator = new Comparator<Key>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Key k1, Key k2) {
				return ((Comparable<Key>) k1).compareTo(k2);
			}
		};
		
		comparator2 = new Comparator<Value>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Value v1, Value v2) {
				return ((Comparable<Value>) v1).compareTo(v2);
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Key k1, Key k2) {
		return ((Comparable<Key>) k1).compareTo(k2);
	}

	@Override
	public Value get(Key k) {
		BinaryTreeNode<Key, Value> location = root;
		while(isInternal(location)) {
			int compare = comparator.compare(location.getKey(), k);
			if(compare > 0) {
				location = location.getLeftChild();
			}
			else if(compare < 0) {
				location = location.getRightChild();
			}
			else {
				return location.getValue();
			}
		}
		return location.getValue();
	}

	@Override
	public Value put(Key k, Value v) {
		Value value = get(k);
		root = insertHelper(root, k, v);
		return value;
	}
	
	private BinaryTreeNode<Key, Value> insertHelper(BinaryTreeNode<Key, Value> root, Key k, Value v) {
		if(isExternal(root)) {
			BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<Key, Value>(null, null);
			expandExternal(newNode, k, v);
			return newNode;
		}
		
		int compareResult = comparator.compare(k, root.getKey());
		if(compareResult < 0) {
			root.setLeftChild(insertHelper(root.getLeftChild(), k, v));
		}
		else if(compareResult > 0) {
			root.setRightChild(insertHelper(root.getRightChild(), k, v));
		}
		else {
			root.setValue(v);
		}
		return root;
	}

	private void expandExternal(BinaryTreeNode<Key, Value> node, Key k, Value v) {
		node.setKey(k);
		node.setValue(v);
		node.setLeftChild(new BinaryTreeNode<Key, Value>(null, null));
		node.getLeftChild().setParent(node);
		node.setRightChild(new BinaryTreeNode<Key, Value>(null, null));
		node.getRightChild().setParent(node);
		size++;
	}

	public BinaryTreeNode<Key, Value> treeSearch(BinaryTreeNode<Key, Value> node, Key k) {
		if(isExternal(node))
			return node;
		int comp = comparator.compare(k, node.getKey());
		if(comp == 0) {
			return node;
		}
		else if(comp < 0) {
			return treeSearch(node.getLeftChild(), k);
		}
		else {
			return treeSearch(node.getRightChild(), k);
		}
	}

	@Override
	public Value remove(Key k) {
		Value v = get(k);
		root = removeHelper(root, k);
		if(v != null)
			size--;
		return v;
	}

	private BinaryTreeNode<Key, Value> removeHelper(BinaryTreeNode<Key, Value> root, Key k) {
		if(isExternal(root)) {
			return root;
		}
		
		int compareResult = comparator.compare(k, root.getKey());
		if(compareResult < 0)
			root.setLeftChild(removeHelper(root.getLeftChild(), k));
		else if(compareResult > 0)
			root.setRightChild(removeHelper(root.getRightChild(), k));
		else if(isInternal(root.getLeftChild()) && isInternal(root.getRightChild())) {
			BinaryTreeNode<Key, Value> successor = getSuccessor(root);
			root.setKey(successor.getKey());
			root.setValue(successor.getValue());
			root.setRightChild(removeHelper(root.getRightChild(), root.getKey()));
		}
		else {
			root = (isInternal(root.getLeftChild())) ? root.getLeftChild() : root.getRightChild();
		}
		return root;
	}

	public BinaryTreeNode<Key, Value> getSuccessor(BinaryTreeNode<Key, Value> node) {
		BinaryTreeNode<Key, Value> position = node.getRightChild();
		while (isInternal(position)) {
			position = position.getLeftChild();
		}
		return position.getParent();
	}

	@Override
	public Iterable<Key> keySet() {
		ArrayList<Key> keySet = new ArrayList<Key>();
		for (BinaryTreeNode<Key, Value> node : getNodesInOrder()) {
			keySet.add(node.getKey());
		}
		return keySet;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public BinaryTreeNode<Key, Value> getRoot() {
		return root;
	}

	@Override
	public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
		return node.getParent();
	}

	@Override
	public boolean isInternal(BinaryTreeNode<Key, Value> node) {
		if(node == root && size == 0)
			return false;
		else if(node.getLeftChild() != null || node.getRightChild() != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isExternal(BinaryTreeNode<Key, Value> node) {
		if(node.getKey() == null)
			return true;
		return false;
	}

	@Override
	public boolean isRoot(BinaryTreeNode<Key, Value> node) {
		return node == root;
	}

	@Override
	public BinaryTreeNode<Key, Value> getNode(Key k) {
		return treeSearch(root, k);
	}

	@Override
	public Value getValue(Key k) {
		BinaryTreeNode<Key, Value> node = getNode(k);
		if (node != null)
			return node.getValue();
		return null;
	}

	@Override
	public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
		return node.leftChild;
	}

	@Override
	public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
		return node.rightChild;
	}

	@Override
	public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
		if (node == root)
			return null;
		if (isLeftChild(node))
			return node.getParent().getRightChild();
		else
			return node.getParent().getLeftChild();
	}

	@Override
	public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
		if (node == root)
			return false;
		if (node.getParent().getLeftChild() == node)
			return true;
		return false;
	}

	@Override
	public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
		if (node == root)
			return false;
		if (node.getParent().getRightChild() == node)
			return true;
		return false;
	}

	@Override
	public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
		ArrayList<BinaryTreeNode<Key, Value>> inOrderList = new ArrayList<BinaryTreeNode<Key, Value>>();
		inOrder(root, inOrderList);
		return inOrderList;
	}

	private void inOrder(BinaryTreeNode<Key, Value> node, ArrayList<BinaryTreeNode<Key, Value>> currentList) {
		if (isExternal(node))
			return;

		if (!isExternal(node.getLeftChild()))
			inOrder(node.getLeftChild(), currentList);
		currentList.add(node);
		if (!isExternal(node.getRightChild()))
			inOrder(node.getRightChild(), currentList);
	}

	@Override
	public void setComparator(Comparator<Key> C) {
		comparator = C;

	}

	@Override
	public Comparator<Key> getComparator() {
		return comparator;

	}

	@Override
	public BinaryTreeNode<Key, Value> ceiling(Key k) {
		if (size == 0)
			return null;
		return ceilingHelper(root, k);
	}

	private BinaryTreeNode<Key, Value> ceilingHelper(BinaryTreeNode<Key, Value> node, Key k) {
		if (comparator.compare(getLargestKey(), k) < 0)
			return null;
		if (isExternal(node))
			return node;
		if (comparator.compare(node.getKey(), k) == 0)
			return node;
		if (comparator.compare(node.getKey(), k) < 0) {
			return ceilingHelper(node.getRightChild(), k);
		}

		BinaryTreeNode<Key, Value> location = ceilingHelper(node.getLeftChild(), k);
		if (!isExternal(location) && comparator.compare(location.getKey(), k) >= 0)
			return location;
		return node;
	}

	private Key getLargestKey() {
		BinaryTreeNode<Key, Value> location = root;
		if (isExternal(root))
			return null;
		while (!isExternal(location.getRightChild()))
			location = location.getRightChild();
		return location.getKey();
	}

	@Override
	public BinaryTreeNode<Key, Value> floor(Key k) {
		if (size == 0) {
			return null;
		}
		return floorHelper(root, k);
	}

	private BinaryTreeNode<Key, Value> floorHelper(BinaryTreeNode<Key, Value> node, Key k) {
		if (comparator.compare(getSmallestKey(), k) > 0)
			return null;
		if (isExternal(node)) {
			return node;
		}
		if (comparator.compare(node.getKey(), k) == 0) {
			return node;
		}

		if (comparator.compare(node.getKey(), k) > 0) {
			return floorHelper(node.getLeftChild(), k);
		}

		BinaryTreeNode<Key, Value> location = floorHelper(node.getRightChild(), k);
		if (!isExternal(location) && comparator.compare(location.getKey(), k) <= 0) {
			return location;
		}
		return node;
	}

	private Key getSmallestKey() {
		if (isExternal(root))
			return null;

		BinaryTreeNode<Key, Value> location = root;
		while (!isExternal(location.getLeftChild()))
			location = location.getLeftChild();

		return location.getKey();
	}
}
