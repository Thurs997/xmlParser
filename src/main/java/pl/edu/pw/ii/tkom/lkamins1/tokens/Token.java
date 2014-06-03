package pl.edu.pw.ii.tkom.lkamins1.tokens;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;

import java.util.logging.Logger;

/**
 * Created by lucas on 11.05.14.
 */
public class Token {
    private static final Logger logger = Logger.getLogger(Token.class.getName());
    public enum TokenType {
        OPENING,
        CLOSING,
        COMMENT,
        VALUE
    }

    public enum ValueType {
        INT,
        DOUBLE,
        STRING,
        SECTION
    }

    public ValueType valueType;
    public TokenType type;

    private String tag;
    private Value value;

    public Token(TokenType type) {this.type = type;} //logger.log(Level.INFO, "New token of type: " + type.toString());}

    public void setValue(Value value) {this.value = value;}
    public Value getValue() {return value;}
    public void setTag(String tag) {this.tag = tag;}
    public String getTag() {return tag;}

    public boolean isExpectedClosingToken(String expectedTag) {
        return (type == TokenType.CLOSING && tag.equals(expectedTag));
    }

    public void setValueType(String typeString) throws WrongValueTypeException {
        if(typeString.equals("int")) {
            valueType = ValueType.INT;
        } else if(typeString.equals("double")) {
            valueType = ValueType.DOUBLE;
        } else if(typeString.equals("string")) {
            valueType = ValueType.STRING;
        } else if(typeString.equals("section")) {
            valueType = ValueType.SECTION;
        } else {
            throw new WrongValueTypeException();
        }
    }
}
