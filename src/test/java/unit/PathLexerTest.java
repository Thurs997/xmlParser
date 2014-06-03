package unit;

import org.junit.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.parsers.PathLexer;
import pl.edu.pw.ii.tkom.lkamins1.tokens.PathToken;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lucas on 03.06.14.
 */
public class PathLexerTest {

    @Test
    public void testSimpleToken() throws IOException, WrongValueTypeException {
        PathLexer lexer = new PathLexer();
        lexer.setPath("/abc/");
        lexer.tokenize();
        PathToken token = lexer.popToken();
        assertTrue("Token type check", token.type == PathToken.PathTokenType.PATH);
        assertTrue("Token path check", token.pathOrAttribute.equals("abc"));
        assertTrue("Token constraint check", token.constraintVal == null);
        assertFalse("Lexer stack check", lexer.stackNotEmpty());
    }

    @Test
    public void testSimpleTokenWithConstraints() throws IOException, WrongValueTypeException {
        PathLexer lexer = new PathLexer();
        lexer.setPath("/abc/cons1='1'/cons2='2'");
        lexer.tokenize();
        PathToken token = lexer.popToken();
        assertTrue("Token type check", token.type == PathToken.PathTokenType.CONSTRAINT);
        assertTrue("Token path check", token.pathOrAttribute.equals("cons2"));
        assertTrue("Token constraint check", token.constraintVal.equals("2"));
        assertTrue("Lexer stack check", lexer.stackNotEmpty());
        token = lexer.popToken();
        assertTrue("Token type check", token.type == PathToken.PathTokenType.CONSTRAINT);
        assertTrue("Token path check", token.pathOrAttribute.equals("cons1"));
        assertTrue("Token constraint check", token.constraintVal.equals("1"));
        assertTrue("Lexer stack check", lexer.stackNotEmpty());
        token = lexer.popToken();
        assertTrue("Token type check", token.type == PathToken.PathTokenType.PATH);
        assertTrue("Token path check", token.pathOrAttribute.equals("abc"));
        assertTrue("Token constraint check", token.constraintVal==null);
        assertFalse("Lexer stack check", lexer.stackNotEmpty());
    }
}
