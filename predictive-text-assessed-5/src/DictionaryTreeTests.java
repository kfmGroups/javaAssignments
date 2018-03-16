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

	//numleaves() test
	@Test
	public void numOfLeaveShouldBeFour() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("work");
		unit.insert("wort");
		unit.insert("wore");
		Assertions.assertEquals(4, unit.numLeaves());
	}
	
	
	//remove(word) test
	@Test
	public void testingRemove() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("bottle");
		unit.insert("botton");
		Assertions.assertEquals(true, unit.contains("bottle"));
		unit.remove("bottle");
		Assertions.assertEquals(false, unit.contains("bottle"));
		

	}
	
	public void testingRemoveShouldBeFalse() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(false, unit.remove("bst"));
		}

	//contains(word) test
	@Test
	public void containsShouldBeTrue() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		Assertions.assertEquals(true, unit.contains("word"));

	}
	
	@Test
	public void containsShouldBeFalse() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(false, unit.contains("word"));

	}
	
	//longestWord() test
	@Test
	public void longestWordShouldBeLonger() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("longer");
		Assertions.assertEquals("longer", unit.longestWord());

	}
	
	@Test
	public void removePreviouslongestWordShouldBeWord() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("longer");
		
		unit.remove("longer");
		Assertions.assertEquals("word", unit.longestWord());

	}

	//allWords() test
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
	public void allWordsInTreeShouldBeReturnedAfterRemoval() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		unit.insert("work");
		unit.insert("wort");
		unit.insert("wore");
		
		unit.remove("work");
		List<String> listOfAllWords = Arrays.asList("word", "wort", "wore");
		Assertions.assertEquals(listOfAllWords, unit.allWords());
		

	}

	//predict(word, numberOfPredictions) with popularity test
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
	
	//predict(word) test
	@Test
	public void predictWithoutPopularityTest() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word");
		Optional<String> listOfAllWords = Optional.of("word");
		Assertions.assertEquals(listOfAllWords, unit.predict("w"));

	}

	//maximumBranching() test
	@Test
	public void maximumBranchShouldBeThree() {
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
	
	//insert(word) test
	@Test
	public void shouldInsertWordIntoDictionaryTree() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("savage");
		Assertions.assertEquals(true, unit.contains("savage"));
	}
	
	//insert(word, popularity)  test
	@Test
	public void shouldInsertsWordInOrderOfPopularityIntoDictionaryTree() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("word", 19);
		unit.insert("work", 3);
		unit.insert("wavage", 1);
		List<String> expectedList = Arrays.asList("wavage", "work", "word");
		Assertions.assertEquals(expectedList, unit.predict("w", 3));
	}

	//size() test
	@Test
	public void sizeShouldBesix() {
		DictionaryTree unit = new DictionaryTree();
		unit.insert("savage");
		Assertions.assertEquals(7, unit.size());
	}
	
	@Test
	public void sizeShouldBeZero() {
		DictionaryTree unit = new DictionaryTree();
		Assertions.assertEquals(1, unit.size());
	}

	
	
}
