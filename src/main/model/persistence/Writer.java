package model.persistence;

// a writer that can write character list data to a file

import com.google.gson.Gson;
import model.characterlist.CharList;
import model.characterlist.UserCharList;
import ui.CharacterStore;

import java.io.*;

public class Writer {
    private Gson gson;
    private FileWriter writer;

    // EFFECTS: constructs a writer
    public Writer(String fileName) throws IOException {
        gson = new Gson();
        writer = new FileWriter(fileName);
    }

    // MODIFIES: this
    // EFFECTS: writes your character list to file
    public void write(CharList yourChar) throws IOException {
        String json = gson.toJson(yourChar);
        writer.write(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() throws IOException {
        writer.close();
    }
}
