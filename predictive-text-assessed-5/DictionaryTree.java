import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;

public class DictionaryTree {

	private Map<Character, DictionaryTree> children = new LinkedHashMap<>();

	/**
	 * Inserts the given word into this dictionary. If the word already exists,
	 * nothing will change.
	 *
	 * @param word
	 *            the word to insert
	 */
	void insert(String word) {
		DictionaryTree currentChild = this;
		for (char letter : word.toCharArray()) {
			currentChild = currentChild.children.computeIfAbsent(letter, (cha) -> new DictionaryTree());
		}
	}

	/**
	 * Inserts the given word into this dictionary with the given popularity. If
	 * the word already exists, the popularity will be overriden by the given
	 * value.
	 *
	 * @param word
	 *            the word to insert
	 * @param popularity
	 *            the popularity of the inserted word
	 */
	void insert(String word, int popularity) {
		// throw new RuntimeException("DictionaryTree.insert not implemented
		// yet");
		insert(word);
	}

	/**
	 * Removes the specified word from this dictionary. Returns true if the
	 * caller can delete this node without losing part of the dictionary, i.e.
	 * if this node has no children after deleting the specified word.
	 *
	 * @param word
	 *            the word to delete from this dictionary
	 * @return whether or not the parent can delete this node from its children
	 */
	boolean remove(String word) {
		return false;

	}

	/**
	 * Determines whether or not the specified word is in this dictionary.
	 *
	 * @param word
	 *            the word whose presence will be checked
	 * @return true if the specified word is stored in this tree; false
	 *         otherwise
	 */
	boolean contains(String word) {
		DictionaryTree currentChild = this;
		for (char letter : word.toCharArray()) {
			currentChild = currentChild.children.get(letter);
			if (currentChild == null)
				return false;
		}
		return true;
	}

	/**
	 * @param prefix
	 *            the prefix of the word returned
	 * @return a word that starts with the given prefix, or an empty optional if
	 *         no such word is found.
	 */
	Optional<String> predict(String prefix) {
		return predict(prefix, 1).stream().findFirst();
	}

	/**
	 * Predicts the (at most) n most popular full English words based on the
	 * specified prefix. If no word with the specified prefix is found, an empty
	 * Optional is returned.
	 *
	 * @param prefix
	 *            the prefix of the words found
	 * @return the (at most) n most popular words with the specified prefix
	 */
	List<String> predict(String prefix, int n) {
		DictionaryTree currentChild = this;
		for (char letter : prefix.toCharArray()) {
			currentChild = currentChild.children.get(letter);
			if (currentChild == null)
				break;
		}
		ArrayList<String> results = new ArrayList<>();
		if (currentChild != null) {
			currentChild.accumulate(results, prefix, n);
		}
		return results;

	}

	private void accumulate(List<String> accumulator, String prefix, int n) {
		if (children.isEmpty()) {
			accumulator.add(prefix);
		} else {
			for (Entry<Character, DictionaryTree> childNodes : children.entrySet()) {
				if (accumulator.size() >= n) {
					break;
				}
				childNodes.getValue().accumulate(accumulator, prefix + childNodes.getKey(), n);
			}
		}
	}

	/**
	 * @return the number of leaves in this tree, i.e. the number of words which
	 *         are not prefixes of any other word.
	 */
	int numLeaves() {
		int numLeaves = 0;
		int rooChildrenSize = children.size();
		if (rooChildrenSize == 0) {
			numLeaves += numLeaves + 1;
		}
		DictionaryTree currentChild = this;
		for (char letter : children.keySet()) {
			
		}

		return numLeaves;
	}

	/**
	 * @return the maximum number of children held by any node in this tree
	 */
	int maximumBranching() {
		int maximumChild = children.size();
		for (DictionaryTree childNodes : children.values()) {
			maximumChild = Math.max(maximumChild, childNodes.maximumBranching());
		}
		return maximumChild;
	}

	/**
	 * @return the height of this tree, i.e. the length of the longest branch
	 */
	int height() {
		int heightOfTheTree = -1;
		for (DictionaryTree childNodes : children.values()) {
			heightOfTheTree = Math.max(heightOfTheTree, childNodes.height());
		}
		return heightOfTheTree + 1;

	}

	/**
	 * @return the number of nodes in this tree
	 */
	int size() {
		int sizeOfTree = 1;
		for (DictionaryTree childNodes : children.values()) {
			sizeOfTree += childNodes.size();
		}
		return sizeOfTree;
		// return fold((tree, cResults) -> (int) cResults.stream().count()+1);
	}

	/**
	 * @return the longest word in this tree
	 */
	String longestWord() {
		throw new RuntimeException("DictionaryTree.longestWord not implemented yet");
	}

	/**
	 * @return all words stored in this tree as a list
	 */
	List<String> allWords() {
		throw new RuntimeException("DictionaryTree.allWords not implemented yet");
	}

	/**
	 * Folds the tree using the given function. Each of this node's children is
	 * folded with the same function, and these results are stored in a
	 * collection, cResults, say, then the final result is calculated using
	 * f.apply(this, cResults).
	 *
	 * @param f
	 *            the summarising function, which is passed the result of
	 *            invoking the given function
	 * @param <A>
	 *            the type of the folded value
	 * @return the result of folding the tree using f
	 */
	<A> A fold(BiFunction<DictionaryTree, Collection<A>, A> f) {
		ArrayList<A> cResults = new ArrayList<A>();
		for (DictionaryTree child : children.values()) {
			cResults.add(f.apply(child, cResults));
		}
		return f.apply(this, cResults);
	}

}