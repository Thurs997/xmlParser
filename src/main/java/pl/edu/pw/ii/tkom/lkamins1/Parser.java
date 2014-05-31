package pl.edu.pw.ii.tkom.lkamins1;


import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;
import pl.edu.pw.ii.tkom.lkamins1.tokens.ClosingTagToken;
import pl.edu.pw.ii.tkom.lkamins1.tokens.OpeningTagToken;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Token;
import pl.edu.pw.ii.tkom.lkamins1.tokens.ValueToken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by lucas on 11.05.14.
 */
class Parser {
    private Lexer lexer;
    private Stack<Token> tokenStack;
    private Stack<Property> sectionPropertyStack;
    private String fileName;

    public void useFile(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public void parse() throws IOException, IllegalStateException, WrongValueTypeException, EndOfFileException {
        lexer = new Lexer();
        tokenStack = new Stack<Token>();
        sectionPropertyStack = new Stack<Property>();
        lexer.openFile(fileName);
        buildPropertyTree();
        lexer.closeFile();
    }

    private void buildPropertyTree() throws IOException, WrongValueTypeException, EndOfFileException, IllegalStateException {
//        Token token;
//        do {
//            token = lexer.getNextToken();
//            if(token instanceof OpeningTagToken) {
//                OpeningTagToken tmpTok = (OpeningTagToken)token;
//                if(tmpTok.getType() == OpeningTagToken.TokenType.SECTION) {
//                    sectionPropertyStack.push(new Property());
//                }
//                tokenStack.push(tmpTok);
//            } else if(token instanceof ClosingTagToken) {
//                //compare to stack top
//                ClosingTagToken tmpTok = (ClosingTagToken)token;
//                if(tmpTok.getTag().equals(sectionPropertyStack)){}
//
//            } else if(token instanceof ValueToken) {
//
//            }
//
//        } while (!tokenStack.isEmpty());

    }

    private Property getNextSection(String openingTag) throws WrongValueTypeException, IOException, EndOfFileException {
        Property retVal = new Property(openingTag);

        Token token;
        token = lexer.getNextToken();
        if(!(token instanceof OpeningTagToken)) {
            throw new WrongValueTypeException();
        }
        OpeningTagToken oTag = (OpeningTagToken)token;
        if(oTag.getType() == OpeningTagToken.TokenType.SECTION) {
            retVal.addProperty(getNextSection(oTag.getTag()));
        } else {
            Property childProp = new Property(oTag.getTag());
            ValueToken value = getNextValue();
            OpeningTagToken.TokenType tokenType = oTag.getType();
            switch(tokenType) {
                case DOUBLE: childProp.setValue(ValueParser.parseToDouble(value.getRawValue()));
                             break;
                case INT:    childProp.setValue(ValueParser.parseToInt(value.getRawValue()));
                             break;
                case STRING: childProp.setValue(value.getRawValue());
                             break;
                default: throw new WrongValueTypeException();
            }
            retVal.addProperty(childProp);
        }
        return retVal;
    }

    private ValueToken getNextValue() throws WrongValueTypeException, IOException, EndOfFileException {
        Token token;
        token = lexer.getNextToken();
        if(!(token instanceof ValueToken)) {
            throw new WrongValueTypeException();
        }
        ValueToken vToken = (ValueToken) token;
        return vToken;
    }

    private ClosingTagToken getExpectedClosingTag(String expectedTag) throws WrongValueTypeException, IOException, EndOfFileException {
        Token token;
        token = lexer.getNextToken();
        if(!(token instanceof ClosingTagToken)) {
            throw new WrongValueTypeException();
        }
        if(((ClosingTagToken) token).getTag().equals(expectedTag)) {

        }
    }
}