package pl.edu.pw.ii.tkom.lkamins1.tokens;

import edu.tkom.lkamins1.exceptions.WrongValueTypeException;

/**
 * Created by lucas on 16.05.14.
 */
public class OpeningTagToken extends Token {
    public enum TokenType {
        INT,
        DOUBLE,
        STRING,
        SECTION;
    }

    private TokenType type;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public void setType(String typeString) throws WrongValueTypeException {
        if(typeString.equals("int")) {
            type = TokenType.INT;
        } else if(typeString.equals("double")) {
            type = TokenType.DOUBLE;
        } else if(typeString.equals("string")) {
            type = TokenType.STRING;
        } else if(typeString.equals("section")) {
            type = TokenType.SECTION;
        } else {
            throw new WrongValueTypeException();
        }
    }





}
