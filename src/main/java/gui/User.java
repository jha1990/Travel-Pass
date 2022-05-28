package gui;

import java.io.Serializable;

public class User implements Serializable {
    // data members
    private String id;
    private String type;
    private String name;
    private String email;

    private TravelPass pass;


    //parameterized constructor
    public User(String id, String type, String name, String email) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        pass = new TravelPass();

    }

    // Accessor and Mutator
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @param credit amount which needs to credit to be pass
     */
    public void rechargeTravelPass(double credit) {
        pass.recharge(credit);
    }


    /**
     * @return the credit of pass
     */
    public double getCredit() {
        return pass.getCredit();
    }

    /**
     * this method display the user information
     */
    public void displayInformation() {
        System.out.println("User Information ");
        System.out.println(String.format("%-15s %-5s", "ID", id));
        System.out.println(String.format("%-15s %-5s", "Name", name));
        System.out.println(String.format("%-15s %-5s", "Type", type));
        System.out.println(String.format("%-15s %-5s", "Email", email));
        System.out.println(String.format("%-15s %-5.2f", "Credit Remaining", pass.getCredit()));
        pass.showHistory();
    }

    /**
     * @param journey added to user
     */
    public void addJourneyToUser(Journey journey, String passType) {
        pass.addJourney(journey, passType);
    }

    /**
     * @param arrivalTime if any journey pass have same departure time
     * @return true if exist else false
     */
    public boolean checkPassifAvaiable(int arrivalTime) {
        return pass.ifPassValid(arrivalTime);
    }

    /**
     * @return the pass type of the user
     */
    public String getPassType() {
        return pass.getPassType();
    }

    public TravelPass getPass() {
        return pass;
    }
}
