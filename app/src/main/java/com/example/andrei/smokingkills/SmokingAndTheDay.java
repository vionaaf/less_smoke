package com.example.andrei.smokingkills;
public class SmokingAndTheDay {
    String numberOfCigarettes;
    String theDay;
    public SmokingAndTheDay(){

    }
    public SmokingAndTheDay(String numberOfCigarettes, String theDay){
        this.numberOfCigarettes = numberOfCigarettes;
        this.theDay = theDay;
    }

    public String getNumberOfCigarettes() {
        return numberOfCigarettes;
    }

    public String getTheDay() {
        return theDay;
    }

    public void setNumberOfCigarettes(String numberOfCigarettes) {
        this.numberOfCigarettes = numberOfCigarettes;
    }

    public void setTheDay(String theDay) {
        this.theDay = theDay;
    }

}
