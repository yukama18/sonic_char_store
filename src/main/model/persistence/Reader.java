package model.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import model.characterlist.CharList;
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

    public Reader(String fileName) throws FileNotFoundException {
        gson = new Gson();
        fr = new FileReader(fileName);
        jr = new JsonReader(fr);
    }

    public CharList readList() {
        savedList = new TypeToken<UserCharList>() {}.getType();
        CharList yourChar = gson.fromJson(jr, savedList);
        return yourChar;
    }

    public void close() throws IOException {
        jr.close();
    }

}
