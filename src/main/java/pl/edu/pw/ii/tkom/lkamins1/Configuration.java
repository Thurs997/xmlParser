package pl.edu.pw.ii.tkom.lkamins1;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;

import java.io.IOException;

/**
 * Created by lucas on 11.05.14.
 */
public class Configuration {

    private Parser parser;
    private Property root;

    public Configuration(String filename) throws IOException, IllegalStateException, WrongValueTypeException, EndOfFileException {
        fromFile(filename);
    }


    private void fromFile(String fileName) throws IOException, IllegalStateException, WrongValueTypeException, EndOfFileException {
        parser = new Parser();
        parser.useFile(fileName);
        parser.parse();
        root = parser.getRoot();
    }

    public void saveToFile(String fileName) {

    }
    public Property getProperty(String path) {
       return root.getFromPath(path);
    }
    public void insert(String propertyName) {

    }
    public void insert(String propertyName, Integer value) {

    }
    public void insert(String propertyName, Integer[] value) {

    }
    public void insert(String propertyName, String value) {

    }
    public void insert(String propertyName, String[] value) {

    }
    public void insert(String propertyName, Double value) {

    }
    public void insert(String propertyName, Double[] value) {

    }
    public void removeProperty(String property) {

    }
    public void clear() {

    }
}
