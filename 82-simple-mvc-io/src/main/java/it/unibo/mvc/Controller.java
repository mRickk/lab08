package it.unibo.mvc;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller implements Serializable {

    private File currentFile;
    private final String DEFAULT_FILENAME = "output.txt";
    private final String DEFAULT_PATH = System.getProperty("user.home")
            + System.getProperty("file.separator") + DEFAULT_FILENAME;
    

    public Controller() {
        currentFile = new File(DEFAULT_PATH);
    }

    public void setFile(final File newFile) throws IllegalArgumentException {
        final File parent = newFile.getParentFile();
        if (parent.exists()) {
            currentFile = newFile;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    public File getFile() {
        return currentFile;
    }

    public String getFilePath() {
        return currentFile.getPath();
    }

    public void writeOnFile(final String input) throws IOException {
        try (PrintStream out = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            out.println(input);
        }
    }
}
