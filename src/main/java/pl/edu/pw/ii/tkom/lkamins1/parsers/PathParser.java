package pl.edu.pw.ii.tkom.lkamins1.parsers;

import com.sun.org.apache.xpath.internal.CachedXPathAPI;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Atom;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Constraint;
import pl.edu.pw.ii.tkom.lkamins1.tokens.PathToken;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by lucas on 01.06.14.
 */
public class PathParser {

    private Stack<Atom> atomStack;
    private String path;
    private PathLexer lexer;

    public void setPath(String path) {this.path = path; atomStack = new Stack<Atom>(); lexer = new PathLexer();}
    public Atom getNextAtom() {return atomStack.pop();}

    public void parse() throws IllegalStateException {
        lexer.setPath(path);
        lexer.tokenize();
        if(!lexer.stackNotEmpty()) throw new IllegalStateException();

        List<Constraint> constraints = new LinkedList<Constraint>();
        while(lexer.stackNotEmpty()) {
            PathToken token = lexer.popToken();
            if(token.type == PathToken.PathTokenType.CONSTRAINT) {
                constraints.add(new Constraint(token.pathOrAttribute, token.constraintVal));
            } else {
                Atom atom = new Atom();
                atom.setPath(token.pathOrAttribute);
                if(!constraints.isEmpty()) atom.addConstraints(constraints);
                constraints = new LinkedList<Constraint>();
                atomStack.push(atom);
            }
        }
    }

    public boolean stackNotEmpty() {
        return !(atomStack.isEmpty());
    }
}
