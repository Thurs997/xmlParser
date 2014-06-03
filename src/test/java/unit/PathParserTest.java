package unit;

import org.junit.Test;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Atom;
import pl.edu.pw.ii.tkom.lkamins1.parsers.PathParser;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Constraint;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lucas on 03.06.14.
 */
public class PathParserTest {

    @Test
    public void testSimplePathAtom() {
        PathParser pathParser = new PathParser();
        pathParser.setPath("/abc");
        Atom atom = pathParser.getNextAtom();
        assertTrue("Atom value", atom.getPath().equals("abc"));
        assertFalse("Atom constraints", atom.hasConstraints());
    }

    @Test
    public void testAtomWithConstraints() {
        PathParser pathParser = new PathParser();
        pathParser.setPath("/abc/cons1='1'/cons2='2'");
        Atom atom = pathParser.getNextAtom();
        assertTrue("Atom value", atom.getPath().equals("abc"));
        assertTrue("Atom constraints", atom.hasConstraints());
        List<Constraint> cons = atom.getConstraints();
        assertTrue(cons.size() == 2);

    }
    @Test
    public void testCondition() {

    }

}
