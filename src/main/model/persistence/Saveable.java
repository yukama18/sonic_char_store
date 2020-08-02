package model.persistence;

import java.io.PrintWriter;

// represents data that can be saved to a file
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
