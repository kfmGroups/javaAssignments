package bst;

import java.util.Arrays;

public class Sample2 {

	public static void main(String[] args) {
		Bst<Integer> t1 = new Empty<Integer>();
		System.out.println(t1);

		Bst<Integer> t2 = t1.insert(1).insert(2).insert(3).insert(4).insert(5).insert(6);
		System.out.println(t2.fancyToString());

		System.out.println("balanced tree: " + (t2.balanced().fancyToString()));
		for (int i : t2) {
			System.out.println(i);
		}

	}
}