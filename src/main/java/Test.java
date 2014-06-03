import pl.edu.pw.ii.tkom.lkamins1.Configuration;
import pl.edu.pw.ii.tkom.lkamins1.Property;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Created by lucas on 01.06.14.
 */
public class Test {
    public static void main(String[] args) throws IllegalStateException, IOException, PropertyNotFoundException, WrongValueTypeException, EndOfFileException {

        LogManager.getLogManager().reset();
        Configuration config = new Configuration("/home/lucas/test.txt");
        System.out.println(config.getRoot().toHTML());
        Property prop = config.getProperty("/stores/store='Księgarnia1'/store='Księgarnia5'/");
        System.out.println(prop.toString());
    }

}
