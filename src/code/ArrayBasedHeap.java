package code;

import java.util.ArrayList;
import java.util.Comparator;

import given.Entry;
import given.iAdaptablePriorityQueue;

/*
 * Implement an array based heap
 * Note that you can just use Entry here!
 * 
 */

public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value>{
  
  // Use this arraylist to store the nodes of the heap. 
  // This is required for the autograder. 
  // It makes your implementation more verbose (e.g. nodes[i] vs nodes.get(i)) but then you do not have to deal with dynamic resizing
  protected ArrayList<Entry<Key,Value>> nodes;
  
  /*
   * 
   * YOUR CODE BELOW THIS
   * 
   */
  
  protected Comparator<Key> comparator;
  protected Comparator<Value> comparator2;
  
  
  public int parentOf(int i) {
	  return (i - 1) / 2;
  }
  
  public int leftChildOf(int i) {
	  return 2*i + 1;
  }
  
  public int rightChildOf(int i) {
	  return 2 * i + 2;
  }
  
  public boolean hasLeftChild(int i) {
	  return leftChildOf(i) < nodes.size();
  }
  
  public boolean hasRightChild(int i) {
	  return rightChildOf(i) < nodes.size();
  }
   
  public ArrayBasedHeap() {
	  nodes = new ArrayList<Entry<Key, Value>>();
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

  @Override
  public int size() {
    return nodes.size();
  }

  @Override
  public boolean isEmpty() {
    return nodes.size() == 0;
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
  public void insert(Key k, Value v) {
	  nodes.add(new Entry<Key, Value>(k, v));
	  int location = nodes.size() - 1;
	  while(location > 0 && comparator.compare(nodes.get(location).getKey(), nodes.get(parentOf(location)).getKey()) < 0) {
		  swap(location, parentOf(location));
		  location = parentOf(location);
	  }
  }
  
 
  
  private void swap(int a, int b) {
	  Entry<Key, Value> e = nodes.get(a);
	  nodes.set(a, nodes.get(b));
	  nodes.set(b, e);
  }

  
  

  @Override
  public Entry<Key, Value> pop() {
	  if(isEmpty())
		  return null;
	  Entry<Key, Value> element = nodes.get(0);
	  swap(0, nodes.size() - 1);
	  nodes.remove(nodes.size() - 1);
	  downheap(0);
	  return element;
  }
  
  private void downheap(int position) {
	  while(hasLeftChild(position)) {
		  int leftIndex = leftChildOf(position);
		  int smallChildIndex = leftIndex;
		  if(hasRightChild(position)) {
			  int rightIndex = rightChildOf(position);
			  if(comparator.compare(nodes.get(leftIndex).getKey(), nodes.get(rightIndex).getKey()) > 0) {
				  smallChildIndex = rightIndex;
			  }
		  }
		  if(comparator.compare(nodes.get(smallChildIndex).getKey(), nodes.get(position).getKey()) >= 0) {
			  break;
		  }
		  swap(position, smallChildIndex);
		  position = smallChildIndex;
	  }
  }

  @Override
  public Entry<Key, Value> top() {
	  return isEmpty() ? null : nodes.get(0);
  }

  @Override
  public Value remove(Key k) {
	  int position = findPositionOf(k);
	  if(position == -1)
		  return null;
	  Value v = nodes.get(position).getValue();
	  nodes.set(position, nodes.get(nodes.size() - 1));
	  nodes.remove(nodes.size() - 1);
	  rebuildHeap();
	  return v;
	  
  }
  
  private int findPositionOf(Key key) {
	  for(int i = 0; i < nodes.size(); i++) {
		  if(nodes.get(i).getKey() == key)
			  return i;
	  }
	  return -1;
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
	  for(Entry<Key, Value> e : nodes) {
		  if(comparator.compare(e.getKey(), entry.getKey()) == 0) {
			  if(comparator2.compare(e.getValue(), entry.getValue()) == 0) {
				  Key key = entry.getKey();
				  Value v = entry.getValue();
				  remove(e.getKey());
				  insert(k, v);
				  rebuildHeap();
				  return key;
			  }
		  }
	  }
	  return null;
  }
  
  private void rebuildHeap() {
	  for(int i = size() / 2; i >= 0; i--) {
		  heapify(i);
	  } 
  }
  
  private void heapify(int location) {
	  if(location > size()) {
		  return;
	  } 
	  
	  int left = leftChildOf(location);
	  int right = rightChildOf(location);
	  
	  int temp = location;
	  
	  if(left < size() && comparator.compare(nodes.get(left).getKey(), nodes.get(location).getKey()) <= 0) {
		  temp = left;
	  }
	  if(right < size() && comparator.compare(nodes.get(right).getKey(), nodes.get(location).getKey()) <= 0 && comparator.compare(nodes.get(right).getKey(), nodes.get(left).getKey()) <= 0)
		  temp = right;
	  
	  if(temp != location) {
		  swap(temp, location);
		  
		  heapify(temp);
	  }
  }
  
  
  @SuppressWarnings("unchecked")
public int compareV(Value v1, Value v2) {
	  return ((Comparable<Value>) v1).compareTo(v2);
  }

  @Override
  public Key replaceKey(Value v, Key k) {
	  for(Entry<Key, Value> e : nodes) {
 		  if(comparator2.compare(e.getValue(), v) == 0) {
 			  Key key = e.getKey();
 			  remove(key);
 			  insert(k, v);
 			  rebuildHeap();
 			  return key;
 		  }
	  }
	  return null;
  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
	  for(Entry<Key, Value> e : nodes) {
		  if(comparator.compare(e.getKey(), entry.getKey()) == 0) {
			  if(comparator2.compare(entry.getValue(), e.getValue()) == 0) {
				  Value value = entry.getValue();
				  e.setValue(v);
				  return value;
			  }
		  }
	  }
	  return null;
  }
  
}
