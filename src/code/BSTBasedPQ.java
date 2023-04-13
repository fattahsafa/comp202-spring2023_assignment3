package code;

import java.util.ArrayList;

import given.Entry;
import given.Util;
import given.iAdaptablePriorityQueue;

/*
 * Implement a binary search tree based priority queue
 * Do not try to create heap behavior (e.g. no need for a last node)
 * Just use default binary search tree properties
 */

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */
	
	
  
  @Override
  public void insert(Key k, Value v) {
    put(k, v);
    
  }

  @Override
  public Entry<Key, Value> pop() {
	BinaryTreeNode<Key, Value> current = root;
	if(isEmpty())
		return null;
	else if(size == 1) {
		Entry<Key, Value> entry = new Entry<Key, Value>(root.getKey(), root.getValue());
		remove(root.getKey());
		return entry;
	}
	while(!isExternal(current.getLeftChild())) {
		current = current.getLeftChild();
	}
	
	Entry<Key, Value> entry = new Entry<Key, Value>(current.getKey(), current.getValue());
	
	remove(current.getKey());
	return entry;
  }

  @Override
  public Entry<Key, Value> top() {
	  BinaryTreeNode<Key, Value> current = root;
	  if(isEmpty()) {
		  return null;
	  }
	  else if(size == 1) {
		  return new Entry<Key, Value>(root.getKey(), root.getValue());
	  }
		while(!isExternal(current.getLeftChild())) {
			current = current.getLeftChild();
		}
		
		return new Entry<Key, Value>(current.getKey(), current.getValue());
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
	  BinaryTreeNode<Key, Value> location = treeSearch(root, entry.getKey());
	  if(isExternal(location) || location.getValue() != entry.getValue())
		  return null;
	  Entry<Key, Value> e = new Entry<Key, Value>(location.getKey(), location.getValue());
	  remove(e.getKey());
	  insert(k, e.getValue());
	  return e.getKey();
  }

  @Override
  public Key replaceKey(Value v, Key k) {
	  ArrayList<BinaryTreeNode<Key, Value>> inOrder = (ArrayList<BinaryTreeNode<Key, Value>>) getNodesInOrder();
	  BinaryTreeNode<Key, Value> location = null;
	  for(BinaryTreeNode<Key, Value> node : inOrder) {
		  if(comparator2.compare(node.getValue(), v) == 0) {
			  location = node;
			  break;
		  }
	  }
	  if(location == null)
		  return null;
	  Key key = location.getKey();
	  remove(location.getKey());
	  put(k, v);
	  return key;
  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
	  BinaryTreeNode<Key, Value> location = treeSearch(root, entry.getKey());
	  
	  if(isExternal(location) || location.getValue() != entry.getValue())
		  return null;
	  
	  Value value = location.getValue();
	  location.setValue(v);
	  return value;
  }


}
