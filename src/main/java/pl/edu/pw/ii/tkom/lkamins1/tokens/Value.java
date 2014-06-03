package pl.edu.pw.ii.tkom.lkamins1.tokens;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.parsers.ValueParser;

/**
 * Created by lucas on 16.05.14.
 */
 public class Value {
    private String rawValue;
    private Integer intValue;
    private Double doubleValue;

    public String getRaw() { return rawValue; }
    public void setRaw(String rawValue) { this.rawValue = rawValue; intValue = null; doubleValue = null; }

    public Value(String rawValue) {
        setRaw(rawValue);
    }

    public Value(int intValue) {
        setRaw(String.valueOf(intValue));
    }

    public Value(double doubleValue) {
        setRaw(String.valueOf(doubleValue));
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
