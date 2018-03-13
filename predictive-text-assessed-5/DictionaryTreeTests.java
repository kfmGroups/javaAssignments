import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Kelsey McKenna
 */
public class DictionaryTreeTests {

	@Test
	public void heightOfRootShouldBeZero() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(0, unit.height());
	}

	@Test
	public void heightOfWordShouldBeWordLength() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word", 0);
		System.out.println(unit.size() + " " + unit.height());
		Assertions.assertEquals("word".length(), unit.height());
	}

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
		unit.insert("word");
		unit.insert("work");
		unit.insert("wort");
		unit.insert("wore");
		unit.insert("wores");
		Assertions.assertEquals(true, unit.remove("wore"));
		Assertions.assertEquals(4, unit.numLeaves());
	}

	@Test
	public void containsShouldBeTrue() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		Assertions.assertEquals(true, unit.contains("word"));

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
	public void maximumBranchShouldBeLonger() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("sage");
		unit.insert("longest");
		Assertions.assertEquals(3, unit.maximumBranching());
	}

	
	@Test
	public void shouldInsertWordIntoDictionaryTree() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("savage");
		Assertions.assertEquals(true, unit.contains("savage"));
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
}
