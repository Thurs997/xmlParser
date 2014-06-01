package pl.edu.pw.ii.tkom.lkamins1.tokens;

/**
 * Created by lucas on 11.05.14.
 */
public class Token {
    public boolean isExpectedClosingToken(String expectedTag) {
        return (this instanceof ClosingTagToken && ((ClosingTagToken)this).getTag().equals(expectedTag));
    }
}
