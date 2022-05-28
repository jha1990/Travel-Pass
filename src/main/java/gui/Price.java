package gui;

import java.io.Serializable;

public class Price implements Serializable {
    // data members
    private String type;
    private double cost;

    // parameterized constructor
    public Price(String type, double cost) {
        this.type = type;
        this.cost = cost;
    }

    // getter
    public String getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }

    // setter

    public void setCost(double cost) {
        this.cost = cost;
    }
}
