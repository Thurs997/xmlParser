package pl.edu.pw.ii.tkom.lkamins1;


import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;
import pl.edu.pw.ii.tkom.lkamins1.tokens.OpeningTagToken;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Token;
import pl.edu.pw.ii.tkom.lkamins1.tokens.ValueToken;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lucas on 11.05.14.
 */
class Parser {
    private Lexer lexer;
    private String fileName;
    private Property root;

    public Property getRoot() {
        return root;
    }

    public void useFile(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public void parse() throws IOException, IllegalStateException, WrongValueTypeException, EndOfFileException {
        lexer = new Lexer();
        lexer.openFile(fileName);
        buildPropertyTree();
        lexer.closeFile();
    }

    private void buildPropertyTree() throws IOException, WrongValueTypeException, EndOfFileException, IllegalStateException {
        Token token = lexer.getNextToken();
        if(token instanceof OpeningTagToken) {
            OpeningTagToken oTag = (OpeningTagToken) token;
            root = getNextProperty(oTag);
        } else {
            throw new WrongValueTypeException();
        }
    }

    private Property getNextProperty(OpeningTagToken oTag) throws WrongValueTypeException, IOException, EndOfFileException {
        if(oTag.getType() == OpeningTagToken.TokenType.SECTION)
            return getNextSectionProperty(oTag);
         else
            return getNextValueProperty(oTag);
    }

    private Property getNextSectionProperty(OpeningTagToken openingTag) throws WrongValueTypeException, IOException, EndOfFileException {
        Property retVal = new Property(openingTag.getTag());
        Token token;
        while ((token = lexer.getNextToken()).isExpectedClosingToken(openingTag.getTag())) {
            if(token instanceof OpeningTagToken) {
                OpeningTagToken oTag = (OpeningTagToken)token;
                retVal.addProperty(getNextProperty(oTag));
                if(!lexer.getNextToken().isExpectedClosingToken(oTag.getTag())) {
                    throw new WrongValueTypeException();
                }
            } else {
                throw new WrongValueTypeException();
            }
        }
    }

    private Property getNextValueProperty(OpeningTagToken oTag) throws WrongValueTypeException, IOException, EndOfFileException {
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
        return childProp;
    }

    private ValueToken getNextValue() throws WrongValueTypeException, IOException, EndOfFileException {
        Token token;
        token = lexer.getNextToken();
        if(!(token instanceof ValueToken)) {
            throw new WrongValueTypeException();
        }
        return (ValueToken) token;
    }
}