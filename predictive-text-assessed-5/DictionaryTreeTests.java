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
        System.out.println("num of leaves "+unit.numLeaves());
        Assertions.assertEquals(2, unit.numLeaves());
    }

}
