package ca.mohawk.project;

public class Course {
    public String _id;
    public String program;
    public String semester;
    public String courseCode;
    public String courseTitle;
    public String courseDescription;
    public String courseEtc;
    public String courseOwner;
    public String optional;
    public String hours;

    @Override
    public String toString() {
        return courseCode + " - " + courseTitle;
    }
}
