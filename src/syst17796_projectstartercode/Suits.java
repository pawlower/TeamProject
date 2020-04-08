/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syst17796_projectstartercode;

/**
 *
 * @author Owner
 */
public enum Suits {
    CLUBS(1, "Clubs"),
    SPADES(2, "Spades"),
    HEARTS(3, "Hearts"),
    DIAMONDS(4, "Diamonds");
    
    private double dispNum;
    private String dispName;
    
    private Suits(double dispNum, String dispName) {
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