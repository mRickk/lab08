package it.unibo.mvc;

import java.util.List;

/**
 * Models a simple Controller responsible
 * of I/O access
 */
public interface Controller {

    void print() throws IllegalStateException;

    void setString(String s) throws IllegalStateException;

    String nextString();

    List<String> showHistory() throws IllegalStateException;

}
