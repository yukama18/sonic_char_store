package persistence;

import model.characterlist.UserCharList;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    private static final String VALID_FILE = "./data/testListFile.txt";
    private static final String DNE_FILE = "./path/does/not/exist/testAccount.txt";

    @Test
    public void testFileNotFound() {
        try {
            Reader reader = new Reader(DNE_FILE);
            fail();
        } catch (FileNotFoundException e) {
            //expected
        }
    }

    @Test
    public void testReadExistingFile() {
        try {
            Reader reader = new Reader(VALID_FILE);
            UserCharList readList = reader.readList();
            reader.close();
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