
public class Sample {
    
  public static void main(String[] args) {
    Bst<Integer> t1 = new Empty<Integer>();
    System.out.println(t1);

    Bst<Integer> t2 = t1.insert(30);  // t1 is unchanged.
    System.out.println(t2);

    Bst<Integer> t3 = t2.insert(40);  // t1 and t2 are unchanged.
    System.out.println(t3);

    Bst<Integer> t4 = t3.insert(25);
    System.out.println(t4);

    Bst<Integer> t5 = t4.insert(10);
    System.out.println(t5);

    Bst<Integer> t6 = t5.insert(100).insert(20).insert(17).insert(40).insert(13).insert(14).insert(23);

    System.out.println(t6.fancyToString());

    System.out.println(t6.fancyToString());

    Bst<Integer> t10 = t6.delete(20); 
    System.out.println(t10.fancyToString());

    Bst<Integer> t10alternative = t6.delete(20); 
    System.out.println(t10alternative.fancyToString());


    Bst<Integer> t11 = t10.delete(13); 
    System.out.println(t11.fancyToString());

    Bst<Integer> t12 = t11.delete(25); 
    System.out.println(t12.fancyToString());

  }
}
