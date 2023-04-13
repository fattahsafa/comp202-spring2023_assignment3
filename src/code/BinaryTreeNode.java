package code;

import given.Entry;

/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {
  
  /*
   * 
   * YOUR CODE HERE
   * 
   */
	BinaryTreeNode<Key, Value> parent, leftChild, rightChild;
  public BinaryTreeNode(Key k, Value v) {
    super(k, v);
    
    /*
     * 
     * This constructor is needed for the autograder. You can fill the rest to your liking.
     * YOUR CODE AFTER THIS:
     * 
     */
    this.k = k;
    this.v = v;
    parent = null;
    if(k != null && v != null) {
    	leftChild = new BinaryTreeNode<Key, Value>(null, null);
    	rightChild = new BinaryTreeNode<Key, Value>(null, null);
    }
  }
  
  public Key getKey() {
	  return k;
  }
  
  public void setKey(Key k) {
	  this.k = k;
  }
  
  public Value getValue() {
	  return v;
  }
  
  public void setValue(Value v) {
	  this.v = v;
  }
  
  public BinaryTreeNode<Key, Value> getParent() {
	  return parent;
  }
  
  public BinaryTreeNode<Key, Value> getLeftChild() {
	  return leftChild;
  }
  
  public BinaryTreeNode<Key, Value> getRightChild() {
	  return rightChild;
  }
  
  public void setParent(BinaryTreeNode<Key, Value> p) {
	  parent = p;
  }
  
  public void setLeftChild(BinaryTreeNode<Key, Value> child) {
	  leftChild = child;
	  if(child != null)
		  leftChild.setParent(this);
  }

  public void setRightChild(BinaryTreeNode<Key, Value> child) {
	  rightChild = child;
	  if(child != null)
		  rightChild.setParent(this);
  }
}
