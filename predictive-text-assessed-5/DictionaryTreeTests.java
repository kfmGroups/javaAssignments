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
    public void numOfLeaveShouldBe() {
        DictionaryTree unit = new DictionaryTree();
        unit.insert("word");
        unit.insert("work");
        unit.insert("wort");
        unit.insert("wore");
        Assertions.assertEquals(4, unit.numLeaves());
    }
    
    @Test
    public void removeShouldBe() {
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

}
