package pl.edu.pw.ii.tkom.lkamins1;



import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.tokens.ValueToken;

/**
 * Created by lucas on 11.05.14.
 */
public class Property implements Comparable<Property> {
    private ValueToken value = null;
    private SortedMultiset<Property> children = null;
    private String tag;

    public int compareTo(Property another) {
        return tag.compareTo(another.getTag());
    }

    public Property (String tag) {
        setTag(tag);
    }

    public void addProperty(Property child) {
        clearValue();
        if(children == null) {
            children = TreeMultiset.create();
        }
        children.add(child);
    }
    private void clearValue() {
        value = null;
    }

    private void clearChildren() {
        if(children != null) {
            for(SortedMultiset.Entry<Property> entry : children.entrySet()) {
                entry.getElement().clear();
            }
            children.clear();
            children = null;
        }
    }

    public Property getFromPath(String path) {
        PathParser.getPathAtom
    }

    public String[] getChildrenAsStringArray(String nodeName) { return new String[]{}; }
    public Integer[] getChildrenAsIntArray(String nodeName) { return new Integer[]{}; }
    public Double[] getChildrenAsDoubleArray(String nodeName) { return new Double[]{}; }
    public String asString() { return value.getRawValue(); }
    public Integer asInt() throws WrongValueTypeException { return value.asInt(); }
    public Double asDouble() throws WrongValueTypeException { return value.asDouble(); }
    public void setValue(int intValue) { clearChildren(); value = new ValueToken(intValue); }
    public void setValue(String stringValue) { clearChildren(); value = new ValueToken(stringValue); }
    public void setValue(double doubleValue) { clearChildren(); value = new ValueToken(doubleValue); }
    public void setTag(String tag) { this.tag = tag; }
    public String getTag() { return tag; }


    public void clear() {
        clearValue();
        clearChildren();
    }
}
