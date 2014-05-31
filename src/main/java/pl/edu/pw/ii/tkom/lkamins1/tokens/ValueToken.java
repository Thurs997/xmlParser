package pl.edu.pw.ii.tkom.lkamins1.tokens;

import edu.tkom.lkamins1.*;
import edu.tkom.lkamins1.exceptions.WrongValueTypeException;

/**
 * Created by lucas on 16.05.14.
 */
 public class ValueToken extends Token {
    private String rawValue;
    private Integer intValue;
    private Double doubleValue;

    public String getRawValue() { return rawValue; }
    public void setRawValue(String rawValue) { this.rawValue = rawValue; intValue = null; doubleValue = null; }

    public ValueToken(String rawValue) {
        setRawValue(rawValue);
    }

    public ValueToken(int intValue) {
        setRawValue(String.valueOf(intValue));
    }

    public ValueToken(double doubleValue) {
        setRawValue(String.valueOf(doubleValue));
    }



    public int asInt() throws WrongValueTypeException {
        try {
            if(intValue == null) {
                intValue = ValueParser.parseToInt(rawValue);
            }
        } catch (IllegalStateException e) {
            throw new WrongValueTypeException();
        }
        return intValue;
    }
    public double asDouble() throws WrongValueTypeException {
        try {
            if(doubleValue == null) {
                doubleValue = ValueParser.parseToDouble(rawValue);
            }
        } catch (IllegalStateException e) {
            throw new WrongValueTypeException();
        }
        return doubleValue;
    }
}
