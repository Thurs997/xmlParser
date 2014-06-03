package unit;

import org.junit.Test;
import pl.edu.pw.ii.tkom.lkamins1.parsers.Lexer;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.EndOfFileException;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucas on 03.06.14.
 */
public class LexerTest {

    @Test
    public void testAnyValueToken() throws WrongValueTypeException, IOException, EndOfFileException {
        Lexer lexer = new Lexer();
        lexer.setReader(new BufferedReader(new StringReader("123<")));
        Token token =  lexer.getNextToken();
        assertTrue("Type check", token.type == Token.TokenType.VALUE);
        assertTrue("Raw value check", token.getValue().getRaw().equals("123"));
    }

    @Test
    public void testOpeningSectionToken() throws WrongValueTypeException, IOException, EndOfFileException {
        Lexer lexer = new Lexer();
        lexer.setReader(new BufferedReader(new StringReader("<opening type=\"section\">")));
        Token token =  lexer.getNextToken();
        assertTrue("Type check", token.type == Token.TokenType.OPENING);
        assertTrue("Tag check", token.getTag().equals("opening"));
        assertTrue("Interior type check", token.valueType == Token.ValueType.SECTION);
    }
    @Test
    public void testOpeningIntToken() throws WrongValueTypeException, IOException, EndOfFileException {
        Lexer lexer = new Lexer();
        lexer.setReader(new BufferedReader(new StringReader("<opening type=\"int\">")));
        Token token =  lexer.getNextToken();
        assertTrue("Type check", token.type == Token.TokenType.OPENING);
        assertTrue("Tag check", token.getTag().equals("opening"));
        assertTrue("Interior type check", token.valueType == Token.ValueType.INT);
    }
    @Test
    public void testOpeningStringToken() throws WrongValueTypeException, IOException, EndOfFileException {
        Lexer lexer = new Lexer();
        lexer.setReader(new BufferedReader(new StringReader("<opening type=\"string\">")));
        Token token =  lexer.getNextToken();
        assertTrue("Type check", token.type == Token.TokenType.OPENING);
        assertTrue("Tag check", token.getTag().equals("opening"));
        assertTrue("Interior type check", token.valueType == Token.ValueType.STRING);
    }
    @Test
    public void testOpeningDoubleToken() throws WrongValueTypeException, IOException, EndOfFileException {
        Lexer lexer = new Lexer();
        lexer.setReader(new BufferedReader(new StringReader("<opening type=\"double\">")));
        Token token =  lexer.getNextToken();
        assertTrue("Type check", token.type == Token.TokenType.OPENING);
        assertTrue("Tag check", token.getTag().equals("opening"));
        assertTrue("Interior type check", token.valueType == Token.ValueType.DOUBLE);
    }

    @Test
    public void testClosingToken() {

    }


}
