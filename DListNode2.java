/* DListNode2.java */

/**
 *  A DListNode2 is a node in a DList2 (doubly-linked list).
 */

public class DListNode2 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int runlength;
  public DListNode2 prev;
  public DListNode2 next;
  public int red;
  public int green;
  public int blue;

  /**
   *  DListNode2() constructor.
   *  The first constructor without any parameters constructs a DListNode with runlength 0 (head);
   */

  DListNode2() {
    runlength = 0;
    red = 0;
    green = 0;
    blue = 0;
    prev = null;
    next = null;
  }

  DListNode2(int rlength, int r, int g, int b) {
    runlength = rlength;
    red = r;
    green = g;
    blue = b;
    prev = null;
    next = null;
  }

}
