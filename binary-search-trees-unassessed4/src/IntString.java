
/*
 * Type of pairs consisting of an int and a String, compared by the
 * lexicographic order.
 */

class IntString implements Comparable<IntString> {

  private int i;
  private String s;

  IntString(int i, String s) {
    this.i = i;
    this.s = s;
  }

  public int getInt() {
    return i;
  }

  public String getString() {
    return s;
  }

  public String toString() {
    return "(" + i + "," + s + ")";
  }

  // Compare in the lexicographic order:
  public int compareTo(IntString x) {
    int c = i - x.getInt();

    if (c != 0)
      return c;
    else
      return s.compareTo(x.getString());
  }
}
