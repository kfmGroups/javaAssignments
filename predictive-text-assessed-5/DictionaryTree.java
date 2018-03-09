import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DictionaryTree {

	private Map<Character, DictionaryTree> children = new LinkedHashMap<>();
	private Optional<Integer> popularity = Optional.empty();

	/**
	 * Inserts the given word into this dictionary. If the word already exists,
	 * nothing will change.
	 *
	 * @param word
	 *            the word to insert
	 */
	void insert(String word) {
		insert(word, 0);
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
		DictionaryTree currentChild = this;
		int index = 0;
		int lastIndex = word.length() - 1;
		for (char letter : word.toCharArray()) {
			currentChild = currentChild.children.computeIfAbsent(letter, (cha) -> new DictionaryTree());
			index++;
		}
		currentChild.popularity = Optional.of(popularity);//from the root all the down it is a valid wpord

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
		boolean result = removeHelper(word);
		prune();
		return result;
	}
	private boolean removeHelper(String word){
		if(word.isEmpty()){
			popularity = Optional.empty();
			return true;
		}
		
		if (contains(word)) {
			return children.get(word.charAt(0)).remove(word.substring(1));
		}
		return false;
		
	}
	void prune(){
		for(Entry<Character, DictionaryTree> child: children.entrySet()){
			List<PopularWord> popular = new ArrayList<>();
			child.getValue().accumulate(popular, "");
			if(popular.isEmpty()){
				children.remove(child.getKey());
			}else{
				child.getValue().prune();
			}
		}
		
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
		ArrayList<PopularWord> results = new ArrayList<>();
		if (currentChild != null) {
			currentChild.accumulate(results, prefix);
		}
		return results.stream()
				.sorted((popularWord1, popularWord2) -> Integer.compare(popularWord1.popularity, popularWord2.popularity))
				.limit(n)
				.map((popularWord) -> popularWord.word)
				.collect(Collectors.toList());
	}

	private class PopularWord {
		public PopularWord(String word, int popularity) {

			this.word = word;
			this.popularity = popularity;
		}

		public String word;
		public int popularity;
	}

	private void accumulate(List<PopularWord> accumulator, String prefix) {
		if (popularity.isPresent()) {
			accumulator.add(new PopularWord(prefix, popularity.get()));
		}
		if(!children.isEmpty() ){
			for (Entry<Character, DictionaryTree> childNodes : children.entrySet()) {
				childNodes.getValue().accumulate(accumulator, prefix + childNodes.getKey());
			}
		}
	}

	/**
	 * @return the number of leaves in this tree, i.e. the number of words which
	 *         are not prefixes of any other word.
	 */
	int numLeaves() {

		if (children.isEmpty()) {
			return 1;
		}
		int numLeaves = 0;
		for (char letter : children.keySet()) {
			DictionaryTree childnode = children.get(letter);
			if (childnode != null) {
				numLeaves += childnode.numLeaves();
			}
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
		String longestWord = "";
		for (Entry<Character, DictionaryTree> childNodes : children.entrySet()) {
			String word = childNodes.getValue().longestWord();
			if (word.length() >= longestWord.length()) {

				longestWord = childNodes.getKey() + word;

			}
		}
		return longestWord;
	}

	/**
	 * @return all words stored in this tree as a list
	 */
	List<String> allWords() {
		List<PopularWord> allWords = new ArrayList<PopularWord>();
		accumulate(allWords, "");
		return allWords.stream().map((popularWord) -> popularWord.word).collect(Collectors.toList());
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
