import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Kelsey McKenna
 */
public class DictionaryTreeTests {
	//all height test
	@Test
	public void heightOfRootShouldBeZero() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(0, unit.height());
	}

	@Test
	public void heightOfWordShouldBeWordLength() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word", 0);
		Assertions.assertEquals("word".length(), unit.height());
	}

	//num leaves test
	@Test
	public void numOfLeaveShouldBeFour() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("work");
		unit.insert("wort");
		unit.insert("wore");
		Assertions.assertEquals(4, unit.numLeaves());
	}
	
	

	@Test
	public void removeShouldBeTrue() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("bottle");
		unit.insert("botton");
		Assertions.assertEquals(true, unit.contains("bottle"));
		unit.remove("bottle");
		System.out.println(unit.allWords());
		Assertions.assertEquals(false, unit.contains("bottle"));

	}

	@Test
	public void containsShouldBeTrue() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		Assertions.assertEquals(true, unit.contains("word"));

	}
	
	@Test(expected = AssertionError.class)
	public void containsShouldReTurnAssertionError() {
		DictionaryTree unit = new DictionaryTree();
		unit.remove("bst");
	}

	@Test
	public void longestWordShouldBeLonger() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("longer");
		Assertions.assertEquals("longer", unit.longestWord());

	}

	@Test
	public void allWordsInTreeShouldBeReturned() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("work");
		unit.insert("wort");
		unit.insert("wore");
		List<String> listOfAllWords = Arrays.asList("word", "work", "wort", "wore");
		Assertions.assertEquals(listOfAllWords, unit.allWords());

	}

	@Test
	public void predictionByPopularityTest() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word", 19);
		unit.insert("work", 3);
		unit.insert("wizard", 1);
		unit.insert("wore", 17);
		List<String> listOfAllWords = Arrays.asList("wizard", "work", "wore", "word");
		Assertions.assertEquals(listOfAllWords, unit.predict("w", 4));

	}
	
	@Test
	public void predictWithoutPopularityTest() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		Optional<String> listOfAllWords = Optional.of("word");
		Assertions.assertEquals(listOfAllWords, unit.predict("w"));

	}

	@Test
	public void maximumBranchShouldBeLonger() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("sage");
		unit.insert("longest");
		Assertions.assertEquals(3, unit.maximumBranching());
	}

	@Test
	public void maximumBranchShouldZero() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(0, unit.maximumBranching());
	}
	
	@Test
	public void shouldInsertWordIntoDictionaryTree() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("savage");
		Assertions.assertEquals(true, unit.contains("savage"));
	}

	@Test
	public void sizeShouldBesix() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("savage");
		Assertions.assertEquals(7, unit.size());
	}

	@Test
	public void shouldInsertsWordInOrderOfPopularityIntoDictionaryTree() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word", 19);
		unit.insert("work", 3);
		unit.insert("savage", 1);
		List<String> listOfAllWords = Arrays.asList("savage", "work", "word");
		Assertions.assertEquals(true, unit.contains("work"));
		Assertions.assertEquals(true, unit.contains("word"));
		Assertions.assertEquals(true, unit.contains("savage"));
	}
	
	@Test(expected = AssertionError.class)
	public void removeShouldReTurnAssertionError() {
		DictionaryTree unit = new DictionaryTree();
		unit.remove("bst");
	}
	
	@Test(expected = AssertionError.class)
	public void shouldReturnInvalidInput() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("2");
	}
}
