package persistence;

// CLASS LEVEL COMMENT: a reader that can read a character list from file

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.characterlist.UserCharList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class Reader {
    private Gson gson;
    private FileReader fr;
    private JsonReader jr;
    private Type savedList;

    // EFFECTS: constructs a reader
    public Reader(String fileName) throws FileNotFoundException {
        gson = new Gson();
        fr = new FileReader(fileName);
        jr = new JsonReader(fr);
    }

    // MODIFIES: this
    // EFFECTS: returns a character list from given file
    public UserCharList readList() {
        savedList = new TypeToken<UserCharList>() {}.getType();
        UserCharList yourChar = gson.fromJson(jr, savedList);
        return yourChar;
    }

    // MODIFIES: this
    // EFFECTS: closes reader
    public void close() throws IOException {
        jr.close();
    }

}
