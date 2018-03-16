import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String errorMessage = "please enter a valid word";
		String notInsertedMessage = "word was not inserted into the tree";
		Optional<String> inputWord = Optional.ofNullable(word);
		  // Pre-condition for this method to operate correctly:
		assert !inputWord.get().isEmpty(): errorMessage;
		insert(inputWord.get(), 0);
		// Post-condition :
		assert contains(inputWord.get()) : notInsertedMessage;
		//
		
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
		String errorMessage = "please enter a valid word";
		String notInsertedMessage = "word was not inserted into the tree";
		Optional<String> inputWord = Optional.ofNullable(word);
		  // Pre-condition for this method to operate correctly:
		assert !inputWord.get().isEmpty() && popularity >= 0: errorMessage;
		DictionaryTree currentChild = this;
		for (char letter : word.toCharArray()) {
			currentChild = currentChild.children.computeIfAbsent(letter, (cha) -> new DictionaryTree());
		}
		currentChild.popularity = Optional.of(popularity);// from the root all
															// the down it is a
															// valid wpord
		 // post-condition :
		assert contains(inputWord.get()) : notInsertedMessage;

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
		
		String errorMessage = "please enter a word in the dictionary";
		String notRemovedMessage = "word not removed";
		boolean result = removeHelper(word);
		prune();
		 // Post-condition:
		assert !contains(word) : notRemovedMessage;
		return result;
		
	}

	public boolean removeHelper(String word) {
		if (word.isEmpty()) {
			popularity = Optional.empty();
			return true;
		}

		if (contains(word)) {
			return children.get(word.charAt(0)).removeHelper(word.substring(1));
		}
		return false;

	}

	void prune() {
		List<Character> charsToRemove = new ArrayList<>();
		for (Map.Entry<Character, DictionaryTree> child : children.entrySet()) {
			List<PopularWord> popular = new ArrayList<>();
			child.getValue().accumulate(popular, "");
			if (popular.isEmpty()) {
				charsToRemove.add(child.getKey());
			} else {
				child.getValue().prune();
			}
		}
		
		for(char c: charsToRemove){
			children.remove(c);
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
		String errorMessage = "please enter a valid word";
		Optional<String> inputWord = Optional.ofNullable(word);
		  // Pre-condition for this method to operate correctly:
		assert !inputWord.get().isEmpty() : errorMessage;
		Optional<DictionaryTree> currentChild = Optional.of(this);
		for (char letter : word.toCharArray()) {
			currentChild = Optional.ofNullable(currentChild.get().children.get(letter));
			if (!currentChild.isPresent())
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
		String errorMessage = "please enter a valid prefix";
		// Pre-condition for this method to operate correctly:
		assert prefix != null: errorMessage;
		Optional<DictionaryTree> currentChild = Optional.of(this);
		for (char letter : prefix.toCharArray()) {
			currentChild = currentChild.flatMap((child) -> child.getChild(letter));
		}
		ArrayList<PopularWord> results = new ArrayList<>();
		currentChild.ifPresent((child) -> child.accumulate(results, prefix));
		return results.stream()
				.sorted((popularWord1, popularWord2) -> Integer.compare(popularWord1.popularity,popularWord2.popularity))
				.limit(n)
				.map((popularWord) -> popularWord.word)
				.collect(Collectors.toList());
	}
	
	private Optional<DictionaryTree> getChild(char letter){
		return Optional.ofNullable(children.get(letter));
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
		popularity.ifPresent((pop) -> accumulator.add(new PopularWord(prefix, pop)));
		if (!children.isEmpty()) {
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

		return fold((tree, cResults) ->{
			if(tree.children.isEmpty())
				return 1;
			int numLeaves = 0;
			for (int childLeaf : cResults)
				numLeaves += childLeaf;
			return numLeaves;
		});
	}

	/**
	 * @return the maximum number of children held by any node in this tree
	 */
	int maximumBranching() {
		return fold((tree, cResults) -> {
			int maximumChild = children.size();
			for(int childMaximumBranch: cResults)
				maximumChild = Math.max(maximumChild, childMaximumBranch);
			return maximumChild;
		});
	}

	/**
	 * @return the height of this tree, i.e. the length of the longest branch
	 */
	int height() {
		return fold((tree, cResults) -> {
			int heightOfTree = -1;
			for(int childHeight: cResults)
				heightOfTree = Math.max(heightOfTree, childHeight);
			return heightOfTree+1;	
		});

	}

	/**
	 * @return the number of nodes in this tree
	 */
	int size() {
		return fold((tree, cResults) -> 1 + cResults.stream().reduce(0,(a,b) -> (a+b)));
	}
	
	

	/**
	 * @return the longest word in this tree
	 */
	String longestWord() {
		return allWords().stream()
				.sorted(((word1, word2) -> Integer.compare(word2.length(), word1.length())))
				.findFirst().orElse("");
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
			cResults.add(child.fold(f));
		}
		return f.apply(this, cResults);
	}

}
