package pl.edu.pw.ii.tkom.lkamins1;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;
import pl.edu.pw.ii.tkom.lkamins1.parsers.Parser;
import pl.edu.pw.ii.tkom.lkamins1.parsers.PathParser;

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
        System.out.println(root.toString());

    }

    public void saveToFile(String fileName) {

    }
    public Property getProperty(String path) throws PropertyNotFoundException, PathParsingException {
       PathParser pathParser = new PathParser();
       pathParser.setPath(path);
       try {
           pathParser.parse();
       } catch(IllegalStateException e) {
           throw new PathParsingException();
       }
       Property prop = root;
       while(pathParser.stackNotEmpty()){
           prop = prop.getNextInPath(pathParser.getNextAtom());
       }
       return prop;
    }
    public Property getRoot() {return root;};

    public void clear() {
        root.clear();
    }
}
