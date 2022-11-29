package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> listOfStrings;
    private int counter;
    
    public SimpleController() {
        listOfStrings = new ArrayList<>();
        counter = 0;
    }

    @Override
    public void print() throws IllegalStateException {
        String s = nextString();
        if (s == null) {
            throw new IllegalStateException();
        }
        System.out.println(s);
        counter++;
    }

    @Override
    public void setString(String s) throws IllegalStateException {
        if (s == null) {
            throw new NullPointerException();
        }
        listOfStrings.add(s);
    }

    @Override
    public String nextString() {
        return listOfStrings.get(counter);
    }

    @Override
    public List<String> showHistory() throws IllegalStateException {
        if (listOfStrings.isEmpty()) {
            throw new IllegalStateException();
        }
        List<String> temp = new ArrayList<>();
        temp.addAll(listOfStrings);
        return temp;
    }
}
