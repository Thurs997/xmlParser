package pl.edu.pw.ii.tkom.lkamins1;

import pl.edu.pw.ii.tkom.lkamins1.exceptions.PropertyNotFoundException;
import pl.edu.pw.ii.tkom.lkamins1.exceptions.WrongValueTypeException;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Atom;
import pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers.Constraint;
import pl.edu.pw.ii.tkom.lkamins1.tokens.Value;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 11.05.14.
 */
public class Property implements Comparable<Property> {
    private Value value = null;
    private List<Property> children = null;
    private String tag;
    private int nestingLevel = 0;
    private static final Logger logger = Logger.getLogger(Property.class.getName());
    public int compareTo(Property another) {
        return tag.compareTo(another.getTag());
    }

    public Property (String tag) {
        setTag(tag);
        logger.log(Level.INFO, "New with tag: " + tag);
    }

    public void addProperty(Property child) {
        clearValue();
        logger.log(Level.INFO, "I am: " + tag + "["+nestingLevel+". Adding child with tag: " + child.getTag());
        if(children == null) {
            logger.log(Level.INFO, "Had to create multiset");
            children = new LinkedList<Property>();
        }
        children.add(child);
    }

    void setNestingLevel(int level) {nestingLevel = level;}

    private void clearValue() {
        value = null;
    }

    private void clearChildren() {
        if(children != null) {
            for(Property entry : children) {
                entry.clear();
            }
            children.clear();
            children = null;
        }
    }

    public String[] getChildrenAsStringArray(String nodeName) { return new String[]{}; }
    public Integer[] getChildrenAsIntArray(String nodeName) { return new Integer[]{}; }
    public Double[] getChildrenAsDoubleArray(String nodeName) { return new Double[]{}; }
    public String asString() { return value.getRaw(); }
    public Integer asInt() throws WrongValueTypeException { return value.asInt(); }
    public Double asDouble() throws WrongValueTypeException { return value.asDouble(); }
    public void setValue(int intValue) { clearChildren(); value = new Value(intValue); }
    public void setValue(String stringValue) { clearChildren(); value = new Value(stringValue); }
    public void setValue(double doubleValue) { clearChildren(); value = new Value(doubleValue); }
    public void setTag(String tag) { this.tag = tag; }
    public String getTag() { return tag; }
    public String toString() {
        String retStr = indent() + tag + ":";
        if(children == null) {
            retStr += value.getRaw();
        } else {
            retStr += "("+ children.size()+") {";
           for(Property entry : children) {
               entry.setNestingLevel(nestingLevel+1);
                retStr += '\n' + entry.toString() + ",";
               entry.setNestingLevel(0);
            }
            retStr = retStr.substring(0,retStr.length()-1);
            retStr += '\n' + indent() + "}";
        }
        return retStr;
    }

    public String toHTML() {
        String retStr = indent() + "<" + tag + " type=\""+ getType() +"\">";
        if(children == null) {
            retStr += value.getRaw();
        } else {
            for(Property entry : children) {
                entry.setNestingLevel(nestingLevel+1);
                retStr += '\n' + entry.toHTML();
                entry.setNestingLevel(0);
            }
            retStr += '\n' + indent();
        }
        retStr += "</" + tag + ">";
        return retStr;
    }

    private String getType() {
        if(children != null && !children.isEmpty())
            return "section";
        try {
            asInt();
            return "int";
        } catch (WrongValueTypeException e) {}
        try {
            asDouble();
            return "double";
        } catch (WrongValueTypeException e) {}
        return "string";
    }

    private String indent() {
        String retVal = "";
        for(int i = 0; i < nestingLevel; ++i) {retVal += '\t';}
        return retVal;
    }

    Property getNextInPath(Atom atom) throws PropertyNotFoundException {
        for(Property child : children) {
            if(child.getTag().equals(atom.getPath())) {
                if(atom.hasConstraints()) {
                    if(child.meetsConstraints(atom.getConstraints())) {
                        return child;
                    }
                } else
                {
                    return child;
                }
            }
        }
        throw new PropertyNotFoundException();
    }

    boolean meetsConstraints(List<Constraint> constraints) {
        for(Constraint constraint : constraints) {
            if(!hasChild(constraint.atttribute, constraint.rawValue))
                return false;
        }
        return true;
    }

    private boolean hasChild(String tag, String val) {
        for(Property child : children) {
            if(child.getTag().equals(tag) && child.asString().equals(val))
                return true;
        }
        return false;
    }

    public Property insert(String propertyName) {
        Property retVal = new Property(propertyName);
        addProperty(retVal);
        return retVal;
    }
    public Property insert(String propertyName, Integer value) {
        Property retVal = insert(propertyName);
        retVal.setValue(value);
        return retVal;
    }
    public Property insert(String propertyName, String value) {
        Property retVal = insert(propertyName);
        retVal.setValue(value);
        return retVal;
    }
    public Property insert(String propertyName, Double value) {
        Property retVal = insert(propertyName);
        retVal.setValue(value);
        return retVal;
    }

    public void clear() {
        clearValue();
        clearChildren();
    }
}
