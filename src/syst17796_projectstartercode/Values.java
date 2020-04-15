/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syst17796_projectstartercode;

/**
 *
 * @author Jesse Thompson
 */

// This is a class for card value enums
public enum Values {
    ACE(1, "Ace"),
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(10, "Jack"),
    QUEEN(10, "Queen"),
    KING(10, "King");
    
    private double dispNum;
    private String dispName;
    
    private Values(double dispNum, String dispName) {
        this.dispNum = dispNum;
        this.dispName = dispName;
    }

    public double getDispNum() {
        return dispNum;
    }

    public String getDispName() {
        return dispName;
    }
    
    public String toString() {
        return this.dispNum + " " + this.dispName;
    }
}
