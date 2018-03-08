import java.util.ArrayList;
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
        System.out.println(unit.size()+ " "+ unit.height());
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
        Assertions.assertEquals(true, unit.remove("wore"));
        Assertions.assertEquals(3, unit.numLeaves());
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
        unit.insert("word",1);
        unit.insert("work",2);
        unit.insert("wort",3);
        unit.insert("wore",4);
        List<String> listOfAllWords = new ArrayList<String>();
        listOfAllWords.add("word");
        listOfAllWords.add("work");
        listOfAllWords.add("wort");
        listOfAllWords.add("wore");
        Assertions.assertEquals(listOfAllWords, unit.allWords());
        
    }

}
