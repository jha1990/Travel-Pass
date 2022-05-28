package gui;
import java.io.Serializable;

public class Journey implements Serializable {
    // data members
    private Station startStation;
    private Station endStation;
    private String day;
    private int departureTime;
    private int arrivalTime;
    private double cost;

    private String stStationStr;
    private String edStationStr;

    private String comments;


    // default constructor
    public Journey() {

    }

    // parametrized constructor

    public Journey(Station startStation, Station endStation, String day, int departureTime, int arrivalTime, double cost) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.day = day;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.cost = cost;
    }


    //Getter and Setter

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the difference between arrival time and departure time
     */
    public int getInterval() {
        return arrivalTime - departureTime;
    }

    public String getStStationStr(){
        return startStation.getName();
    }

    public String getEdStationStr(){
        return endStation.getName();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
