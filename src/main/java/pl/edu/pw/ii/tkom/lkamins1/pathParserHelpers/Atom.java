package pl.edu.pw.ii.tkom.lkamins1.pathParserHelpers;

import java.util.List;

/**
 * Created by lucas on 01.06.14.
 */
public class Atom {
    private String path;
    private List<Constraint> constraints;

    public boolean hasConstraints() {return (constraints != null && constraints.size() > 0);}

    public List<Constraint> getConstraints() {return constraints;}
    public void addConstraints(List<Constraint> constraints) {this.constraints = constraints;}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
