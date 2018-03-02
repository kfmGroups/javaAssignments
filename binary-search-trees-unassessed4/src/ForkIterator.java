package bst;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForkIterator<E> implements Iterator<E> {

	Iterator<E> left;
	Iterator<E> right;
	boolean doneSelf = false;
	private E root;

	public ForkIterator(E root, Iterator<E> left, Iterator<E> right) {
		this.root = root;
		this.left = left;
		this.right = right;

	}

	@Override
	public boolean hasNext() {
		if (left.hasNext()) {
			return true;
		} else if (!doneSelf) {
			return true;
		} else if (right.hasNext()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public E next() {
		if (left.hasNext()) {
			return left.next();
		} else if (!doneSelf) {
			doneSelf = true;
			return root;
		} else if (right.hasNext()) {
			return right.next();
		} else {
			throw new NoSuchElementException();

		}

	}

}
