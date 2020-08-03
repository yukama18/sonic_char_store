package persistence;

import model.Character;
import model.characterlist.UserCharList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_FILE = "./data/testList.txt";
    Writer testWriter;
    UserCharList testList;

    @BeforeEach
    public void runBefore() throws IOException {
        testWriter = new Writer(TEST_FILE);
        testList = new UserCharList("test list");
        testList.addChar(new Character("sonic"));
    }

    @Test
    public void testSave() throws IOException {
        testWriter.write(testList);
        testWriter.close();

        try {
            Reader reader = new Reader(TEST_FILE);
            UserCharList readList = reader.readList();

            assertTrue(readList.getListName().equals("test list"));
            assertEquals(1,readList.numOfChar());
            assertFalse(readList.isEmpty());
            assertFalse(readList.containsChar("eggman"));
            assertFalse(readList.containsChar("knuckles"));
            assertFalse(readList.containsChar("tails"));
            assertFalse(readList.containsChar("amy"));
            assertTrue(readList.containsChar("sonic"));
            assertFalse(readList.containsChar("mario"));
        } catch (IOException e) {
            fail();
        }
    }
}
