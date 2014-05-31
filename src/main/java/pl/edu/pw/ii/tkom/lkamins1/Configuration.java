package pl.edu.pw.ii.tkom.lkamins1;

import java.io.FileNotFoundException;

/**
 * Created by lucas on 11.05.14.
 */
public class Configuration {

    private Parser parser;
    private Property root;

    public Configuration() {
        parser = new Parser();
        root = new Property();
    }


    public void fromFile(String fileName) throws FileNotFoundException {
            parser.useFile(fileName);
    }
    public void saveToFile(String fileName) {

    }
    public Property getProperty(String propertyName) {
        return new Property();
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
