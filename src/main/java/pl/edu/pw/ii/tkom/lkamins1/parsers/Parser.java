package pl.edu.pw.ii.tkom.lkamins1.parsers;


import pl.edu.pw.ii.tkom.lkamins1.Property;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Value;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.*;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.IllegalStateException;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Token;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 11.05.14.
 */
public class Parser {
    private Lexer lexer;
    private String fileName;
    private Property root;
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    public Property getRoot() {
        return root;
    }

    public void useFile(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
    }

    public void parse() throws ParsingException {
        lexer = new Lexer();
        try {
            lexer.openFile(fileName);
        } catch (FileNotFoundException e) {
            throw new ParsingException("Couldn't find file.");
        }
        buildPropertyTree();
        try {
            lexer.closeFile();
        } catch (IOException e) {
            throw new ParsingException("There was some I/O problem, check your input file.");
        }
    }

    private void buildPropertyTree() throws ParsingException {
        Token token;
        try {
            token = lexer.getNextToken();
            if (token.type == Token.TokenType.OPENING) {
                root = getNextProperty(token);
            } else {
                throw new ParsingException("Tags don't match.");
            }
        } catch (IOException e) {
            throw new ParsingException("There was some I/O problem, check your input file.");
        } catch (EndOfFileException e) {
            throw new ParsingException("Unexpected end of file.");
        } catch (WrongValueTypeException e) {
            throw new ParsingException("Unexpected value found. Check tag types.");
        }
    }

    private Property getNextProperty(Token oTag) throws WrongValueTypeException, IOException, EndOfFileException {
        if(oTag.valueType == Token.ValueType.SECTION)
            return getNextSectionProperty(oTag);
         else
            return getNextValueProperty(oTag);
    }

    private Property getNextSectionProperty(Token openingTag) throws WrongValueTypeException, IOException, EndOfFileException {
        Property retVal = new Property(openingTag.getTag());
        Token token;
        while (!((token = lexer.getNextToken()).isExpectedClosingToken(openingTag.getTag()))) {
            if(token.type == Token.TokenType.OPENING) {
                retVal.addProperty(getNextProperty(token));
            } else {
                throw new WrongValueTypeException();
            }
        }
        logger.log(Level.INFO, "Returning section property with tag:" + openingTag.getTag());
        return retVal;
    }

    private Property getNextValueProperty(Token oTag) throws WrongValueTypeException, IOException, EndOfFileException {
        Property childProp = new Property(oTag.getTag());
        Value value = getNextValue();
        switch(oTag.valueType) {
            case DOUBLE: childProp.setValue(ValueParser.parseToDouble(value.getRaw()));
                break;
            case INT:    childProp.setValue(ValueParser.parseToInt(value.getRaw()));
                break;
            case STRING: childProp.setValue(value.getRaw());
                break;
            default: throw new WrongValueTypeException();
        }
        if(!lexer.getNextToken().isExpectedClosingToken(oTag.getTag())) {
            throw new WrongValueTypeException();
        }
       // logger.log(Level.INFO, "Returning value property with tag:" + oTag.getTag());
        return childProp;
    }

    private Value getNextValue() throws WrongValueTypeException, IOException, EndOfFileException {
        Token token;
        token = lexer.getNextToken();
        if(!(token.type == Token.TokenType.VALUE)) {
            throw new WrongValueTypeException();
        }
        return token.getValue();
    }
}