package ca.mohawk.myapplication;

public class Projects {
    public String _id;
    public String Year;
    public String ProjectNum;
    public String Title;
    @Override
    public String toString() {
        return Year + " " + ProjectNum + " " + Title;
    }
}

