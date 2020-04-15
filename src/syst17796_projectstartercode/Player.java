/**
 * SYST 17796 Project Winter 2020 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package syst17796_projectstartercode;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @modified Paul Bonenfant Jan 2020
 * @author Jesse Thompson April 2020
 */
public class Player {

    private String name; //the unique name for this player
    private boolean playing = false;
    private ArrayList<Card> hand = new ArrayList<Card>();
    private int bet = 0;
    private boolean dealer = false;
    private int won = 0;
    
    // A constructor that allows you to set the player's unique ID

    public Player(String name, int bet) {
        this.name = name;
        this.bet = bet;
        this.playing = true;
    }
    
    // If player is a dealer, use this constructor to set dealer boolean to true
    public Player(String name, Boolean dealer) {
        this.name = name;
        this.playing = true;
        this.dealer = dealer; 
    }

    // Player name
    public String getName() {
        return name;
    }

    // Get player name
    public void setName(String name) {
        this.name = name;
    }

    // Get player state
    public Boolean getPlayingState() {
        return this.playing;
    }
    
    public void recieveCard(Card card) {
        this.hand.add(card);
    }
    
    public ArrayList<Card> getHand() {
        return this.hand;
    }
    
    public int getHandSize() {
        return this.hand.size();
    }
    
    public int getHandValue() {
        int value = 0;
        for (Card card : this.getHand()) {
            value += card.getValue().getDispNum();
        }
        return value;
    }
    
    /**
     * Get value of hand when aces are counted 
     * @return 
     */
    public int getHandValueAlt() {
        int value = 0;
        for (Card card : this.getHand()) {
            if  (card.getValue().getDispNum() == 1) {
                value += 11;
            } else {
                value += card.getValue().getDispNum();
            }
        }
        return value;
    }
    
    public int getBet() {
        return this.bet;
    }
    
    // When player loses game, report the amount of money they lost.
    public int setLost() {
        this.playing = false;
        return this.getBet();
    }
    
    // Will set player playing state to false, sets won to amount won and returns value
    public int setWon(Boolean blackjack) {
        this.playing = false;
        if (blackjack) {
            this.won = (int) (this.getBet()*1.5);
        } else {
            this.won = this.getBet()*2;
        }
        return this.won;
    }
    
    public String toString() {
        if (this.getPlayingState()) {
            return this.getName() + " is currently playing" ;
        } else {
            return this.getName() + " is currently not playing";
        }
    }

}
