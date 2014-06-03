package pl.edu.pw.ii.tkom.lkamins1.tokens;

/**
 * Created by lucas on 03.06.14.
 */
public class PathToken {
    public enum PathTokenType {
        PATH,
        CONSTRAINT
    }
    public PathTokenType type;
    public String pathOrAttribute;
    public String constraintVal;
}
