

/*
 * Interface for immutable binary search trees.
 */

public interface Bst <E extends Comparable<E>> {
  public boolean isEmpty();
  public boolean smaller(E e); // Checks whether all nodes smaller than e.
  public boolean bigger(E e);  // Checks whether all nodes bigger than e.
  public boolean has(E e);     // Checks whether e occurs in "this".
  public Bst<E>  find(E e);    // Returns subtree of "this" with e at root (or null).
  public Bst<E>  insert(E e);  // Returns a copy of "this" with e inserted.  
  public Bst<E>  delete(E e);  // Returns a copy of "this" with e deleted.
  public E       smallest();   // Returns smallest node (=left-most node).
  public Bst<E>  deleteSmallest();// Return new tree with smallest element deleted.
  public E       largest();    // Returns largest node (=right-most node).
  public Bst<E>  deleteLargest();// Return new tree with largest element deleted.
  public String  fancyToString();// 2-dimensional, rotated tree printing.
  public String  fancyToString(int d);// Starting at a given position d.
  public int size(); //returns the size of the binary search tree.
  public int height();//returns the height of the binary search tree.
  public Bst<E> balance(); // returns a balance tree
  public int saveInOrder(E[] a, int nextAvailableIndex);
  Bst<E> balanced(int start, int end, E[] elements);
}
