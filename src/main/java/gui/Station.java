package gui;

import java.io.Serializable;

public class Station implements Serializable {
    // data members
    private String name;
    private int zone;
    private int noOfVisit;

    // parametrized constructor
    public Station(String name, int zone) {
        this.name = name;
        this.zone = zone;
        noOfVisit = 0;
    }

    // Accessor And Mutator
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getNoOfVisit() {
        return noOfVisit;
    }

    public void visit() // every time user visit the station
    {
        noOfVisit++;
    }

}
