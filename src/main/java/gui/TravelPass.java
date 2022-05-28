package gui;
import java.io.Serializable;
import java.util.ArrayList;

public class TravelPass implements Serializable {

    // parameters
    private ArrayList<Journey> journeyList;
    private String passType;
    private double credit; // credit of the card

    /**
     * default constructor
     */
    public TravelPass() {
        credit = 0;
        journeyList = new ArrayList<>();
    }


    /**
     * parameterized constructor
     *
     * @param credit value of credit at starting
     */
    public TravelPass(double credit) {
        this.credit = credit;
        journeyList = new ArrayList<>();
    }

    /**
     * @param credit recharge the credit
     */
    public void recharge(double credit) {
        this.credit = this.credit + credit;
    }

    /**
     * @return the credit of ticket
     */
    public double getCredit() {
        return credit;
    }

    /**
     * @param journey which needed to be added
     */
    public void addJourney(Journey journey, String passType) {
        journeyList.add(journey);
        credit = credit - journey.getCost();
        this.passType = passType;
    }

    /**
     * display the history of the pass
     */
    public void showHistory() {
        System.out.println(String.format("%-15s %-5d", "Number of journey: ", journeyList.size()));
    }

    public String getPassType() {
        return passType;
    }

    public boolean ifPassValid(int arrivalTime) {
        if (passType.equalsIgnoreCase("2 hour")) {
            for (int i = 0; i < journeyList.size(); i++) {
                if ((journeyList.get(i).getDepartureTime() + 200) < arrivalTime) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Journey> getJourneyList() {
        return journeyList;
    }
}
